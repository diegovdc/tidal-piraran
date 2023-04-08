var path = thisProcess.nowExecutingPath.dirname;

var protestas = SoundFile.collectIntoBuffers(path++"/Instruments/agua"++"/*");

var numFrames= protestas.collect{|item|  item.numFrames};

numFrames.postln;

SynthDef(\samplePlayer, {
	| buf= 0, rate=1, imp=0, startPos=0, loop=0, amp=0.25,
	att= 0.1, dec= 0.1, sus=1, rel=0.3, gate=1, out=0, pan=0 |
	var sig, env;

	sig= PlayBuf.ar(1,buf,rate,Impulse.kr(imp),startPos,loop);
	env= EnvGen.kr(Env.adsr(att,dec,sus,rel),gate,doneAction:2);

	Out.ar(out, Pan2.ar(sig*amp,pan)*env)

}).add;


Pdef(\voices, Pbind(
	\buf, Prand(protestas,inf),
	\instrument, \samplePlayer,
	\dur, Pwhite(0.1,1),
	\rate, Pwhite(0.75,1)*Pxrand([-1,1],inf),
	\att, Prand([0.1,0.5,1,0.005],inf),
	\rel, Pkey(\dur)*Pwhite(5,15),
	\startPos, Pwhite(0, numFrames.minItem * 0.9 ),
	\legato, Pxrand([1,2,3,4,1,0.75,0.666],inf),
	\db, Pxrand([-12,-6,-3,-18],inf),
	\pan, Pwhite(1.0,-1.0),
	\out, 20, // synth listening to protest sounds (debe ser el mismo q \in1 en el synth protesta
));

SynthDef(\protesta, {
	|amp=0.125, ampConv=1, ampIn=1, ampKl=1, ampProtesta=1, ampTidal=1, gate=1, in1=20, in2=0, out=14|

	var in, kl, cleanIn, cleanKl, cvL, cvR, sig, env;

	in= PitchShift.ar(In.ar(in1,2), 0.2,1.3,1.2,0.2,1)*LFNoise2.ar(1.2).range(0.5,1)*ampIn;

	kl= Splay.ar(In.ar(in2,14)*LFNoise2.ar([0.8,0.5,0.9,0.7,0.76,0.6,1.01,0.95]).range(0.5,1))*ampKl;

	cvL= Convolution.ar(in[0],kl[0],512,0.5)*LFNoise2.ar(0.21).range(2,5);

	cvR= Convolution.ar(in[1],kl[1],512,0.5)*LFNoise2.ar(0.23).range(2,5);

	cleanIn= In.ar(in1,2)*SinOsc.kr(0.23,0,1,2.5);

	cleanKl= In.ar(in2,2)*SinOsc.kr(0.15,0,0.3,0.4);

	sig= (cleanIn*ampProtesta) + (cleanKl*ampTidal) + ([cvL,cvR]*ampConv);


	env= EnvGen.kr(Env.adsr(0.01,0.1,0.75,0.2),gate,doneAction:2);

	Out.ar(out, Limiter.ar(sig*env*amp,0.85))
	// Out.ar(out, kl)   // este es el out para calibrar el kernel
}).add;

/*( // testing
Pdef(\voices).play;
~protesta = Synth(\protesta, [\in1, 20, \in2, 0, \amp,1, \out, 14], addAction: \addToTail);
)*/

// para probar se tienen q considerar los canales de manera adecuada, el out del synth protesta es por donde debe de salir el sonido al DAW (lo tenemos en 14 por ahora), si el out de protesta es 0 habra retroalimentacion entre in2 (canales 0 a 13) y out de protesta. No es catastrofico pero no es el efecto buscado.

// el kernel esta recibiendo 14 canales (del 0 al 13) y los esta distribuyendo en un campo stereo con Splay, la ganancia del kernel debe de ser probada en condiciones reales: mandar desde tidal un programa pleno (bajo, baterias, melodias, bombo, etc.) y usar el Out de prueba en el sincho para calibrar el kernel


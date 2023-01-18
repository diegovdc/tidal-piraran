//Wavetable Synths for TidalCycles

// thinks to do:
////  add a vibrato: lfo to the sig of every synth but taking into consideration that any sig multiplied by 0 will make the synth be silent


//load wavetable catalogue;
var path = thisProcess.nowExecutingPath.dirname;
var waveShapeCatalogue = path++"/wavetable";
//catalogue taken from here: https://www.adventurekid.se/akrt/waveforms/
// open source and free for distribution
// adapted to SC in the sc mailing list (search for thread)

// input is a range (min, max numbers) and the output is a mul and add value for an oscillator;
~range= { |min= 102, max= 400|
	var mul= (max - min)/2;
	var add= min + mul;

	(multi: mul, addi: add)
};

// test, the polled number should go from 100 to 200
/*(
var val= ~range.(100,200);
{SinOsc.kr(0.1,0,val.multi,val.addi).poll}.play
)*/

~waveArray = Array.new(44);

// this can be automatised so no manual input is necessary
~waveList = ["_aguitar","_altosax","_birds","_bitreduced","_blended","_bw_saw","_bw_sawbright","_bw_sawgap","_bw_sawrounded","_bw_sin","_bw_sq","_bw_sqrounded","_bw_tri","_c604","_cello","_clarinett","_clavinet","_dbass","_distorted","_ebass","_eguitar","_eorgan","_epiano","_flute","_fmsynth","_granular","_hdrawn","_hvoice","_oboe","_oscchip","_overtone","_piano","_pluckalgo","_raw","_sinharm","_snippets","_stereo","_stringbox","_symetric","_theremin","_vgame","_vgamebasic","_violin"];

(~waveList.size+1).do{|i|
	var akwf= "/AKWF", list= ~waveList;

//	(wavsCatalogue++akwf++list[i]++"/*").postln;
	if(i==0,
		{~waveArray.insert(i,SoundFile.collectIntoBuffers(waveShapeCatalogue++akwf++"/*"))},
		{~waveArray.insert(i,SoundFile.collectIntoBuffers(waveShapeCatalogue++akwf++list[i-1]++"/*",s))}
	);

};

~waveShapeList= ["AKWF"]++~waveList;

~postOscs= ~waveShapeList.collect({|item|  item.postln; \n });

(
~osciladores = Dictionary.new;

~waveArray.size.do{|i|
	~osciladores.add(~waveShapeList[i].asSymbol ->
		(
			min: ~waveArray[i][0].bufnum,
			max: ~waveArray[i][~waveArray[i].size-1].bufnum)
	).postln;
}
);

(// check all the ranges and the oscilators
~osciladores.size;
~waveArray.size.do{|i| ~osciladores.at(~waveShapeList[i].asSymbol).postln}
);


// Synthdefs

// all waveshapes
SynthDef(\wpad, {
	| wave=0, freq=300, amp=1,
	att=1, dec=0.1, sus=1, rel=1, gate=1, pan=0.5, out=0 |
	var sig, env;
	sig= Osc.ar(wave, freq, 0, amp);
	env= EnvGen.kr(Env.adsr(att, dec, sus, rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

// first test, change the waveshape with the param wave!
/*(instrument: \wpad, wave: 500).play;*/

// all waveshapes with a LPF and a simple 4 point envelope
SynthDef(\wlpfpad, {
	| wave=0, freq=300, rq=0.1, amp=0.5,
	att=1, dec=0.1, sus=1, rel=1, gate=1, pan=0.5, out=0,
	p1=5,p2=8, p3=13, p4=5, t1=0.2, t2=0.2, t3=0.2 |
	var sig, filter, env;
	filter= EnvGen.kr(Env([p1,p2,p3,p4],[t1,t2,t3]));
	sig= Osc.ar(wave, freq, 0, 1);
	sig= RLPF.ar(sig,freq*filter, rq, amp);
	env= EnvGen.kr(Env.adsr(att, dec, sus, rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wlpfpad, wave: 1500).play;*/

// all waveshapes with a HPF and a simple 4 point envelope
SynthDef(\whpfpad, {
	| wave=0, freq=300, rq=0.1, amp=1,
	att=1, dec=0.1, sus=1, rel=1, gate=1, pan=0.5, out=0,
	p1=4,p2=6, p3=2, p4=3, t1=0.2, t2=0.2, t3=0.2 |
	var sig, filter, env;
	filter= EnvGen.kr(Env([p1,p2,p3,p4],[t1,t2,t3]));
	sig= Osc.ar(wave, freq, 0, 1);
	sig= RHPF.ar(sig,freq*filter, 0.1, amp);
	env= EnvGen.kr(Env.adsr(att, dec, sus, rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \whpfpad, wave: 1500).play;*/

// waveshapes with chorus
SynthDef(\wchpad, {
	| wave=1, freq=300, beats=0.5, amp=1,
	att=1, dec=0.1, sus=1, rel=1, gate=1, pan=0.5, out=0 |
	var sig, env;
	sig= COsc.ar(wave, freq, beats, amp);
	env= EnvGen.kr(Env.adsr(att, dec, sus, rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wchpad, wave: 3500).play;*/

// interpolation between waveshapes
SynthDef(\wvpad, {
	| bufPos=0.1, phase=1, min=40, max= 50, freq=300, amp=1,
	att=1, dec=0.1, sus=1, rel=1, gate=1, pan=0.5, out=0 |
	var range, osc, sig, env;
	range= ~range.(min, max);
	osc= SinOsc.ar(bufPos, phase, range.multi, range.addi);
	sig= VOsc.ar(osc, freq, 0, amp);
	env= EnvGen.kr(Env.adsr(att, dec, sus, rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wvpad).play;*/

// interpolation between waveshapes, 3 freqs at the same time
SynthDef(\wv3pad, {
	| bufPos=0.1, phase=1, min=40, max= 50, freq=300, offset=#[0,0,0], amp=1,
	att=1, dec=0.1, sus=1, rel=1, gate=1, pan=0.5, out=0 |
	var range, osc, pitch, sig, env;
	range= ~range.(min, max);
	osc= SinOsc.ar(bufPos, phase, range.multi, range.addi);
	sig= VOsc3.ar(osc, freq+offset[0], freq+offset[1], freq+offset[2], amp);
	env= EnvGen.kr(Env.adsr(att, dec, sus, rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wv3pad).play;*/

// Percusive Envelope Synths, los mismos synths pero con envolvente percusivo, los argumentos \att y \rel controlan el attaque y el release respectivamente independientemente del tiempo de onset de cada evento

// all waveshapes
SynthDef(\wperc, {
	| wave=50, freq=300, amp=1,
	att=0.001, rel=1, gate=1, pan=0.5, out=0 |
	var sig, env;
	sig= Osc.ar(wave, freq, 0, amp);
	env= EnvGen.kr(Env.perc(att,rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wperc).play;*/

// all waveshapes with a LPF and a simple 4 point envelope
SynthDef(\wlpfperc, {
	| wave=3500, freq=300, rq=0.1, amp=0.5,
	att=0.001, rel=1, gate=1, pan=0.5, out=0,
	p1=5,p2=8, p3=13, p4=5, t1=0.2, t2=0.2, t3=0.2 |
	var sig, filter, env;
	filter= EnvGen.kr(Env([p1,p2,p3,p4],[t1,t2,t3]));
	sig= Osc.ar(wave, freq, 0, 1);
	sig= RLPF.ar(sig,freq*filter, rq, amp);
	env= EnvGen.kr(Env.perc(att,rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wlpfperc).play;*/

// all waveshapes with a HPF and a simple 4 point envelope
SynthDef(\whpfperc, {
	| wave=250, freq=300, rq=0.1, amp=0.5,
	att=0.001, rel=1, gate=1, pan=0.5, out=0,
	p1=4,p2=6, p3=2, p4=3, t1=0.2, t2=0.2, t3=0.2 |
	var sig, filter, env;
	filter= EnvGen.kr(Env([p1,p2,p3,p4],[t1,t2,t3]));
	sig= Osc.ar(wave, freq, 0, 1);
	sig= RHPF.ar(sig,freq*filter, 0.1, amp);
	env= EnvGen.kr(Env.perc(att,rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \whpfperc).play;*/

// waveshapes with chorus
SynthDef(\wchperc, {
	| wave=100, freq=300, beats=0.5, amp=0.5,
	att=0.01, dec=0.1, susTime=1, rel=1, gate=1, pan=0.5, out=0 |
	var sig, env;
	sig= COsc.ar(wave, freq, beats, amp);
	env= EnvGen.kr(Env.perc(att,rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wchperc).play;*/

// interpolation between waveshapes
SynthDef(\wvpPerc, {
	| bufPos=0.1, phase=1, min=0, max= 30, freq=300, amp=0.5,
	att=0.01, rel=1, gate=1, pan=0.5, out=0 |
	var range, osc, sig, env;
	range= ~range.(min, max);
	osc= SinOsc.ar(bufPos, phase, range.multi, range.addi);
	sig= VOsc.ar(osc, freq, 0, amp);
	env= EnvGen.kr(Env.perc(att,rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wvpPerc).play;*/

// interpolation between waveshapes, 3 freqs at the same time
SynthDef(\wv3perc, {
	| bufPos=0.1, phase=1, min=0, max= 30, freq=300, offset=#[0,0,0], amp=0.5,
	att=0.01, rel=1, gate=1, pan=0.5, out=0 |
	var range, osc, sig, env;
	range= ~range.(min, max);
	osc= SinOsc.ar(bufPos, phase, range.multi, range.addi);
	sig= VOsc3.ar(osc, freq+offset[0], freq+offset[1], freq+offset[2], amp);
	env= EnvGen.kr(Env.perc(att,rel),gate, doneAction:2);
	OffsetOut.ar(out, DirtPan.ar(sig, ~dirt.numChannels, pan, env))
}).add;

/*(instrument: \wv3perc).play;*/

// ----------------------------------------; // FX and Filters

// add fx here



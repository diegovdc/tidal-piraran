// Controlers for convolution
( // play!!!
Pdef(\voices).play;
~protesta = Synth(\protesta, [\in1, 20, \in2, 0, \amp,1, \out, 14], addAction: \addToTail);
)


// controles
~protesta.set(\ampConv, 3) // volume of convolution
~protesta.set(\ampTidal, 1) // volume from clean signal coming from tidal
~protesta.set(\ampProtesta, 1) // volume of clean signal from pattern playing protest
~protesta.set(\ampKl, 3) // volume input to kernel (amount of tidal to convo)
~protesta.set(\ampIn, 1) // volume input to input (amount of protest to convo)
~protesta.set(\amp, 1) // general volume



~protesta.set(\gate,0) // turn off // panic



~protesta.set(\out,0) // change out from this process to DAW
// 0 will feedback but easy monitoring and not catastrophic event

Pdef(\voices).stop // stop the pdef playing the protest sounds
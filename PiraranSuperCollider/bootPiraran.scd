(
var path = thisProcess.nowExecutingPath.dirname.postln;
var environment = path ++ "/env.scd";
var wavesynths = path ++ "/Instruments/Synths.sc";
s.options.numBuffers = 1024 * 16;
Server.default.options.memSize= 512000*20;
Server.default.options.maxNodes=128*1024;
Server.default.options.numWireBufs= 512;

// devices
//Server.default.options.device = "ASIO : MOTU Pro Audio";   ////// Interfaz de NIL
//Server.default.options.device = "Fireface UCX (23893620)"; ////// Interfaz de Ivan

// outputs
Server.default.options.numOutputBusChannels = 16;

// Sample Rate
Server.default.options.sampleRate = 48000;

s.boot;

s.waitForBoot({
	wavesynths.load; // load the synths
	~dirt = SuperDirt(2, s); // two output channels, increase if you want to pan across more channels
	// leave this at 2 (stereo channel per orbit)

    ~dirt.loadSoundFiles;   // load samples (path containing a wildcard can be passed in)
	~dirt.loadSoundFiles(path ++ "/Instruments/tarola/");
    s.sync; // wait for samples to be read
    ~dirt.start(57120, [0, 2, 4, 6, 8, 10, 12, 14]);   // start listening on port 57120, create two orbits, each sending audio to channel 0. You can direct sounds to the orbits from tidal e.g. by: `# orbit "0 1 1"
	// MIDIClient.init;
	try({
		~midiOut = MIDIOut.newByName(~midiDeviceName0, ~midiPortName0); // aqui van las variables globales de midiDeviceName0, midiPortName0
		~dirt.soundLibrary.addMIDI(\bajo, ~midiOut);
		~dirt.soundLibrary.addMIDI(\bombo, ~midiOut);
	},
	{"Could not connect to midi".warn;});
});

);



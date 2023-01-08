(
var path = thisProcess.nowExecutingPath.dirname.postln;
var wavesynths = path ++ "/Instruments/Synths.sc";
s.options.numBuffers = 1024 * 16;
Server.default.options.memSize= 512000*20;
Server.default.options.maxNodes=128*1024;
Server.default.options.numWireBufs= 512;

// devices
//Server.default.options.device = "ASIO : MOTU Pro Audio";   ////// Interfaz de NIL
//Server.default.options.device = "Fireface UCX (23893620)"; ////// Interfaz de Ivan

// outputs
Server.default.options.numOutputBusChannels = 2;

// Sample Rate
Server.default.options.sampleRate = 48000;

s.boot;

s.waitForBoot({
	wavesynths.load; // load the synths

	~dirt = SuperDirt(2, s); // two output channels, increase if you want to pan across more channels
    ~dirt.loadSoundFiles;   // load samples (path containing a wildcard can be passed in)
    s.sync; // wait for samples to be read
    ~dirt.start(57120, [0, 0]);   // start listening on port 57120, create two orbits, each sending audio to channel 0. You can direct sounds to the orbits from tidal e.g. by: `# orbit "0 1 1"

});

);



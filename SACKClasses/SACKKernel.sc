SACKKernel {

	*start {
		var engine, sequencer, controlProcessor;

		Routine {
			var server, name, synthsPath, synths;

			server = Server.default;
			name = \test;

			synthsPath = PathName(thisProcess.nowExecutingPath).pathOnly+/+"SackSynthdefs.scd";

			engine = SACKEngine.new;
			synths = engine.addSynthDefs(synthsPath);

			engine = SACKEngine.new;
			sequencer = SACKSequencer.new([\pp], engine, "SackPatterns.scd");

			controlProcessor = SACKControlProcessor.new;
			controlProcessor.createStartStop(sequencer);

		}.play;
	}
}
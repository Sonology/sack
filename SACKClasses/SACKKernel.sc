SACKKernel {



	*start {
		var engine, sequencer, controlProcessor;

		Routine {
			var server, name, synthsPath, synths;

			server = Server.default;
			name = \test;

			synthsPath = PathName(thisProcess.nowExecutingPath).pathOnly+/+"SackSynthdefs.scd";


			engine = SACKEngine.new(server);

			server.sync;

			synths = engine.addSynthDefs(synthsPath);





			sequencer = SACKSequencer.new(synths, engine);
			controlProcessor = SACKControlprocessor.new;
			controlProcessor.createStartStop(sequencer);
		}.play;
	}
}
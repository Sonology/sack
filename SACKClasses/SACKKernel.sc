SACKKernel {


	*start {
		var engine, sequencer, controlProcessor;

		Routine {
			var server, name, synths;

			server = Server.default;
			name = \test;


			engine = SACKEngine.new(server);

			server.sync;

			synths = engine.addSynthDefs;





			sequencer = SACKSequencer.new(synths, engine);
			/*controlProcessor = SACKControlprocessor.new;
			controlProcessor.createStartStop(sequencer);*/
		}.play;
	}
}
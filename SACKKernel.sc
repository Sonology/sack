SACKKernel {


	*start {
		var engine, sequencer, controlProcessor;

		Routine {
			var server, name, synths;

			server = Server.default;
			name = \test;


			engine = SACKEngine.new(this, server);

			server.sync;



			sequencer = SACKSequencer.new(synths, engine);
/*			controlProcessor = SACKControlprocessor.new;
			controlProcessor.createStartStop(sequencer);
*/
		}.play;
	}
}
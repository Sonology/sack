SACKKernel {

	*start {
		var engine, sequencer, controlProcessor;

		Routine {
			var server, name;

			server = Server.default;
			name = \test;

			SynthDef(name, {|freq=1, amp=0.2|
				Out.ar(0, SinOsc.ar(LinLin.kr(0.0,1.0,100,1000) * EnvGen.kr(Env.perc, 1, amp,doneAction: 2) ! 2))
			}).add;

			server.sync;

			engine = SACKEngine.new;
			sequencer = SACKSequencer.new(name, engine);
			controlProcessor = SACKControlProcessor.new;
			controlProcessor.createStartStop(sequencer);

		}.play;
	}
}
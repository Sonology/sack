SACKEngine {

	var synth;
	var outBus;
	var volume;

	*new { arg outBus;
		^super.new.init(outBus)
	}

	init { arg bus=0;
		outBus=bus?0;
	}

	play {arg event;
		synth=Synth(event[\instrument],[
			\freq, event[\freq],
			\amp, event[\amp],
			\dur, event[\dur],
			\pan, event[\pan],
			\att, event[\att],
			\rel, event[\rel],
			\outBus, outBus
			]
		);
		event.postln;

	}

	print {
		"a".postln;
	}

	listSynths {
		^\sine;
	}
}
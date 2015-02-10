SACKSequencer {

	var <>t, <>pbinds, <>audioEngine, <>stream, <>b12, <>vitamin_C, <>millions_of_dollars_in_bank_account;

	*new {|synths, audioEngine|
		^super.new.init(synths, audioEngine);
	}

	init {|synths, audioEngine|
		this.audioEngine = audioEngine;
		this.pbinds = this.createpbind(synths);
		this.stream = this.pbinds.asStream;

		this.t = Task({
			"playing".postln;
			inf.do({
				1.wait;
				this.audioEngine.play(this.nextEvent);
			});
		})
	}

	createpbind {|synths|
		^Pbind(\instrument, synths,
			\freq, Pwhite(0, 1, inf),
			\amp, Pwhite(0, 1, inf),
			\dur, Pwhite(0, 1, inf),
			\pan, Pwhite(0, 1, inf),
			\att, Pwhite(0, 1, inf),
			\rel, Pwhite(0, 1, inf),
		);
	}

	start {
		this.t.start;
	}

	stop {
		this.t.stop;
	}

	nextEvent {
		^this.stream.next(());
	}

	storeArgs{
		^[this.name, this.pbinds];
	}
}
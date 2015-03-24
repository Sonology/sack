SACKSequencer {

	var <>audioEngine, <>stream, <>run, <>pattern_array, <>synths;

	*new {|synths, audioEngine, patternsFilename, tempo|
		^super.new.init(synths, audioEngine, patternsFilename);
	}

	init {|synths, audioEngine, patternsFilename, tempo=60|
		this.audioEngine = audioEngine;
		this.synths = synths;
		this.pattern_array = SackInclude.load(patternsFilename).value; // evaluate code in patternsFilename;
		this.pattern_array[0].postcs;
		this.stream = this.pattern_array.choose.asStream;

		this.run = Task({
			"playing".postln;
			inf.do({
				var newEvent = ();
				newEvent.freq = this.nextEvent;
				newEvent.instrument = synths[0];
				this.audioEngine.play(newEvent);
				(60.0/tempo).wait;
			});
		})
	}

	start {
		this.run.start;
	}

	stop {
		this.run.stop;
	}

	nextEvent {
		^this.stream.next();
	}

	tempo {
		^this.tempo;
	}

	tempo_ {
		|tempo|
		this.tempo = tempo;
	}
}
SACKEngine {

	var outBus, <synth, <>synths, <volume, <server, <bufs, <buses;

	*new { arg s;

		"
		_____  ______  ______
		|      |     | |      |   /
		\\'     |     | |      |  /
		 \\'    |     | |      | /
		  \\'   |=====| |      |/\\'
		   \\'  |     | |      |  \\'
		    \\' |     | |      |   \\'
		____|  |     | |_____ |    \\'



		".postln;
		^super.new.init(s)
	}


	init { arg s;



		server = s ? Server.default;


		synths = SynthDescLib.new(\SACKLib); //initialize SACKLib
		synths.addServer(server); //associate it with the server we want to use
		server.boot;


		bufs = IdentityDictionary.new(know: true);
		buses = IdentityDictionary.new(know: true);

	}


	addSynthDefs { arg libName;

		var lib = libName ? synths.name.asSymbol;
		var string, defs;

		string = String.readNew(File(PathName(thisProcess.nowExecutingPath).pathOnly+/+"SackSynthdefs.scd", "r")).split($_); //read in synthdefs as strings

		defs = string.collect({arg item, i;
			var def = thisProcess.interpreter.interpret(item);
			def;
		});

		defs.do( _.add(lib));
		^this.synths;
	}

	play { arg event;

		event.postln;
		synth = event.play;
	}

	createControlBus { arg name, numChannels = 1;
		var nm = Bus.control(server, numChannels);
		var what = name?("bus"++this.buses.size);
		buses.put(what.asSymbol, nm);
		^buses;
	}

	createAudioBus { arg name, numChannels = 1;
		var nm  = Bus.audio(server, numChannels);
		var what = name?("bus"++this.buses.size);
		buses.put(what.asSymbol, nm);
		^buses;

	}



	print {
		"a".postln;
	}

	listSynths {
		^\sine;
	}
}
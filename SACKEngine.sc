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

		var string, defs;

		string = String.readNew(File(PathName(thisProcess.nowExecutingPath).pathOnly+/+"SackSynthdefs.scd", "r")).split($_); //read in synthdefs as strings

		server = s ? Server.default;

		/*synths.servers = server;
		what I want is to create new SynthDescLib called 'SACKLib' and then add the
		synthdefs in SackSynthdefs.scd to it using .add(\SACKLib). That way information
		about the special available SackSynthDefs is accessible with the symbol \SACKLib or
		SACKEngine.synths.
		I don't know how to load/compile/execute a file and pass to it the argument
		\SACKLib. Another solution is to have .add(\SACKLib) hard-coded in SackSynthDefs.scd
		but this doesn't seem to load the synthdefs on the server properly. What might be
		nice is if
		SackSynthdefs.scd contained only SynthDefs (without the .add method) which could be
		read from a file and passed as arguments along with the name \SACKLib to a function
		which adds them all with the .add(\SACKLib) method*/


		/* the following stuff all works outside the class, but if it is in the class I get
		error: SynthDef not found


		k = SynthDescLib.new(\SACKLib);
		k.addServer(s);


		a = String.readNew(File(PathName(thisProcess.nowExecutingPath).pathOnly+/+"SackSynthdefs.scd", "r")).split($_); //read in synthdefs as strings


		d = a.collect({arg item, i;
		var def = thisProcess.interpreter.interpret(item);
		("def"++i++":
		").postln;
		item.post;
		"\n".postln;
		def; //change to def.add(\SACKLib) but doesn't work properly yet
		});

		d.do( _.add(\SACKLib));


		*/


		/*synths = SynthDescLib.new(\SACKLib);
		synths.addServer(server);
*/
		server.boot;


		defs = string.collect({arg item, i;
			var def = thisProcess.interpreter.interpret(item);
			("def"++i++":
			").postln;
			item.post;
			"\n".postln;
			def;
		});

		defs.do( _.add); //change to _.add(\SACKLib) but doesn't work properly yet







		bufs = IdentityDictionary.new(know: true);
		buses = IdentityDictionary.new(know: true);

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
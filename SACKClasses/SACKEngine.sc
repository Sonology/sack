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


	init { arg s, path;



		server = s ? Server.default;


		synths = SynthDescLib.new(\SACKLib); //initialize SACKLib
		synths.addServer(server); //associate it with the server we want to use
		server.boot;


		bufs = IdentityDictionary.new(know: true);
		buses = IdentityDictionary.new(know: true);

	}


	addSynthDefs { arg path, libName;

		var lib = libName ? synths.name;
		var string, defs;

		string = String.readNew(File(path, "r")).split($_); //read in synthdefs as strings

		defs = string.collect({arg item, i;
			var def = thisProcess.interpreter.interpret(item);
			def;
		});

		defs.do( _.add(lib.asSymbol));
		^this.synths;
	}

	play { arg event;
		/*need some help with this method--
		the SACKEngine can create Buses and give access to available buses/buffers with the
		dictionaries (ie this.buses.busName or this.bufs.bufName) and can also
		give information about what
		type of controls/data a synth needs (ie this.synths.at(\synthDefName).controls), but
		should the mapping of different control sources to running synths be handled by
		other parts of the program? need to know what sort of argument the play method is
		supposed to get from SACKSequencer or SACKControl
		should the argument be a very general specification of which buses/streams go where
		and then the play method connects them all up before playing the synth?
		*/
		event.postln;
		synth = event.play;
		^synth
	}



	createControlBus { arg name, numChannels = 1;
		var nm = Bus.control(server, numChannels);
		var what = name?("bus"++this.buses.size);
		buses.put(what.asSymbol, nm);
		^nm
	}

	createAudioBus { arg name, numChannels = 1;
		var nm  = Bus.audio(server, numChannels);
		var what = name?("bus"++this.buses.size);
		buses.put(what.asSymbol, nm);
		^nm

	}



	print {
		"a".postln;
	}

	listSynths {
		var a = this.synths.synthDescs;
		a.keys.postln;
		^a.keys.asArray;
	}

	listControls { arg synth;
		var a = this.synths.synthDescs.at(synth.asSymbol).controls;
		a.postln;
		^a
	}

	needsBuf { arg synth;
		var a = this.listControls(synth.asSymbol);
		a.do({ arg ctrl;
			(ctrl.name == \buf).if({^true});});
		^false
	}




}
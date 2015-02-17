# sack

Start SACK by running:

SACKKernel.start

...

* Sequencer responsible for composing larger sections and controls for synths.

* Audio engine responsible for managing SynthDefs that will generate sound.

* Control processor responsible for handling control data from MIDI devices and/or GUI elements. 

* Kernel that handles communication between components, initialization and configuration from data files.


ADAM: SackSynthdefs.scd should be a file in your current working directory (ie: not in the same folder as the classes)
and it should contain only SuperCollider SynthDefs followed immediately by an underscore, eg:

SynthDef(\hello, { arg a = 0, b = 1;
	var sound = SinOsc.ar(a,b);
	Out.ar(0, sound);
});_

the last SynthDef should have no underscore

# sack

Start SACK by running:

SACKKernel.start

...

* Sequencer responsible for composing larger sections and controls for synths.

* Audio engine responsible for managing SynthDefs that will generate sound.

* Control processor responsible for handling control data from MIDI devices and/or GUI elements. 

* Kernel that handles communication between components, initialization and configuration from data files.


* SackSynthdefs.scd should be a file in your current working directory 
(ie: not in the same folder as the classes)
and it should contain only SuperCollider SynthDefs followed 
immediately by an underscore, eg:

"SynthDef(\hello, { arg a = 0, b = 1;
	var sound = SinOsc.ar(a,b);
	Out.ar(0, sound);
});_"

* the last SynthDef should have no underscore

* I've been using the install.sh file to make a symbolic link from my 
SuperCollider extensions directory to the SACKClasses directory so I 
can keep all the SACKcode together. 
If you want to do the same just change SC_EXT_DIR=~/.local/share/SuperCollider/Extensions 
to wherever you keep your SC extensions

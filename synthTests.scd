//synth tests




a = SACKEngine.new;
a.addSynthDefs;

a.synths

j = a.listSynths
h = a.needsBuf(\imp2)

h


or

h


h[1].cla
ControlName

j

(
i = Synth(\imp2, [
	\freq, a.createControlBus(\ctrl1).asMap,
	\filterFreq, a.createControlBus(\ctrl2).asMap
]);

j = Synth(\writeBusLag, [\bus, a.buses.ctrl1, \val, 1]);
k = Synth(\writeBus, [\bus, a.buses.ctrl2, \val, 8000]);
)



a.buses


//could put this ^ in the .play method so .play makes a synth with all the necessary controls //done as this.createControlBus(\name).asMap and also creates Synths for writing to the //busses by controllers/patterns


Pbindef(\l, \type, \set, \id, j, \args, #[\val], \val, 1, \dur, 1)

Pbindef(\l).play;

Pbindef(\l, \val, Prand([1,2,4,8], inf)*4)

Pbindef(\l, \val, Pn(Pshuf([4,8,3,3,16,32],1),inf))

Pbindef(\l, \val, Pwhite(10,500))




a.createControlBus()

j.set(\lag, 0.1)


j.set(\lag, 0)

Pbindef(\l, \dur, 0.5)

Pbindf(Pbindef(\l), \id, k, \val, Pwhite(100,8000), \dur, Pdefn(\g, 0.25)).play;

a.buses.ctrl1.index



(
var b1 = a.buses.ctrl1;
var b2 = a.buses.ctrl2;

i = Synth(\imp2, [\freq, b1.asMap, \filterFreq, b2.asMap], addAction: 'addToTail');

Pmono(\writeBus, \dur, 0.3, \bus, b1.index, \val, Pwhite(10,50,inf)).play;
Pmono(\writeBus, \dur, 0.3, \bus, b2.index, \val, Pwhite(100,8000,inf)).play;

//this isn't working really
)

SynthDescLib


NamedControl


a.synths.synthDescs.do( _.postln)

k = a.synths.synthDescs.at(\imp2).controls

k[0].defaultValue




o = PathName(thisProcess.nowExecutingPath).pathOnly+/+"SackSynthdefs.scd";

a = SACKEngine.new;

a.addSynthDefs(o);


a.listSynths.do({ arg ar; a.listControls(ar)})







ControlName

SynthDesc
k.class

k.keys



a = SACKEngine.new;

b = a.addSynthDefs("/home/a__a/PROJECTS/SACK/sack/SackSynthdefs.scd");

a.listSynths;""


f = Synth(\sine)


a.addEffect(\reverb, 20, 0);


a.fx.reverb.get()


f = Synth(\imp2, [\fxBus, 4]);

a.applyEffect(f, a.fx.reverb)
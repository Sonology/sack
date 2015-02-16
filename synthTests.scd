//synth tests

a = SACKEngine.new;
a.addSynthDefs;

(
i = Synth(\imp2, [\freq, a.createControlBus(\ctrl1).asMap, \filterFreq, a.createControlBus(\ctrl2).asMap]);

j = Synth(\writeBus, [\bus, a.buses.ctrl1, \val, 1]);
k = Synth(\writeBus, [\bus, a.buses.ctrl2, \val, 8000]);
)


//could put this ^ in the .play method so .play makes a synth with all the necessary controls //done as this.createControlBus(\name).asMap and also creates Synths for writing to the //busses by controllers/patterns




Pbindef(\l, \type, \set, \id, j, \args, #[\val], \val, 1, \dur, 1)

Pbindef(\l).play;

Pbindef(\l, \val, Prand([1,2,4,8], inf))

Pbindef(\l, \dur, 1)

Pbindf(Pbindef(\l), \id, k, \val, Pwhite(100,8000)).play;

a.buses.ctrl1.index



(
var b1 = a.buses.ctrl1;
var b2 = a.buses.ctrl2;

i = Synth(\imp2, [\freq, b1.asMap, \filterFreq, b2.asMap], addAction: 'addToTail');

Pmono(\writeBus, \dur, 0.3, \bus, b1.index, \val, Pwhite(10,50,inf)).play;
Pmono(\writeBus, \dur, 0.3, \bus, b2.index, \val, Pwhite(100,8000,inf)).play;

//this isn't working really
)
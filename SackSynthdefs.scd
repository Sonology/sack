SynthDef(\sine, {|outBus=0,amp=0.5,freq=0.5,dur=0.5,att=0.0,rel=0.0,pan=0.5|
	var attack=att.linexp(0.0,1,0.0001,dur);
	var release=rel.linexp(0.0,1,0.0001,dur).clip(0,dur-attack);
	var frequency=freq.linexp(0.0,1,50,10000);
	var panning=pan.linlin(0.0,1,-1.0,1.0);
	var env=EnvGen.kr(Env.linen(attack, dur-attack-release, release),doneAction:2);
	var signal=SinOsc.ar(frequency);
	Out.ar(outBus, Pan2.ar(signal*env*amp,pan));

});_

SynthDef(\pp, {|outBus=0,amp=0.5,freq=0.5,dur=0.5,att=0.0,rel=0.0,pan=0.5|
	var attack=att.linexp(0.0,1,0.0001,dur);
	var release=rel.linexp(0.0,1,0.0001,dur).clip(0,dur-attack);
	var frequency=freq.linexp(0.0,1,50,10000);
	var panning=pan.linlin(0.0,1,-1.0,1.0);
	var env=EnvGen.kr(Env.linen(attack, dur-attack-release, release),doneAction:2);
	var signal=Saw.ar(frequency);
	Out.ar(outBus, Pan2.ar(signal*env*amp,pan));

});_

SynthDef(\imp2, {arg freq = 10, filterFreq = 100;
	Out.ar(0, RHPF.ar(Impulse.ar(freq), filterFreq, 0.1)!2);
});_


SynthDef(\writeBus, {arg bus, val;
	Out.kr(bus, val);
});_


SynthDef(\writeBusLag, {arg bus, val, lag = 0.4;
	Out.kr(bus, Lag.kr(val, 0.4));
});_

SynthDef(\doesItNeedBuf, {arg bus, buf;
	var sound = PlayBuf.ar(1, buf);
	Out.ar(bus, sound);
});_



SynthDef(\imp, {arg freq = 10;
	Out.ar(0, Impulse.ar(freq));
});






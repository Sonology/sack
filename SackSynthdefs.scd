SynthDef(\sine, {|outBus=0,amp=0.5,freq=0.5,dur=0.5,att=0.0,rel=0.0,pan=0.5, fxBus = 20, fxAmt = 0.5|
	var attack=att.linexp(0.0,1,0.0001,dur);
	var release=rel.linexp(0.0,1,0.0001,dur).clip(0,dur-attack);
	var frequency=freq.linexp(0.0,1,50,10000);
	var panning=pan.linlin(0.0,1,-1.0,1.0);
	var env=EnvGen.kr(Env.linen(attack, dur-attack-release, release),doneAction:2);
	var signal=SinOsc.ar(frequency);

	signal = Pan2.ar(signal*env*amp,pan);



	Out.ar(fxBus, fxAmt*signal);

	Out.ar(outBus, Pan2.ar(signal));

});_

SynthDef(\pp, {|outBus=0,amp=0.5,freq=0.5,dur=0.5,att=0.0,rel=0.0,pan=0.5, fxBus = 20, fxAmt = 0.5|
	var attack=att.linexp(0.0,1,0.0001,dur);
	var release=rel.linexp(0.0,1,0.0001,dur).clip(0,dur-attack);
	var frequency=freq.linexp(0.0,1,50,10000);
	var panning=pan.linlin(0.0,1,-1.0,1.0);
	var env=EnvGen.kr(Env.linen(attack, dur-attack-release, release),doneAction:2);
	var signal=Saw.ar(frequency);

	signal = Pan2.ar(signal*env*amp, pan);

	Out.ar(fxBus, fxAmt*signal);

	Out.ar(outBus, signal);

});_




SynthDef(\spm, { arg buf, rate = 1, rateMod = 1, out = 0, dur = 1, pan = 0, startPos = 0, amp = 0.5, loop = 0, fxBus = 20, fxAmt = 0.5;
	var signal = PlayBuf.ar(1, buf, rate*rateMod, \trig.kr, startPos*BufFrames.kr(buf), loop);
	var env = EnvGen.kr(Env.new([1,1,0],[1,0]), 1, timeScale: dur, doneAction: 2);

	signal = Pan2.ar(signal*env*amp, pan);

	Out.ar(fxBus, fxAmt*signal);



	Out.ar(0, signal);
});_



SynthDef(\sps, { arg buf, rate = 1, out = 0, dur = 1, pan = 0, startPos = 0, amp = 0.5, loop = 0, fxBus = 20, fxAmt = 0.5;
	var signal = PlayBuf.ar(2, buf, rate, \trig.kr, startPos*BufFrames.kr(buf), loop);
	var env = EnvGen.kr(Env.new([1,1,0],[1,0]), 1, timeScale: dur, doneAction: 2);
	
	signal = Pan2.ar(signal*env*amp, pan);

	Out.ar(fxBus, fxAmt*signal);
	

	Out.ar(0, signal*env*amp);
});_



SynthDef(\imp2, {arg freq = 10, filterFreq = 100, fxBus = 20, fxAmt = 0.5;
	Out.ar(0, RHPF.ar(Impulse.ar(freq), filterFreq, 0.1)!2);
});_



SynthDef(\reverbz, { arg in=20, out=100, amp=0.1, mix=0.5, rmix=1, room=0.8, damp=0.8, fxBus = 20, fxAmt = 0.0;
	var snd;
	snd = In.ar(in, 2);
	snd = FreeVerb2.ar(snd[0], snd[1], rmix, room, damp);
	XOut.ar(out, mix, snd);
});_




SynthDef(\reverb, {|inBus=50, outBus=0, room=0.5, damp=0.5, amp=1, mix=1|
	var preSignal,postSignal;
	preSignal=In.ar(inBus, 2);
	postSignal=FreeVerb.ar(preSignal, 1, 0.5, 0.5);
	Out.ar(outBus, postSignal);
});_



SynthDef(\route, {|inBus=20, outBus=50, amt=0|
	var signal;
	signal=In.ar(inBus, 2);
	Out.ar(outBus, signal*amt);
});_



SynthDef(\writeBus, {arg bus, val;
	Out.kr(bus, val);
});_


SynthDef(\writeBusLag, {arg bus, val, lag = 0.4;
	Out.kr(bus, Lag.kr(val, 0.4));
});_




SynthDef(\imp, {arg freq = 10;
	Out.ar(0, Impulse.ar(freq));
});






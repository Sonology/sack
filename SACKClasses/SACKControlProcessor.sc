SACKControlProcessor {

	var <>synth, <>argumentsNames, <>argumentsValues;

	*new { arg name, names, values;
		^super.new.init(name, names, values);
	}

	init { arg synth, names, values;
		this.synth = synth;
		this.argumentsNames = names;
		this.argumentsValues = values;
	}

	createGui {
		var win, btn, count=20;
		"createGui".postln;

		this.synth.postln;

		win = Window(synth, Rect(200,200,200,300));
		win.background = Color.fromHexString("#DDDFFF");

		this.argumentsNames.do {|item, index|
			var nb, slider, label;
			label = StaticText(win,Rect(20,count,50,20));
			label.string = item;
			nb = NumberBox(win, Rect(20, count + 20, 160, 20));
		    slider = Slider(win, Rect(20, count + 60, 160, 30));
		    slider.action = {|value| nb.value = slider.value};
			count = count + 100;
		};

		btn = Button(win, Rect(20, count, 160, 50));
		btn.states = [
			["Start", Color.black, Color.white],
			["Stop", Color.white, Color.black]];
		btn.action = {|button| if(button.value == 1) {
			"Do something".postln} { "Stop doing something".postln} };

		win.front
	}

	createStartStop {|obj|
		var win, btn, count=20;
		"CreateStartStop".postln;

		{
			win = Window(synth, Rect(200,200,200,300));
			win.background = Color.fromHexString("#DDDFFF");

			btn = Button(win, Rect(20, count, 160, 50));
			btn.states = [
				["Start", Color.black, Color.white],
				["Stop", Color.white, Color.black]];
			btn.action = {|button| if(button.value == 1) {
			obj.start; "Start".postln} { obj.stop; "Stop".postln} };

			win.front

		}.defer
	}
}
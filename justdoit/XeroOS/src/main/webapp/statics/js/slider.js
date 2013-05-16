

/**************************************************************

	Script		: Slider
	Version		: 1.0
	Authors		: Samuel Birch
	Desc		: Slider bar.
	Licence		: Open Source MIT Licence

**************************************************************/

var Slider = new Class({

	options: {
		onChange: Class.empty,
		onComplete: Class.empty,
		onTick: function(pos){
			this.knob.setStyle(this.p, pos);
		},
		mode: 'horizontal',
		steps: 100,
		offset: 0
	},

	initialize: function(el, knob, options){
		this.element = $(el);
		this.knob = $(knob);
		this.setOptions(options);
		this.previousChange = -1;
		this.previousEnd = -1;
		this.step = -1;
		this.element.addEvent('click',this.click_t,true);
		for(var i = 0; i < document.getElementsByClassName("percent_span").length; i ++){
			document.getElementsByClassName("percent_span")[i].addEvent('click', this.clickedElement.bindWithEvent(this),false);
		}
		//document.getElementsByClassName("note_point_field")[0].addEvent('click', this.clickedElement.bindWithEvent(this),false);
		var mod, offset;
		switch(this.options.mode){
			case 'horizontal':
				this.z = 'x';
				this.p = 'left';
				mod = {'x': 'left', 'y': false};
				offset = 'offsetWidth';
				break;
			case 'vertical':
				this.z = 'y';
				this.p = 'top';
				mod = {'x': false, 'y': 'top'};
				offset = 'offsetHeight';
		}
		this.max = this.element[offset] - this.knob[offset] + (this.options.offset * 2) - 12;
		this.half = this.knob[offset]/2;
		this.getPos = this.element['get' + this.p.capitalize()].bind(this.element);
		this.knob.setStyle('position', 'absolute').setStyle(this.p, - this.options.offset);
		var lim = {};
		lim[this.z] = [- this.options.offset, this.max - this.options.offset];
		this.drag = new Drag.Base(this.knob, {
			limit: lim,
			modifiers: mod,
			snap: 0,
			onStart: function(){
				this.draggedKnob();
			}.bind(this),
			onDrag: function(){
				this.draggedKnob();
			}.bind(this),
			onComplete: function(){
				this.draggedKnob();
				this.end();
			}.bind(this)
		});
		if (this.options.initialize) this.options.initialize.call(this);
	},

	/*
	Property: set
		The slider will get the step you pass.

	Arguments:
		step - one integer
	*/

	set: function(step){
		this.step = step.limit(0, this.options.steps);
		this.checkStep();
		this.end();
		this.fireEvent('onTick', this.toPosition(this.step));
		return this;
	},
	
	setDefault: function(step){
		if (step > this.options.steps) step = this.options.steps;
		else if (step < 0) step = 0;
		this.step = step;
		this.checkStep();
		this.end();
		this.knob.setStyle(this.p, this.toPosition(this.step) +'px');
		return this;
	},

	scrolledElement: function(event){
		if (event.wheel < 0) this.set(this.step + 1);
		else if (event.wheel > 0) this.set(this.step - 1);
		event.stop();
	},

	clickedElement: function(event){
		var position = event.page[this.z] - this.getPos() - this.half;
		position = position.limit(-this.options.offset, this.max -this.options.offset);
		this.step = this.toStep(position);
		this.checkStep();
		this.end();
		this.fireEvent('onTick', position);
		this.set(this.step);
	},

	draggedKnob: function(){
		var num = ((this.step*1.017 + this.step*1.018)/2 - 0.1);
		//console.log(this.drag.value.now[this.z]-20);
		this.step = this.toStep(this.drag.value.now[this.z]);
		this.checkStep();
		this.setDefault(this.step);
	},

	checkStep: function(){
		if (this.previousChange != this.step){
			this.previousChange = this.step;
			this.fireEvent('onChange', this.step);
		}
	},

	end: function(){
		if (this.previousEnd !== this.step){
			this.previousEnd = this.step;
			this.fireEvent('onComplete', this.step + '');
			
		}
	},

	toStep: function(position){
		return (position + this.options.offset) / this.max * this.options.steps;
	},

	toPosition: function(step){
		return this.max * step / this.options.steps;
	}

});

Slider.implement(new Events);
Slider.implement(new Options);

/*************************************************************/


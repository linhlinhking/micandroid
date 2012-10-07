Cufon.replace('h3', {fontFamily: "MyriadPro"});
Cufon.replace('h2', {fontFamily: "MyriadPro"});

function sliderLoaded(carousel, state)
{
	$('.slider-navigation a').bind('click', function(){
		carousel.scroll($.jcarousel.intval($(this).text()));
		return false
	 });
}

function mycarousel_itemFirstInCallback(carousel, item, idx, state) {
	$('.slider-navigation a').removeClass('active');
	$('.slider-navigation a').eq(idx-1).addClass('active');
};


$(document).ready(function(){
	
	$(".slider-holder ul").jcarousel({
		wrap:"both",
		scroll:1,
		auto:4,
		initCallback: sliderLoaded,		
        itemFirstInCallback: mycarousel_itemFirstInCallback,
		// Don't autobuild next & prev buttons
		buttonNextHTML: null,
		buttonPrevHTML: null
	});
	
	  $('.field').
                focus(function() {
                    if(this.title==this.value) {
                        this.value = '';
                    }
                }).
                blur(function(){
                    if(this.value=='') {
                        this.value = this.title;
                    }
                });
                
});

function htmlLoaded()
{
	Cufon.now();
}
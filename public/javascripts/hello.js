$(document).ready(function(){
	$('.datepicker').datetimepicker({
		locale: 'cs',
		format: 'L'
	});

	$('.datetimepicker').datetimepicker({
		locale: 'cs'
	});
	
	var mandatoryAttrCount = $(".mandatoryAttr").length;
	if (mandatoryAttrCount > 0) {
		$("#main-content").append("<div class=''><span class='mandatoryAttr'></span> Takto označená pole jsou povinná!</div>");
	}
});
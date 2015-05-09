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

	$('input[required="true"],select[required="true"],textarea[required="true"]').each(function () {
		var label = $(this).closest('.form-group').find('label');
		if(label.find('span.mandatoryAttr').length == 0) {
			label.append('<span class="mandatoryAttr"></span>');
		}
	});
});
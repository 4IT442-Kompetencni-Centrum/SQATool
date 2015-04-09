$(document).ready(function(){
	$(".deleteButton").on("click", function(event){
		var elem = $(event.target);
		if (elem.hasClass("glyphicon")) {
			elem = elem.parents(".deleteButton");
		}
		var partnerId = elem.attr("data-partnerID");
		var partnerName = elem.attr("data-partnerName");
		var partnerUrl = elem.attr("data-partnerDeleteUrl");
		var modal = $("#deleteWarning");
		modal.find(".partnerName").html(partnerName);
		modal.find(".partnerID").html(partnerId);
		modal.find(".deleteConfirm").attr("href", partnerUrl);
		modal.modal();
	});
	$("#deleteCancel").on("click", function(){
		$("#deleteWarning").modal("hide");
	})
});
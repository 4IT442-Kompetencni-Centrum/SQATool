$(document).ready(function(){
	$(".deleteButton").on("click", function(event){
		var elem = $(event.target);
		if (elem.hasClass("glyphicon")) {
			elem = elem.parents(".deleteButton");
		}
		var projectId = elem.attr("data-projectID");
		var projectName = elem.attr("data-projectName");
		var projectUrl = elem.attr("data-projectDeleteUrl");
		var modal = $("#deleteWarning");
		modal.find(".projectName").html(projectName);
		modal.find(".projectID").html(projectId);
		modal.find(".deleteConfirm").attr("href", projectUrl);
		modal.modal();
	});
	$("#deleteCancel").on("click", function(){
		$("#deleteWarning").modal("hide");
	});
});
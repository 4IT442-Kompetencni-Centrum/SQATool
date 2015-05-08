$(document).ready(function(){
	$("#timeFrom").on("dp.change", function (e) {
        $('#timeTo').data("DateTimePicker").minDate(e.date);
    });
    $("#timeTo").on("dp.change", function (e) {
        $('#timeFrom').data("DateTimePicker").maxDate(e.date);
    });
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
	$(".actionButton").on("click", function(event){
		var elem = $(event.target);
		if (elem.attr("data-action") == undefined) {
			elem = elem.parents(".actionButton");
		}
		var url = elem.attr("data-action");
		$.ajax({
			  type: "POST",
			  url: url,
			  success: function(){
				  var cell = elem.parents("td");
				  if (elem.hasClass("approve")) {
					  cell.find(".approve").removeClass("btn-default");
					  cell.find(".approve").addClass("btn-success");
					  cell.find(".reject").removeClass("btn-danger");
					  cell.find(".reject").addClass("btn-default");
					  cell.find(".actionButton").off("click");
					  cell.find(".actionButton").attr("disabled", "disabled");
				  } else {
					  cell.find(".approve").addClass("btn-default");
					  cell.find(".approve").removeClass("btn-success");
					  cell.find(".reject").addClass("btn-danger");
					  cell.find(".reject").removeClass("btn-default");
				  }
				  cell.find(".actionButton").removeAttr("approve");
				  cell.find(".actionButton").removeAttr("reject");
			  },
		});
	});
});
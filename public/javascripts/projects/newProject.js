var actualItems = {};
var actualItemsManager = {};
var actualItemsMembers = {};
function resolveAjaxReponseData(data, storageArray) {
	storageArray = {};
	  for (var i=0; i < data.length; i++) {
	        var jsonData = data[i];
	        storageArray[jsonData.value] = jsonData;
	    }
	  return storageArray;
}
var typeAheadPartnerParam = {
		  displayKey: 'value',
		  highlight: true,
		  minLength: 2,
		  source: function(query, process) {
			  $.getJSON("/partneri/json/"+query, function(data){
				  actualItems = resolveAjaxReponseData(data, actualItems);
				  return process(data);
			  });
		  }
	}
var typeAheadUserParam = {
		  displayKey: 'value',
		  highlight: true,
		  minLength: 2,
		  source: function(query, process) {
			  $.getJSON("/uzivatel/json/"+query, function(data){
				  actualItemsMembers = resolveAjaxReponseData(data, actualItemsMembers);
				  return process(data);
			  });
		  }
	}

function doDeleteMember(event) {
	var elem = $(event.target);
	var row = elem.parents(".memberRow");
	var partnerInput = row.find(".memberElem.tt-input");
	var elemName = partnerInput.attr("name");
	var index = elemName.replace("memberNames[", "").replace("]", "");
	row.remove();
	var rows = $(".memberRow");
	for (var i = index; i < rows.length; i++) {
		var tmp = $(rows[i]);
		tmp.find(".memberElem.tt-input").attr("name", "memberNames["+i+"]");
		if (i < rows.length-1) {
			$("input[name='memberIds["+(i)+"]']").val($("input[name='memberIds["+(parseInt(i)+1)+"]']").val());
		} else {
			$("input[name='memberIds["+(i)+"]']").remove();
		}
	}
	updateDisabledDelete();
}

function doDeletePartner(event) {
	var elem = $(event.target);
	var row = elem.parents(".partnerRow");
	var partnerInput = row.find(".partnerElem.tt-input");
	var elemName = partnerInput.attr("name");
	var index = elemName.replace("partnerNames[", "").replace("]", "");
	row.remove();
	var rows = $(".partnerRow");
	for (var i = index; i < rows.length; i++) {
		var tmp = $(rows[i]);
		tmp.find(".partnerElem.tt-input").attr("name", "partnerNames["+i+"]");
		if (i < rows.length-1) {
			$("input[name='partnerIds["+(i)+"]']").val($("input[name='partnerIds["+(parseInt(i)+1)+"]']").val());
		} else {
			$("input[name='partnerIds["+(i)+"]']").remove();
		}
	}
	updateDisabledDelete();
}

$(document).ready(function(){
	
		$("#projectStartDate").on("dp.change", function (e) {
	        $('#projectEndDate').data("DateTimePicker").minDate(e.date);
	    });
	    $("#projectEndDate").on("dp.change", function (e) {
	        $('#projectStartDate').data("DateTimePicker").maxDate(e.date);
	    });
	
		//init project shortcut check
		$("#projectShortcut").on("keyup", function(event) {
			var query = $("#projectShortcut").val();
			$.getJSON("/projekty/volny/" + query,
				function (data) {
					var projectId = $("input[name='projectId']").val();
					if (data.isFree == 0 && projectId != data.project) {
						$("#projectShortcutValidationGlyphicon").removeClass("glyphicon-ok");
						$("#projectShortcutValidationGlyphicon").addClass("glyphicon-remove");
						var par = $("#projectShortcutValidationGlyphicon").parents(".shortcutInput");
						par.removeClass("has-success");
						par.addClass("has-error");
						$("#projectShortcutValidationMessage").removeClass("hidden");
						$("#submitProject").addClass("disabled");
					} else {
						$("#projectShortcutValidationGlyphicon").addClass("glyphicon-ok");
						$("#projectShortcutValidationGlyphicon").removeClass("glyphicon-remove");
						var par = $("#projectShortcutValidationGlyphicon").parents(".shortcutInput");
						par.addClass("has-success");
						par.removeClass("has-error");
						$("#projectShortcutValidationMessage").addClass("hidden");
						$("#submitProject").removeClass("disabled");
					}
			})
		});
		$("form").submit(function(){
			//magic trick ;-)
			//to submit form correctly, all temp fields (without index) have to be removed
			$("input[name='partnerNames[]']").remove();
			$("input[name='memberNames[]']").remove();
		});
/**************************init partner dynamic fiels**********************************/
		
		//init first autocomplete for partner
		$(".partnerRow .partnerElem").typeahead(null, typeAheadPartnerParam);
		bindSelectedAction();
		var selectedPartner = null;
		function bindSelectedAction() {
			$('.partnerRow .partnerElem.tt-input').off('typeahead:selected');
			$('.partnerRow .partnerElem.tt-input').change( function(e){
				selectedPartner = $(e.target);
				//if unknown name is written -> alert warning
				var inputValue = selectedMember.val();
				if (actualItems[inputValue] == undefined) {
					$("#unknowPartnerModal").modal({
						backdrop: 'static',
						keyboard: false
					});
				}
			});
			$("#unknowPartnerModalCancel").click(function(){
				selectedPartner.val("");
				$("#unknowPartnerModal").modal('hide');
			});
			$('.partnerRow .partnerElem.tt-input').on('typeahead:selected', function(evt, item) {
				var hiddenArea = $("#dbIds");
				var id = actualItems[item.value].id;
				var defaultElem = $(evt.target).parents(".partnerRow");
				var order = 0;
				var elems = $('.partnerRow');
				for (var i = 0; i < elems.length; i++) {
					if ($(elems[i])[0] == defaultElem[0]) {
						order = i;
						break;
					}
				}
				var storageInput = hiddenArea.find("input[name='partnerIds["+order+"]']");
				var addedElem = false
				if (storageInput.length == 0) {
					hiddenArea.append("<input type='hidden' name='partnerIds["+order+"]'>");
					storageInput = hiddenArea.find("input[name='partnerIds["+order+"]']");
					addedElem = true;
				}
				storageInput.val(id);
				if (addedElem || hiddenArea.find("input[name='partnerIds["+(parseInt(order)+1)+"]']").length == 0) {
					var defaultPartnerRow = $(".defaultPartnerRowWrap");
					var newPartnerRow = defaultPartnerRow.clone();
					newPartnerRow.find(".partnerElem").val("");
					newPartnerRow.find(".defaultPartnerRow").addClass("partnerRow").removeClass("defaultPartnerRow");
					$($(".newPartnerPlace")[0]).replaceWith(newPartnerRow.html());
					var rows = $(".partnerRow");
					var newRow = $(rows[rows.length-1]);
					newRow.find(".partnerElem").typeahead(null, typeAheadPartnerParam);
					newRow.find(".deletePartner").on("click", function(event) {
						doDeletePartner(event)
					});
					bindSelectedAction();
				}
				updateDisabledDelete();
			});
		}
/**************************init member dynamic fiels**********************************/
		
		//init first autocomplete for project member
		$("#projectManager").typeahead(null, typeAheadUserParam);
		$('#projectManager.tt-input').on('typeahead:selected', function(evt, item) {
			var hiddenArea = $("#dbIds");
			var id = actualItemsMembers[item.value].id;
			$("#managerId").val(id);
		});
		$('#projectManager.tt-input').change( function(e){
			selectedMember = $(e.target);
			//if unknown name is written -> alert warning
			var inputValue = selectedMember.val();
			if (actualItemsMembers[inputValue] == undefined) {
				$("#unknowUserModal").modal({
					backdrop: 'static',
					keyboard: false
				});
			}
		});
		$("#unknowUserModalCancel").click(function(){
			selectedMember.val("");
			$("#unknowUserModal").modal('hide');
		});
		//init first autocomplete for member
		$(".memberRow .memberElem").typeahead(null, typeAheadUserParam);
		bindSelectedActionUser();
		var selectedMember = null;
		function bindSelectedActionUser() {
			$('.memberRow .memberElem.tt-input').off('typeahead:selected');
			$('.memberRow .memberElem.tt-input').change( function(e){
				selectedMember = $(e.target);
				//if unknown name is written -> alert warning
				var inputValue = selectedMember.val();
				if (actualItemsMembers[inputValue] == undefined) {
					$("#unknowUserModal").modal({
						backdrop: 'static',
						keyboard: false
					});
				}
			});
			$("#unknowMemberModalCancel").click(function(){
				selectedMember.val("");
				$("#unknowPartnerModal").modal('hide');
			});
			$('.memberRow .memberElem.tt-input').on('typeahead:selected', function(evt, item) {
				var hiddenArea = $("#dbIds");
				var id = actualItemsMembers[item.value].id;
				var defaultElem = $(evt.target).parents(".memberRow");
				var order = 0;
				var elems = $('.memberRow');
				for (var i = 0; i < elems.length; i++) {
					if ($(elems[i])[0] == defaultElem[0]) {
						order = i;
						break;
					}
				}
				var storageInput = hiddenArea.find("input[name='memberIds["+order+"]']");
				var addedElem = false
				if (storageInput.length == 0) {
					hiddenArea.append("<input type='hidden' name='memberIds["+order+"]'>");
					storageInput = hiddenArea.find("input[name='memberIds["+order+"]']");
					addedElem = true;
				}
				storageInput.val(id);
				if (addedElem || hiddenArea.find("input[name='memberIds["+(parseInt(order)+1)+"]']").length == 0) {
					var defaultMemberRow = $(".defaultMemberRowWrap");
					var newMemberRow = defaultMemberRow.clone();
					newMemberRow.find(".memberElem").val("");
					newMemberRow.find(".defaultMemberRow").addClass("memberRow").removeClass("defaultMemberRow");
					$($(".newMemberPlace")[0]).replaceWith(newMemberRow.html());
					var rows = $(".memberRow");
					var newRow = $(rows[rows.length-1]);
					newRow.find(".memberElem").typeahead(null, typeAheadUserParam);
					newRow.find(".deleteMember").on("click", function(event) {
						doDeleteMember(event)
					});
					bindSelectedActionUser();
				} 
				updateDisabledDelete();
			});
		}
/********************************delete buttons***************************************/
		$(".deletePartner").click(function(event){
			doDeletePartner(event);
		});
		
		$(".deleteMember").click(function(event){
			doDeleteMember(event);
		});
		updateDisabledDelete();
});

function updateDisabledDelete() {
	var memberDelete = $(".deleteMember");
	for (var i = 0; i < memberDelete.length; i++) {
		$(memberDelete[i]).removeClass("disabled");
	}
	if (memberDelete.length == 2) {
		for (var i = 0; i < memberDelete.length; i ++) {
			$(memberDelete[i]).addClass("disabled");
		}
	}
	var partnerDelete = $(".deletePartner");
	for (var i = 0; i < partnerDelete.length; i++) {
		$(partnerDelete[i]).removeClass("disabled");
	}
	if (partnerDelete.length == 2) {
		for (var i = 0; i < partnerDelete.length; i ++) {
			$(partnerDelete[i]).addClass("disabled");
		}
	}
}
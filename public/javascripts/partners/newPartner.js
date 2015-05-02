function isLast(elem) {
	var elems = $(".contactPersonRow");
	if ($(elems[elems.length-2])[0] == elem[0]) return true;
	return false;
}

function updateName(event) {
	var parentContainer = $(event.target).parents(".contactPersonRow");
	parentContainer.find(".firstname").html($(event.target).val());
}

function updateSurname(event) {
	var parentContainer = $(event.target).parents(".contactPersonRow");
	parentContainer.find(".lastname").html($(event.target).val());
}

function updateContactPersonIndex(oldIndex, newIndex, name, row) {
	row.find("input[name='contactPersons["+oldIndex+"]."+name+"']")
				.attr("name", "contactPersons["+newIndex+"]."+name);
}

function deleteContactPerson(event) {
	var button = $(event.target);
	var row = button.parents(".contactPersonRow");
	var inputField = row.find(".contactPersonName");
	var inputName = inputField.attr("name");
	var index = parseInt(inputName.replace("contactPersons[", "").replace("].firstName", ""));
	row.remove();
	var rows = $(".contactPersonRow");
	for (var i = index; i < rows.length; i++) {
		var r = $(rows[i]);
		updateContactPersonIndex(parseInt(i)+1, i, "firstName", r);
		updateContactPersonIndex(parseInt(i)+1, i, "lastName", r);
		updateContactPersonIndex(parseInt(i)+1, i, "phoneNumber", r);
		updateContactPersonIndex(parseInt(i)+1, i, "email", r);
		updateContactPersonIndex(parseInt(i)+1, i, "version", r);
		updateContactPersonIndex(parseInt(i)+1, i, "contactPersonId", r);
	}
}

function bindActions() {
	$(".contactPersonRow").find("input").off("keypress");
	$(".contactPersonName").off("keyup");
	$(".deleteContactPerson").off("click");
	$(".contactPersonSurname").off("keyup");
	$(".contactPersonRow").find("input").keypress(function(event){
		if (isLast($(event.target).parents(".contactPersonRow"))) {
			var newIndex = $(".contactPersonRow").length-1;
			var newElemHtml = $("#defaultContactPersonRow").html();
			newElemHtml = newElemHtml.replace(/generatedAttr/g, "contactPersons["+newIndex+"].");
			$($(".newContactPersonPlace")[0]).replaceWith(newElemHtml);
			bindActions();
		} 
	});
	$(".contactPersonName").keyup(function(event) {
		updateName(event);
	});
	$(".contactPersonSurname").keyup(function(event) {
		updateSurname(event);
	});
	$(".deleteContactPerson").on("click", function(event) {
		deleteContactPerson(event);
	});

}

$(document).ready(function(){
	bindActions();
});
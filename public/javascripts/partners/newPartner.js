function isLast(elem) {
	var elems = $(".contactPersonRow");
	if ($(elems[elems.length-2])[0] == elem[0]) return true;
	return false;
}

function updateName(event) {
	var parentContainer = $(event.target).parents(".contactPersonRow");
	console.log(parentContainer.find(".firstname"));
	parentContainer.find(".firstname").html($(event.target).val());
}

function updateSurname(event) {
	var parentContainer = $(event.target).parents(".contactPersonRow");
	console.log(parentContainer.find(".lastname"));
	parentContainer.find(".lastname").html($(event.target).val());
}

function bindActions() {
	$(".contactPersonRow").find("input").off("keypress");
	$(".contactPersonName").off("keyup");
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

}

$(document).ready(function(){
	bindActions();
});
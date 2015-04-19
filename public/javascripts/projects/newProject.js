$(document).ready(function(){
		$("form").submit(function(){
			//magic trick ;-)
			//to submit form correctly, all temp fields (without index) have to be removed
			$("input[name='partnerNames[]']").remove();
		});
		var actualItems = {};
		//init first autocomplete for partner
		$(".partnerRow .partnerElem").typeahead(null, {
			  displayKey: 'value',
			  highlight: true,
			  minLength: 2,
			  source: function(query, process) {
				  $.getJSON("/partneri/json/"+query, function(data){
					  actualItems = {};
					  for (var i=0; i < data.length; i++) {
					        var jsonData = data[i];
					        actualItems[jsonData.value] = jsonData;
					    }
					  return process(data);
				  });
			  }
		});
		bindSelectedAction();
		var selectedPartner = null;
		function bindSelectedAction() {
			$('.partnerRow .partnerElem.tt-input').off('typeahead:selected');
			$('.partnerRow .partnerElem.tt-input').change( function(e){
				selectedPartner = $(e.target);
				//if unknown name is written -> alert warning
				$("#unknowPartnerModal").modal({
					backdrop: 'static',
					keyboard: false
				});
			});
			$("#unknowPartnerModalCancel").click(function(){
				selectedPartner.val("");
				$("#unknowPartnerModal").modal('hide');
			});
			$('.partnerRow .partnerElem.tt-input').on('typeahead:selected', function(evt, item) {
				var hiddenArea = $("#projectPartnersIds");
				var id = actualItems[item.value].id;
				var defaultElem = $(event.target).parents(".partnerRow");
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
				if (addedElem) {
					var defaultPartnerRow = $(".defaultPartnerRowWrap");
					var newPartnerRow = defaultPartnerRow.clone();
					newPartnerRow.find(".partnerElem").val("");
					newPartnerRow.find(".defaultPartnerRow").addClass("partnerRow").removeClass("defaultPartnerRow");
					$($(".newPartnerPlace")[0]).replaceWith(newPartnerRow.html());
					var rows = $(".partnerRow");
					var newRow = $(rows[rows.length-1]);
					newRow.find(".partnerElem").typeahead(null, {
						  displayKey: 'value',
						  highlight: true,
						  minLength: 2,
						  source: function(query, process) {
							  $.getJSON("/partneri/json/"+query, function(data){
								  actualItems = {};
								  for (var i=0; i < data.length; i++) {
								        var jsonData = data[i];
								        actualItems[jsonData.value] = jsonData;
								    }
								  return process(data);
							  });
						  }
					});
					
					bindSelectedAction();
				}
			});
		}
});
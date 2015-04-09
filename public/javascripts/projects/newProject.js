$(document).ready(function(){
		var actualItems = {};
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
				hiddenArea.append("<input type='hidden' name='partnerIds[]' value='"+id+"'>");
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
			});
		}
});
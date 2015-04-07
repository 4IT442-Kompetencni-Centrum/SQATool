$(document).ready(function(){
		var actualItems = {};
		$("#projectPartner").typeahead(null, {
			  displayKey: 'value',
			  highlight: true,
			  minLength: 2,
			  source: function(query, process) {
				  $.getJSON("/partneri/json/"+query, function(data){
					  actualItems = {};
					  for (var i=0; i < data.length; i++) {
						  console.log(data[i]);
					        var jsonData = data[i];
					        console.log(jsonData.value+" "+jsonData.id);
					        actualItems[jsonData.value] = jsonData;
					    }
					  return process(data);
				  });
			  }
		});
		$('#projectPartner').on('typeahead:selected', function(evt, item) {
			var hiddenArea = $("#projectPartnersIds");
			var id = actualItems[item.value].id;
			hiddenArea.append("<input type='hidden' name='partnerIds[]' value='"+id+"'>");
		});
});
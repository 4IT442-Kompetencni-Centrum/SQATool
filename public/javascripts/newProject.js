$(document).ready(function(){
	var partners = new Bloodhound({
		  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
		  queryTokenizer: Bloodhound.tokenizers.whitespace,
		  remote: '/partneri/json/%QUERY'
		});
		 
	partners.initialize();
		 
		$("#projectPartner").typeahead(null, {
			  displayKey: 'value',
			  source: partners.ttAdapter()
		});
});
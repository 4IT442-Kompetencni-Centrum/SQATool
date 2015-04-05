var datepicker = $.fn.datepicker.noConflict(); // return $.fn.datepicker to previously assigned value
$.fn.bootstrapDP = datepicker;                 // give $().bootstrapDP the bootstrap-datepicker functionality
$(document).ready(function(){
	$(".datepicker").bootstrapDP({
	    language: 'cs',
	    autoclose: true,
	    format: 'dd.mm.yyyy'
	});
});
if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}
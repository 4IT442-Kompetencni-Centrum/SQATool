$(document).ready(function () {
    var button = $('<a>');
    button.attr('href', '/reset');
    button.text('Reset databáze');
    button.addClass('btn');
    button.addClass('btn-danger');
    button.css('position', 'fixed');
    button.css('bottom', 50);
    button.css('right', 0);
    $('body').append(button);
});
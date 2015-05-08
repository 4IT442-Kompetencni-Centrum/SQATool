$(document).ready(function(){
    $('body').on('click','.remove-button',function(e){
        e.preventDefault(e);
        $(this).closest('.form-group').remove();
        $('form').submit();

    }).on('click','.edit-button', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).attr("type","submit")
            .removeClass('edit-button')
            .find('span')
            .removeClass('glyphicon-cog')
            .addClass('glyphicon-floppy-disk');
        $(this).closest('.enumeration-item').find('input[type="text"]').removeAttr('readonly');
    }).on('click','.add-button',function(){
        var clone = $('.enumeration-item-prototype').find('.enumeration-item').clone();
        var id = $('.enumeration-items').find('.enumeration-item').last().attr("id");
        if(typeof id === 'undefined') {
            id = 0;
        } else {
            id = (parseInt(id) + 1);
        }
        clone.attr("id",id);
        clone.html(clone.html().replace(/__i__/g,id));
        clone.find('input[type="text"]').removeAttr('readonly');
        clone.find('input[type="hidden"]').val('');
        clone.find('.edit-button').find('span').removeClass('glyphicon-cog').addClass('glyphicon-floppy-disk');
        clone.find('.edit-button').attr("type","submit").removeClass('edit-button');

        $('.enumeration-items').append(clone);
    });
});
(function ($) {
    "use strict";
    /*==================================================================
    [ Focus Contact2 ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })
    });
})(jQuery);

function loginFormSubmit(){
    if(fieldsValidate()){
        var formData = $('#loginForm').serialize();
        $.ajax({
            url: "/login",
            data: formData,
            type: "POST",
            success: function (result) {
                alert(result);
                $('#errMessage').html(""+result);
                $('#errMessage').css("display", "block");
            },
            error: function (result) {
                alert("---"+result);
                $('#errMessage').html("failed");
                $('#errMessage').css("display", "block");
            }
        })
    }
}

function fieldsValidate(){
    var input = $('.validate-input .input100');
    var check = true;
    for(var i=0; i<input.length; i++) {
        if(validate(input[i]) == false){
            showValidate(input[i]);
            check=false;
        }
    }
    return check;
}

$('.validate-form .input100').each(function(){
    $(this).focus(function(){
        hideValidate(this);
    });
});

function validate (input) {
    /*if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {*/
    if($(input).attr('type') == 'text' || $(input).attr('name') == 'loginid') {
        /*if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            return false;
        }*/
        if($(input).val().trim().match(/^[A-Za-z0-9]+$/) == null){
            return false;
        }
    }
    else {
        if($(input).val().trim() == ''){
            return false;
        }
    }
}

function showValidate(input) {
    var thisAlert = $(input).parent();
    $(thisAlert).addClass('alert-validate');
}

function hideValidate(input) {
    var thisAlert = $(input).parent();
    $(thisAlert).removeClass('alert-validate');
}
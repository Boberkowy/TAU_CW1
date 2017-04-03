$(document).ready(function(){

    $("#loginButton").click(function() {
        $("#page").css("display", "none");
        $("#loginForm").show();
    });

        $(".cancelButton").click(function () {
             $("#page").css("display", "block");
            if($("#loginForm").is(':visible')) {
                $("#loginForm").hide(500);
            }
            else{
                $("#registerForm").hide(500);
            }
        });

        $("#registerButton").click(function(){
        $("#page").css("display", "none");
        $("#registerForm").show(500);
        });
    });




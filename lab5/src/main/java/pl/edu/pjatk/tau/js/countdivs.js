$(document).ready(function(){

    var numItems = $('#notesList').length;
    if(numItems < 5)
        $('#load').css("display","none");
    console.log(numItems);
});

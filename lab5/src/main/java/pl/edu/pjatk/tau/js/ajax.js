$(document).ready(function(){
    $("#load").click(function(){
        loadMore();
    });
});

function loadMore()
{
    var val = document.getElementById("howManyResults").value;
    $.ajax({
        type: 'post',
        url: 'loadMore.php',
        data: {
            result:val
        },
        success: function (response) {
            var content = document.getElementById("notes");
            content.innerHTML = content.innerHTML+response;

            document.getElementById("howManyResults").value = Number(val)+2;
        }
    });
}
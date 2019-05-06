$(function () {
    $('#searchbox').keypress(function (e) {
        var key = e.which; //e.which是按键的值
        if (key == 13) {
            var q = $(this).val();
            if (q && q != '') {
                window.location.href = '/search/' + q;
            }
        }
    });
});

function search() {
    var q = $('#searchbox').val();
    if (q && q != '') {
        window.location.href = '/search/' + q;
    }
}
$(document).ready(function () {

    $("body").on("click", ".navigation__sub > a", function (a) {
        a.preventDefault(),
            $(this).parent().toggleClass("navigation__sub--toggled"),
            $(this).next("ul").slideToggle(250)
    });

    // $("body").on("click", ".navigation__sub > ul > li > a", function (a) {
    //     a.preventDefault(),
    //     $(".navigation__sub--toggled > a").attr('style', 'color: #f17000');
    //     $(this).attr('style', 'color: #f17000');
    // });
    //
    // $("body").on("click", ".navigation__active > a", function (a) {
    //     a.preventDefault(),
    //     $(this).attr('style', 'color: #f17000');
    // });

    $(function () {
        $('.date').datepicker();
        $('.start_date').datetimepicker();
        $('.end_date').datetimepicker();
    });

});

function openNav() {
    $(".openbtn").hide();
    $(".closebtn").show();
    $(".sidebar").toggleClass("sidebar--toggled");
}

function closeNav() {
    $(".closebtn").hide();
    $(".openbtn").show();
    $(".sidebar").removeClass("sidebar--toggled");
}

function parseDate(str) {
    var mdy = str.split('/');
    return new Date(mdy[2], mdy[0] - 1, mdy[1]);
}

function parseSeconds(str) {
    var seconds = str.split(':');
    return (seconds[0] * 1000 * 60 * 60 + seconds[1] * 1000 * 60);
}

function daydiff(first, second, startRegistration, endRegistration) {
    return ((second - first) + (endRegistration - startRegistration)) / (1000 * 60 * 60 * 24);
}

function registration() {
    var startRegistrationDate = $(".start_date").value;
    var endRegistrationDate = $(".end_date").value;
    if(startRegistrationDate != null && endRegistrationDate != null){
        alert(startRegistrationDate);
    }
    // alert(endRegistrationDate);
    // var result = daydiff(parseDate(startRegistrationDate[1]), parseDate(endRegistrationDate[1]), parseSeconds(startRegistrationDate[0]), parseSeconds(endRegistrationDate[0]));
    // $(".working_day").text(result);
}




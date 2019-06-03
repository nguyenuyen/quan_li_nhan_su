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

    $( ".flatpickr" ).flatpickr({
        enableTime: true,
        noCalendar: true,
        dateFormat: "H:i",
        time_24hr: true

    });

    $(".flatpickr_act").flatpickr({
        enableTime: true,
        dateFormat: "Y-m-d H:i",
    });

    $(".date").flatpickr({
        mode: "multiple",
        dateFormat: "Y-m-d"
    });

    $(function () {
        var searchStartDate = $("#searchStartDate").val();
        var date = new Date(), y = date.getFullYear(), m = date.getMonth();
        var firstDay = new Date(y, m, 1);
        if(searchStartDate != ""){
            $("#startDate").val(searchStartDate);
        }else {
            $("#startDate").val(convertDate(firstDay.toLocaleDateString()));
        }
    });

    $(function () {
        var searchEndDate = $("#searchEndDate").val();
        var date = new Date();
        if(searchEndDate != ""){
            $("#endDate").val(searchEndDate);
        }else {
            $("#endDate").val(convertDate(date.toLocaleDateString()));
        }
    });

    var convertDate = function(usDate) {
        var dateParts = usDate.split(/(\d{1,2})[\/ -](\d{1,2})[\/ -](\d{4})/);
        return dateParts[3] + "-" + dateParts[1] + "-" + dateParts[2];
    }

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







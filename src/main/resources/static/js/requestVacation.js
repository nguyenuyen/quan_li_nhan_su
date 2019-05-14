function requestVacation(action) {
    var startDate = $("#start_date").val();
    var endDate = $("#end_date").val();
    var workingDate = $("#working_day").val();
    var approver = $("#approver").val();
    var reason = $("#reason").val();

    if (startDate == "" || endDate == "" || workingDate == "" || approver == "" || reason == "") {
        alert("ban can nhap day du cac truong");
        return false;
    }
    var jsonText = jQuery.ajax({
        type: "GET",
        url: "/"+action,
        data: "act=" + action + "&startDate=" + startDate + "&endDate=" + endDate + "&workingDate=" + workingDate + "&approver=" + approver + "&reason=" + reason,
        async: false
    }).responseText;
    var json = eval("(" + jsonText + ")");
    if (json.result == "OK") {
        alert("yeu cau thanh cong !");
    } else {
        alert("yeu cau that bai ");
    }
};


function search() {
    $("#form-request").submit();
}

function downloadListRequest() {
    var search = $("#search").val();
    jQuery.ajax({
        type: "POST",
        url: "/download",
        data: "act=download",
        async: false,
        success: function (response) {
            alert("thanh cong");
        }
    })

};



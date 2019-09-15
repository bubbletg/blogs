$(function () {
    $.get("header.html",function (data) {
        $("#header").html(data);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
    $.get("简历2019秋招.html",function (data) {
        $("#resume").html(data);
    });
});
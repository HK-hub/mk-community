$(function() {
    var labelList = [];
    var color = ['#6495ED', '#9edD48', '#cca4e3', '#ffc773', '#70f3ff'];
    var appendStr = "<div style='float: inherit;'>";
    var appendStr2 = "";

    $.each(labelList, function(index, element) {
        var num = parseInt(Math.random() * 5);
        appendStr += '<span id="' + element.labelId + '" class="labelSpan labelS" style="border-color:' + color[num] + ';">' + element.labelNanme + ' </span>';

    });
    appendStr += '</div><div id="diyLabelDiv"></div>';
    $("#SelectLabelDiv").prepend(appendStr);

    $(".labelSpan").on("click", function() {
        var rgb = $(this).css("borderColor");
        rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
        rgb = "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]) + "30";
        if ($(this).css("backgroundColor") != null && $(this).css("backgroundColor") != "rgba(0, 0, 0, 0)") {
            $(this).css("backgroundColor", "rgba(0, 0, 0, 0)");
            var inputId = "label" + $(this).attr("id");
            $("#" + inputId).remove();
        } else {
            $(this).css("backgroundColor", rgb);
            appendStr2 = '<input id="label' + $(this).attr("id") + '" name="labelID" type="hidden" class="form-control" value="' + $(this).attr("id") + '">';
            $("#SelectLabelDiv").prepend(appendStr2);
        }
    });

});

function hex(x) {
    return ("0" + parseInt(x).toString(16)).slice(-2);
}
var labelId = 0;

function submitDiyLabel() {
    //alert(String.fromCharCode((65+labelId)));
    var labelName = $("#newLabel").val();

    $("#newLabel").val("");
    var str = '<span id="' + String.fromCharCode((65 + labelId)) + '" class="diyLabelSpan labelS" style="border-color:#599bff;background-color:#599bff30">' + labelName + ' </span>';
    $("#diyLabelDiv").append(str);
    appendStr2 = '<input id="diyLabel' + String.fromCharCode((65 + labelId)) + '" type="hidden" name="tags" class="form-control" value="' + labelName + '">';
    $("#diyLabelDiv").prepend(appendStr2);

    $(".diyLabelSpan").on("click", function() {
        var rgb = $(this).css("borderColor");
        rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
        rgb = "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]) + "30";

        if ($(this).css("backgroundColor") != null && $(this).css("backgroundColor") != "rgba(0, 0, 0, 0)") {

            $(this).css("backgroundColor", "rgba(0, 0, 0, 0)");

            var inputId = "diyLabel" + $(this).attr("id");

            $("#" + inputId).remove();

        } else {
            $(this).css("backgroundColor", rgb);
            appendStr2 = '<input name="tags" id="diyLabel' + $(this).attr("id") + '" type="hidden" name="tags" class="form-control" value="' + $(this).html() + '">';
            $("#diyLabelDiv").prepend(appendStr2);
        }
    });
    labelId++;
}






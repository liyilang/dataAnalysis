/**
 * Created by yilangli on 11/10/2015.
 */

Chart.defaults.global.pointHitDetectionRadius = 1;
Chart.defaults.global.customTooltips = function(tooltip) {

    var tooltipEl = $('#chartjs-tooltip');

    if (!tooltip) {
        tooltipEl.css({
            opacity: 0
        });
        return;
    }

    tooltipEl.removeClass('above below');
    tooltipEl.addClass(tooltip.yAlign);

    var innerHtml = '';
    for (var i = tooltip.labels.length - 1; i >= 0; i--) {
        innerHtml += [
            '<div class="chartjs-tooltip-section">',
            '	<span class="chartjs-tooltip-key" style="background-color:' + tooltip.legendColors[i].fill + '"></span>',
            '	<span class="chartjs-tooltip-value">' + tooltip.labels[i] + '</span>',
            '</div>'
        ].join('');
    }
    tooltipEl.html(innerHtml);

    tooltipEl.css({
        opacity: 1,
        left: tooltip.chart.canvas.offsetLeft + tooltip.x + 'px',
        top: tooltip.chart.canvas.offsetTop + tooltip.y + 'px',
        fontFamily: tooltip.fontFamily,
        fontSize: tooltip.fontSize,
        fontStyle: tooltip.fontStyle,
    });
};
var randomScalingFactor = function() {
    return Math.round(Math.random() * 100);
};
var lineChartData = {
    labels: ["January", "February", "March", "April", "May", "June", "July"],
    datasets: [{
        label: "My First dataset",
        fillColor: "rgba(220,220,220,0.2)",
        strokeColor: "rgba(220,220,220,1)",
        pointColor: "rgba(220,220,220,1)",
        pointStrokeColor: "#fff",
        pointHighlightFill: "#fff",
        pointHighlightStroke: "rgba(220,220,220,1)",
        data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
    }, {
        label: "My Second dataset",
        fillColor: "rgba(151,187,205,0.2)",
        strokeColor: "rgba(151,187,205,1)",
        pointColor: "rgba(151,187,205,1)",
        pointStrokeColor: "#fff",
        pointHighlightFill: "#fff",
        pointHighlightStroke: "rgba(151,187,205,1)",
        data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
    }]
};

/*window.onload = function() {
	
	console.log(lineChartData);
    var ctx1 = document.getElementById("chart1").getContext("2d");
    window.myLine = new Chart(ctx1).Line(lineChartData, {
        showScale: false,
        pointDot : true,
        responsive: true
    });

    var ctx2 = document.getElementById("chart2").getContext("2d");
    window.myLine = new Chart(ctx2).Line(lineChartData, {
        responsive: true
    });
};*/


function dateValid(){
    var start=new Date($("#starttime").val()).getTime();
    var end=new Date($("#endtime").val()).getTime();
    if((start>end)||start==null||end==null){
        $("#endtime").css("border-color","red");
        $("#submit").attr("disabled",true);
    }else{
        $("#endtime").css("border-color","#d5dbdb")
        $("#submit").attr("disabled",false);

    }
};


function Plot(data){

    var ctx1 = document.getElementById("chart1").getContext("2d");
    window.myLine = new Chart(ctx1).Line(data, {
        showScale: false,
        pointDot : true,
        responsive: true
    });
    $("#chart2").remove();
    $("#canvas-holder2").append('<canvas id="chart2" width="100%" height="50%" />');
    var ctx2 = document.getElementById("chart2").getContext("2d");
    window.myLine = new Chart(ctx2).Line(data, {
        responsive: true
    });

}

function draw(){
	var asset_id=$("#asset").val();
    var param=new Array();
    $("#param :selected").each(function () {
            param.push(this.value);
        }
    );
    var params=param.toString();
    var start=$("#starttime").val();
    var end=$("#endtime").val();
    $.post("asset_getData.action",{asset_id:asset_id,params:params,start:start,end:end},Plot,"json");
}
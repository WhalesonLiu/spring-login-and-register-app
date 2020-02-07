(function(window){

    function drawChart(chartId, title, xCondition,yCondition) {

        var chartBox = echarts.init(document.getElementById(chartId));

        var option = {
            title: {
                text: title
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['新增确诊病例(例)-全国', '新增确诊病例(例)-湖北', '新增重症病例(例)-全国', '新增重症病例(例)-湖北',
                    '新增死亡病例(例)-全国','新增死亡病例(例)-湖北','新增治愈出院病例(例)-全国','新增治愈出院病例(例)-湖北',
                    '新增疑似病例(例)-全国','新增疑似病例(例)-湖北','累计确诊病例(例)-全国','重症病例-全国','累计死亡病例-全国',
                    '累计治愈出院病例-全国','疑似病例-全国','密切接触者(人)-全国','当日解除医学观察(人)-全国','正在接受医学观察(人)-全国',
                    '香港特别行政区通报确诊病例(例)','澳门特别行政区通报确诊病例(例)','台湾地区通报确诊病例(例)']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: null
            },
            yAxis: {
                type: 'value'
            },
            series: null
        };
        //x坐标轴的数据
        if( xCondition == null){
            xCondition = ['date']
        }
        //获取x轴的数据
        var xCon = {
            conditions : xCondition
        }
        $.ajax({
            async : false,
            type : 'GET',
            url : '/2019-nCoV/list',
            contentType: "application/json;charset=utf-8",
            data : xCon,//400
            success : function(result) {
                if( result.code == '206'){
                    option.xAxis.data = result.content[0].xAxis;
                }
            },
            dataType : 'json'
        });
        //获取y轴的数据
        var yCon = {
            conditions : yCondition
        }
        $.ajax({
            async : false,
            type : 'GET',
            url : '/2019-nCoV/list',
            contentType: "application/json;charset=utf-8",
            data : yCon,//400
            success : function(result) {
                if( result.code == '206'){
                    option.series = result.content
                }
            },
            dataType : 'json'
        });
        chartBox.setOption(option);
    }
    window.drawChart = drawChart;

})(window);
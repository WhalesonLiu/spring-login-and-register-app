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

    //增加一行
    function addFunctionLine (type, areaId,dealLineId){

        if(type =='' || type == null){
            return;
        }

        var areaObj = $(areaId);
        var areaIdLength = areaObj.children('li').length;
        if(areaIdLength >= 1){
            $(dealLineId).removeClass('d-none');
        }

        var elementId = ++areaIdLength;
        if(type == 'commodity'){

            var liObj = $('<li class="form-group row"></li>');

            var commodityNameLbObj = $('<label for="commodity-name-'+ elementId +'" class="col-sm-2 col-form-label" >商品'+ elementId +'名称</label>');

            liObj.append(commodityNameLbObj);

            var commodityNameIptObj = $('<input type="text"  class="form-control" id="commodity-name-'+elementId+'" autocapitalize="off">');


            var divCol3Obj= $('<div class="col-sm-3"></div>').append(commodityNameIptObj);

            liObj.append(divCol3Obj);

            var commodityNumLbObj = $('<label for="commodity-num-'+ elementId +'" class="offset-sm-1 col-sm-1 col-form-label" >数量</label>');

            liObj.append(commodityNumLbObj);
            var commodityNumIptObj = $('<input type="number"  class="form-control" id="commodity-num-'+ elementId +'" autocapitalize="off" min="0" >');

            var divCol1Obj = $('<div class="col-sm-1"></div>').append(commodityNumIptObj);
            liObj.append(divCol1Obj);


        }else if( type == 'user'){
            var liObj = $('<li  style="border: 1px dashed #ced4da;margin-top: 10px;"></li>');

            var deliveryNameDiv = $('<div class="form-group row" style="margin-top: 10px;"></div>');
            var deliveryNameLb = $('<label for="deliveryName'+ elementId +'" class=" col-sm-2 col-form-label" >&nbsp;&nbsp;&nbsp;&nbsp;收货人姓名</label>');
            deliveryNameDiv.append(deliveryNameLb);
            var colsm3Div = $('<div class="col-sm-3"></div>');
            var deliveryNameIptObj = $('<input type="text"  class="form-control" id="deliveryName'+ elementId +'" autocomplete="off">');
            colsm3Div.append(deliveryNameIptObj);
            deliveryNameDiv.append(colsm3Div);
            var phoneNumberLbl =$('<label for="phoneNumber'+ elementId +'" class="offset-sm-1 col-sm-2 col-form-label" >&nbsp;&nbsp;&nbsp;&nbsp;收货人手机号</label>');
            deliveryNameDiv.append(phoneNumberLbl);
            var colsm3Div2Obj = $('<div class="col-sm-3"></div>');
            var phoneNumberInpt = $('<input type="text"  class="form-control" id="phoneNumber'+ elementId +'" autocomplete="off">');
            colsm3Div2Obj.append(phoneNumberInpt);
            deliveryNameDiv.append(colsm3Div2Obj);
            liObj.append(deliveryNameDiv);

            var deliveryAddressDivObj = $('<div class="form-group row"></div>');
            var deliveryAddressLblObj = $('<label for="deliveryAddress'+ elementId +'" class=" col-sm-2 col-form-label" >&nbsp;&nbsp;&nbsp;&nbsp;收货人地址</label>')
            deliveryAddressDivObj.append(deliveryAddressLblObj);
            var deliveryAddressDivsm8Obj = $('<div class="col-sm-8"></div>');
            var deliveryAddressIptObj = $('<input type="text"  class="form-control" id="deliveryAddress'+ elementId +'" autocomplete="off">');
            deliveryAddressDivsm8Obj.append(deliveryAddressIptObj);
            deliveryAddressDivObj.append(deliveryAddressDivsm8Obj);
            liObj.append(deliveryAddressDivObj);

            var deliveryAddressRemarkDivObj = $('<div class="form-group row"></div>');
            var deliveryAddressRemarkIptObj = $('<label for="remark'+ elementId +'" class=" col-sm-2 col-form-label" >&nbsp;&nbsp;&nbsp;&nbsp;备注</label>');
            deliveryAddressRemarkDivObj.append(deliveryAddressRemarkIptObj);
            var deliveryAddressRemarkDivsm8Obj = $('<div class="col-sm-8"></div>');
            var deliveryAddressRemarkTxArObj = $('<textarea  class="form-control" id="remark'+ elementId +'" autocomplete="off"></textarea>');
            deliveryAddressRemarkDivsm8Obj.append(deliveryAddressRemarkTxArObj);
            deliveryAddressRemarkDivObj.append(deliveryAddressRemarkDivsm8Obj);
            liObj.append(deliveryAddressRemarkDivObj);

            liObj.appendTo(areaObj);
        }

    }
    window.addFunctionLine = addFunctionLine;

    //删除最后一行
    function removeFunctionLine(type, areaId,dealLineId) {
        if(type =='' || type == null){

            return;
        }
        var areaObj = $(areaId);

        var areaIdLength = areaObj.children('li').length;

        console.log(areaIdLength);

        if(areaIdLength == 2){
            $(dealLineId).addClass('d-none');
        }
        $(areaId + ' li:last').remove();

    }

    function ajaxMethod(method,url){

    }
    window.removeFunctionLine = removeFunctionLine;

})(window);
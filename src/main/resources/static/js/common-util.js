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

            var liObj = $('<li class="form-group" style="border: 1px dashed #ced4da;"></li>');

            var div1Obj = $('<div class="form-group row pl-1" style="margin-top: 10px;"></div>');

            //商品名称label
            var commodityNameLbObj = $('<label for="commodity-name-'+ elementId +'" class="col-sm-2 col-form-label" >商品'+ elementId +'名称</label>');
            div1Obj.append(commodityNameLbObj);
            var div1Sub1Obj = $('<div class="col-sm-3"></div>');
            //商品select
            var commodityNameSlctObj = $('<select id="order-commodity" class="form-control" name="commodityId'+ elementId +'"></select>');
            var commodityNameIptObj = $('#order-commodity option').clone();
            commodityNameSlctObj.append(commodityNameIptObj);
            div1Sub1Obj.append(commodityNameSlctObj);
            div1Obj.append(div1Sub1Obj);
            var commodityNumLabelObj = $('<label for="commodity-num-1" class="offset-sm-1 col-sm-2 col-form-label" ></label>').html('数量');
            div1Obj.append(commodityNumLabelObj);
            var div1Sub2Obj = $('<div class="col-sm-3"></div>');
            var commodityNumIptObj = $('<input type="number"  name="commodityNum'+ elementId +'" class="form-control" id="commodity-num-1" autocapitalize="off" min="0" value="1" >');
            div1Sub2Obj.append(commodityNumIptObj);
            div1Obj.append(div1Sub2Obj);
            liObj.append(div1Obj);


            var div4Obj = $('<div class="form-group row pl-1" style="margin-top: 10px;"></div>');
            //快递公司
            var expressCompanyLbObj = $('<label for="express-company" class="col-sm-2 col-form-label" ></label>').html('快递公司');
            div4Obj.append(expressCompanyLbObj);
            var div4ObjSub1Obj = $('<div class="col-sm-3"></div>');
            var expressCompanyIptObj = $('<input type="text"  class="form-control" id="express-company" name="expressCompany'+ elementId +'" autocapitalize="off">');
            div4ObjSub1Obj.append(expressCompanyIptObj);
            div4Obj.append(div4ObjSub1Obj);
            liObj.append(div4Obj);

            //快递单号label
            var div5Obj = $('<div class="form-group row pl-1" style="margin-top: 10px;"></div>');
            var expressNoLbObj = $('<label for="express-no" class=" col-sm-2 col-form-label" ></label>').html('快递单号');
            div5Obj.append(expressNoLbObj);
            var div5ObjSub1Obj = $('<div class="col-sm-10"></div>');
            var  expressNoIptObj = $('<input type="text"  class="form-control" id="express-no" name="expressNo'+ elementId +'" autocapitalize="off" placeholder="多个快递单号请以英文状态下的分号分割1;2;3">');
            div5ObjSub1Obj.append(expressNoIptObj);
            div5Obj.append(div5ObjSub1Obj);
            liObj.append(div5Obj);

            var div2Obj = $('<div class="form-group row pl-1" style="margin-top: 10px;"></div>');
            //运费label
            var freightLblObj = $('<label for="freight-1" class="col-sm-2 col-form-label" ></label>').html('运费');
            div2Obj.append(freightLblObj);
            var dic2Sub1Obj = $('<div class="col-sm-3"></div>');
            //运费
            var freightIptObj = $('<input type="number" name="freight'+ elementId +'" class="form-control" id="freight-1" autocapitalize="off" min="0" step="0.01">');
            dic2Sub1Obj.append(freightIptObj);
            div2Obj.append(dic2Sub1Obj);
            liObj.append(div2Obj);

            //优惠券和折扣
            var div3Obj = $('<div class=" form-group row pl-1"></div>');
            //优惠券
            var couponLabelObj = $('<label class="col-sm-2 col-form-label" ></label>').html('优惠券');
            div3Obj.append(couponLabelObj);
            var div3ObjSub1 = $('<div class="col-sm-3"></div>');
            var couponSelectObj = $('<select class="form-control" name="coupon'+ elementId +'"></select>');
            var couponOptObj = $('#coupon1 option').clone();
            couponSelectObj.append(couponOptObj);
            div3ObjSub1.append(couponSelectObj);
            div3Obj.append(div3ObjSub1);

            var discountObj =$('<label for="discount" class="offset-sm-1 col-sm-2 col-form-label" ></label>').html('折扣');
            div3Obj.append(discountObj);
            var div3ObjSub2 = $('<div class="col-sm-3"></div>');
            var discountObj = $('<select id="discount'+ elementId +'" class="form-control"></select>');
            var discountOptObj = $('#discount1 option').clone();
            discountObj.append(discountOptObj);
            div3ObjSub2.append(discountObj);
            div3Obj.append(div3ObjSub2);
            liObj.append(div3Obj);

            //备注
            var div5Obj = $('<div class=" form-group row pl-1"></div>');
            //备注
            var remarkLbObj = $('<label class="col-sm-2 col-form-label" ></label>').html('备注');
            div5Obj.append(remarkLbObj);
            var div5Sub1Obj = $('<div class="col-sm-8"></div>');
            var remarklIptObj = $(' <input type="text"  class="form-control" id="remark'+ elementId +'" autocapitalize="off">');
            div5Sub1Obj.append(remarklIptObj);
            div5Obj.append(div5Sub1Obj);
            liObj.append(div5Obj);

            liObj.appendTo(areaObj);

        }else if( type == 'user'){
            var liObj = $('<li  style="border: 1px dashed #ced4da;margin-top: 10px;"></li>');

            var deliveryNameDiv = $('<div class="form-group row" style="margin-top: 10px;"></div>');

            var deliveryIndex = $('<input type="hidden"  class="form-control" name="deliveryIndex'+ elementId +'" value="'+ elementId +'">');
            deliveryNameDiv.append(deliveryIndex);

            var deliveryNameLb = $('<label for="deliveryName'+ elementId +'" class=" col-sm-2 col-form-label" >&nbsp;&nbsp;&nbsp;&nbsp;收货人姓名</label>');
            deliveryNameDiv.append(deliveryNameLb);
            var colsm3Div = $('<div class="col-sm-3"></div>');
            var deliveryNameIptObj = $('<input type="text"  class="form-control" name="deliveryName'+ elementId +'" id="deliveryName'+ elementId +'" autocomplete="off">');
            colsm3Div.append(deliveryNameIptObj);
            deliveryNameDiv.append(colsm3Div);
            var phoneNumberLbl =$('<label for="phoneNumber'+ elementId +'" class="offset-sm-1 col-sm-2 col-form-label" >&nbsp;&nbsp;&nbsp;&nbsp;收货人手机号</label>');
            deliveryNameDiv.append(phoneNumberLbl);
            var colsm3Div2Obj = $('<div class="col-sm-3"></div>');
            var phoneNumberInpt = $('<input type="text"  class="form-control" name = "phoneNumber'+ elementId +'" id="phoneNumber'+ elementId +'" autocomplete="off">');
            colsm3Div2Obj.append(phoneNumberInpt);
            deliveryNameDiv.append(colsm3Div2Obj);
            liObj.append(deliveryNameDiv);

            var deliveryAddressDivObj = $('<div class="form-group row"></div>');
            var deliveryAddressLblObj = $('<label for="deliveryAddress'+ elementId +'" class=" col-sm-2 col-form-label" >&nbsp;&nbsp;&nbsp;&nbsp;收货人地址</label>')
            deliveryAddressDivObj.append(deliveryAddressLblObj);
            var deliveryAddressDivsm8Obj = $('<div class="col-sm-8"></div>');
            var deliveryAddressIptObj = $('<input type="text"  class="form-control" name="deliveryAddress'+ elementId +'" id="deliveryAddress'+ elementId +'" autocomplete="off">');
            deliveryAddressDivsm8Obj.append(deliveryAddressIptObj);
            deliveryAddressDivObj.append(deliveryAddressDivsm8Obj);
            liObj.append(deliveryAddressDivObj);

            var deliveryAddressRemarkDivObj = $('<div class="form-group row"></div>');
            var deliveryAddressRemarkIptObj = $('<label for="deliveryRemark'+ elementId +'" class=" col-sm-2 col-form-label" >&nbsp;&nbsp;&nbsp;&nbsp;备注</label>');
            deliveryAddressRemarkDivObj.append(deliveryAddressRemarkIptObj);
            var deliveryAddressRemarkDivsm8Obj = $('<div class="col-sm-8"></div>');
            var deliveryAddressRemarkTxArObj = $('<textarea  class="form-control" name = "deliveryRemark'+ elementId +'" id="deliveryRemark'+ elementId +'" autocomplete="off"></textarea>');
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


    //查询List
    function getTableList(url, offset, size) {
        var result = null;
        $.ajax({
            async : false,
            type : 'GET',
            url : url,
            contentType: "application/json;charset=utf-8",
            data : {page:offset,
                size:size},
            success : function(callback) {
                result = callback;
            },
            dataType : 'json'
        });
        return result;
    }

    window.getTableList = getTableList;

    //请求ajax
    function ajax(method,url,param) {
        var result = null;
        console.log(JSON.stringify(param));
        if(method == 'POST' || method == 'PUT'){
            param = JSON.stringify(param);
        }
        $.ajax({
            async : false,
            type : method,
            url : url,
            contentType: "application/json;charset=utf-8",
            data : param,
            success : function(callback) {
                console.log(callback);
                result = callback;
            },
            dataType : 'json'
        });
        return result;
    }

    window.ajax = ajax;

    /**
     * 按钮组的页码设置
     * */
    function showPageNo(content){

        var paginationId = $('#pagination-list');
        paginationId.attr('data-size',content.size);

        //首页
        var firstPageObj = $('#pagination-list .first-page' );
        //上一页
        var previousPageObj = $('#pagination-list .previous-page');
        //下一页
        var nextPageObj = $('#pagination-list .next-page');
        //末页
        var lastPageObj = $('#pagination-list .last-page');
        //页码信息
        var pageInfoObj = $('#pagination-list .page-info');

        if(content.totalPages == 0 || content.totalPages == 1){
            firstPageObj.addClass('disabled');
            previousPageObj.addClass('disabled');
            nextPageObj.addClass('disabled');
            lastPageObj.addClass('disabled');

        }else if(content.first){

            firstPageObj.addClass('disabled');
            previousPageObj.addClass('disabled');
            nextPageObj.removeClass('disabled');
            lastPageObj.removeClass('disabled');

            nextPageObj.attr('data-page',content.number +1);
            lastPageObj.attr('data-page',content.totalPages - 1);

        }else if(content.last){
            firstPageObj.removeClass('disabled');
            previousPageObj.removeClass('disabled');
            nextPageObj.addClass('disabled');
            lastPageObj.addClass('disabled');

            firstPageObj.attr('data-page',0);
            previousPageObj.attr('data-page',content.number -1);
        }else{

            firstPageObj.removeClass('disabled');
            previousPageObj.removeClass('disabled');
            nextPageObj.removeClass('disabled');
            lastPageObj.removeClass('disabled');

            firstPageObj.attr('data-page',0);
            previousPageObj.attr('data-page',content.number -1);//1
            nextPageObj.attr('data-page',content.number + 1);
            lastPageObj.attr('data-page',content.totalPages -1);

        }


        pageInfoObj.html('第'+ (content.number +1) +'页/共'+ content.totalPages +'页(共'+ content.totalElements+'条)');

    }
    window.showPageNo = showPageNo;

    //关闭模态框
    $('#result-modal').on("click",".close-modal", function(){

        $('#result-modal').modal('hide');

        var resultModalInfoObj = $('#result-modal-info');

        var addFlag = resultModalInfoObj.attr('data-flag');

        if(addFlag == "true"){
            window.location.href =resultModalInfoObj.attr('data-url');
        }

    });

    /**
     * 关闭当前页面
     * */
    $('#close-page').on('click',function(){
        var resultModalInfoObj = $('#result-modal-info');
        resultModalInfoObj.html('您确定要关闭当前界面吗？');
        $('#result-modal').modal('show');

    });
    /**
     * 点击模态框的确定按钮
     * */
    $('.confirm-close-modal').on('click',function(){
        window.location.href =$(this).attr('data-url');
    });

    /**
     * 详情页更新信息
     * */
    $('#update-info-btn').on('click',function(){

        var resultModalInfoObj = $('#result-modal-info');

        var updateFormObj = $('#update-form');
        var updateFormArrays = updateFormObj.serializeArray();
        var updateObj = {};
        $.each(updateFormArrays, function() {
            if(this.value == "true"){
                this.value = true;
            }else if( this.value == "false"){
                this.value = false;
            }
            updateObj[this.name] = this.value;
        });
        console.log(updateObj);
        var updateUrl = $(this).attr('data-update-url');
        var updateResult = window.ajax('PUT',updateUrl,updateObj);
        console.log(updateResult);

        resultModalInfoObj.html(updateResult.responseMessage);

        $('#result-modal').modal('show');
    });
    //表格信息，点击行后显示详情
    $('#list-table').on("click","tr", function(){

        var thisObj = $(this);
        var id = $(this).data('id');
        var url = thisObj.data('url');
        console.log(id);
        console.log(url);

        if( url != null && url != ''){
            window.location.href = url;
            if(url == '/order/detail'){
                localStorage.removeItem('orderId');
                localStorage.setItem('orderId',id);
            }else if(url == '/commodity/detail'){
                localStorage.removeItem('commodityId');
                localStorage.setItem('commodityId',id);
            }
        }

    });

    //分页查询
    $('#pagination-list').on('click','.pagination-page',function(){
        var paginationListObj = $('#pagination-list');
        var url = paginationListObj.data('url');
        var pageNo = $(this).attr('data-page');
        var size = paginationListObj.data('size');
        var pageName = paginationListObj.data('page-name');

        var result = window.ajax('GET',url, {page:pageNo,size : size});
        if(pageName == 'user'){
            window.drawUserTableList(result);
        }else if(pageName == 'commodity'){
            window.drawCommodityTableList(result);
        }else if(pageName == 'order'){
            window.drawOrderTableList(result);
        }else{

        }

    });

    /**
     * 绘制用户列表
     * */
    var drawUserTableList = function (result) {

        var listTableObj = $('#list-table tbody');

        if( result.responseType == 'N'){
            listTableObj.empty();

            result.responseReplyInfo.content.forEach(function(element,index){
                var trObj = $('<tr></tr>');
                var realNameTdObj = $('<td></td>').html(element.realName);
                trObj.append(realNameTdObj);
                var birthDayTdObj = $('<td class="text-center"></td>').html(element.birthDay);
                trObj.append(birthDayTdObj);
                var joinDayTdObj  = $('<td class="text-center"></td>').html(element.joinDay);
                trObj.append(joinDayTdObj);
                var buyTimesObj = $('<td></td>');
                trObj.append(buyTimesObj);
                var orderAccountObj = $('<td></td>');
                trObj.append(orderAccountObj);
                var grossProfitObj = $('<td></td>');
                trObj.append(grossProfitObj);

                /*var operationObj = $('<td class="p-0 align-middle text-center"></td>');
                var editOperationObj = $('<a  class="btn btn-primary btn-sm text-white">编辑</a>');
                operationObj.append(editOperationObj);
                trObj.append(operationObj);*/

                listTableObj.append(trObj);
            });
        }
        window.showPageNo(result.responseReplyInfo);
    };
    window.drawUserTableList = drawUserTableList;

    /**
     * 绘制商品列表
     * */
    var drawCommodityTableList = function (result) {

        var listTableObj = $('#list-table tbody');

        if(result.responseType == 'N' && result.responseReplyInfo != null){
            listTableObj.empty();

            var listContent = result.responseReplyInfo.content;

            listContent.forEach(function (element, index) {
                var trObj = $('<tr></tr>');

                trObj.attr('data-id', element.commodityId);
                trObj.attr('data-url', '/commodity/detail');
                trObj.addClass('pointer');

                //商品类型
                //var commodityType = element.parentId.commodityName;
                /*if( commodityType == '新鲜水果'){
                    trObj.addClass('bg-msg');
                }else if(commodityType == '蔬菜米蛋'){
                    trObj.addClass('bg-gold');
                }else if( commodityType == '休闲零食'){
                    trObj.addClass('bg-cclt');
                }else{
                    trObj.addClass('bg-idr');
                }*/
                var commodityTypeTd = $('<td></td>').html(element.parentId.commodityName);
                trObj.append(commodityTypeTd);

                //商品名称
                var commodityNameTd = $('<td></td>').html(element.commodityName);
                trObj.append(commodityNameTd);

                //成本
                var costPriceTd = $('<td></td>').html(element.costPrice);
                trObj.append(costPriceTd);

                //售价
                var commodityPriceTd = $('<td></td>').html(element.commodityPrice);
                trObj.append(commodityPriceTd);

                //利润
                var profitTd = $('<td></td>').html(element.commodityPrice - element.costPrice);
                trObj.append(profitTd);

                //规格
                var commoditySpecificationsTd = $('<td></td>').html(element.commoditySpecifications);
                trObj.append(commoditySpecificationsTd);

                //操作
                var totalProfitTd = $('<td class="p-0 align-middle text-center"></td>').html('0');
                trObj.append(totalProfitTd);

                listTableObj.append(trObj);
            });
            window.showPageNo(result.responseReplyInfo);
        }

    };
    window.drawCommodityTableList = drawCommodityTableList;


    var drawOrderTableList = function (result) {
        var listTableObj = $('#list-table tbody');

        if(result.responseType == 'N' && result.responseReplyInfo != null) {
            listTableObj.empty();
            var listContent = result.responseReplyInfo.content;
            listContent.forEach(function (element, index) {
                var trObj = $('<tr style="color: white"></tr>');

                trObj.attr('data-id', element.orderId);
                trObj.attr('data-url', '/order/detail');
                trObj.addClass('pointer');

                //订单编号
                var orderIdTd = $('<td  class="text-center"></td>').html(element.orderId);
                trObj.append(orderIdTd);


                //订单日期
                var creationDateTd = $('<td class="text-center"></td>').html(getFormatDate(element.orderCreationDate));
                trObj.append(creationDateTd);

                //商品
                var commodityTd = $('<td></td>').html(
                    element.commodityName + '*' + element.commodityNum);
                trObj.append(commodityTd);

                //付款人
                var payerNameTd = $('<td class="text-center"></td>').html(element.payerName);
                trObj.append(payerNameTd);
                //收货人
                var deliveryNameTd = $('<td class="text-center"></td>').html(element.deliveryName);
                trObj.append(deliveryNameTd);

                //商品总额
                var orderTotalPriceTd = $('<td  class="text-center"></td>').html(element.orderTotalPrice);
                trObj.append(orderTotalPriceTd);

                //订单状态
                var orderStatusTd = $('<td  class="text-center"</td>').html(element.orderStatus);
                trObj.append(orderStatusTd);

                //订单状态Id
                var orderStatusId = element.orderStatusId;
                switch (orderStatusId) {
                    case 0:
                        trObj.css('background','#DB7093');
                        break;
                    case 1:
                        trObj.css('background','#DC143C');
                        break;
                    case 2:
                        trObj.css('background','#6BBE45');
                        break;
                    case 3:
                        trObj.css('background','#3CB878');
                        break;
                    case 4:
                        trObj.css('background','#1F9BCA');
                        break;
                    case 5:
                        trObj.css('background','#2C6EA5');
                        break;
                    case 6:
                        trObj.css('background','#A9A9A9');
                        break;
                    case 9:
                        trObj.css('background','#20B2AA');
                        break;
                    case 20:
                        trObj.css('background','#B21D4B');
                        break;
                    case 21:
                        trObj.css('background','#C9DB31');
                        break;
                    case 22:
                        trObj.css('background','#FFA07A');
                        break;

                }
                /*0:预定中
                1:待录入
                2:未发货
                3:已发货
                4:已签收
                5:已好评
                6:取消
                9:完成
                20:已退款
                21:待处理
                22:处理中*/


                /*//付款方式*/
                listTableObj.append(trObj);
            });
            window.showPageNo(result.responseReplyInfo);
        }
    }
    window.drawOrderTableList = drawOrderTableList;

    /**
     * 格式化日期yyyy-MM-dd
     * **/
    var getFormatDate = function (datetime) {
        datetime = new Date(datetime);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) :
            datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        return year + '-' + month + '-'+ date;
    }
    window.getFormatDate = getFormatDate;

    /**
     * 将boolean转换为String类型的int值
     * */
    var booleanToStringInt = function(value){

        if(value instanceof Boolean){
            if(value){
                return "1";
            }else{
                return "0";
            }
        }else if(value instanceof String){
            if(value == "true"){
                return "1";
            }else{
                return "0";
            }
        }
    }

    window.booleanToStringInt = booleanToStringInt;

})(window);
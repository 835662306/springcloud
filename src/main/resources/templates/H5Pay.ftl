<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>
<input type="hidden" name="appId" value="${appId}">
<input type="hidden" name="nonceStr" value="${nonceStr}">
<input type="hidden" name="prepayId" value="${prepayId}">
<input type="hidden" name="paySign" value="${paySign}">
<input type="hidden" name="timeStamp" value="${timeStamp}">
</body>
<script>

    function onBridgeReady(){
        var appId = $("input[name='appId']").val();
        var nonceStr = $("input[name='nonceStr']").val();
        var prepayId = $("input[name='prepayId']").val();
        var paySign = $("input[name='paySign']").val();
        var timeStamp = $("input[name='timeStamp']").val();
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":appId,
                    "timeStamp":timeStamp,
                    "nonceStr":nonceStr,
                    "package":prepayId,
                    "signType":"MD5",
                    "paySign":paySign
                },
                function(res){
                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                        location.href="支付成功返回商家自定义页面";
                    }else {//这里支付失败和支付取消统一处理
                        alert("支付取消");
                        location.href="支付失败返回商家自定义页面";
                    }
                }
        );
    }

    $(document).ready(function () {
        if (typeof WeixinJSBridge == "undefined"){
            if (document.addEventListener){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        }else {
            onBridgeReady();
        }
    });
</script>
</html>
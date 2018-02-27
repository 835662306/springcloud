<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(function() {
            $('.a').click(function() {
                alert('hello world');
            });
        })
    </script>
</head>
<body>
<h2>首页<h2>

    <font>${name!}
    <#--<#list userList as item>-->
    <#--${item!}<br />-->
    <#--</#list>-->
    </font>

    <button class="a"> click me</button>
</body>
</html>
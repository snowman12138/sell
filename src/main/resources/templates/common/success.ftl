<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>成功页面</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功！
                    <br>
                    <br>
                </h4> <strong>${msg}</strong> 3秒后自动跳转回${back!"订单页面或点击"}<a href="${url}" class="alert-link">跳转</a>进行跳转！
            </div>
        </div>
    </div>
</body>
<script>
    setTimeout('location.href="${url}"',3000);
</script>
</html>
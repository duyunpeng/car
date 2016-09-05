<!DOCTYPE html>
<html lang="en">
<head>
[@block name="Meta"]
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content="YJH"/>
[/@block]

    <title>西瓜专车 后台管理 - 登录</title>
    <link rel="shortcut icon" href="[@spring.url '/resources/assets/images/favicon.ico' /]" type="image/x-icon"/>

    <link href="[@spring.url '/resources/assets/css/bootstrap.min.css'/]" rel="stylesheet"/>
    <link href="[@spring.url '/resources/assets/css/font-awesome.min.css'/]" rel="stylesheet"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/font-awesome-ie7.min.css'/]"/>
    <![endif]-->

    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/ace.min.css'/]"/>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/ace-rtl.min.css'/]"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/ace-ie.min.css'/]"/>
    <![endif]-->

    <!--[if lt IE 9]>
    <script src="[@spring.url '/resources/assets/js/html5shiv.js'/]"></script>
    <script src="[@spring.url '/resources/assets/js/respond.min.js'/]"></script>
    <![endif]-->


    <script type="text/javascript">
        window.jQuery || document.write("<script src='/resources/assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
    </script>

    <!-- <![endif]-->

    <!--[if !IE]> -->
    <script type="text/javascript">
        window.jQuery || document.write("<script src='/resources/assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
    </script>
    <!-- <![endif]-->

    <script type="text/javascript">
        if ("ontouchend" in document) document.write("<script src='/resources/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
    </script>

    <!-- inline scripts related to this page -->

    <script type="text/javascript">
        function show_box(id) {
            jQuery('.widget-box.visible').removeClass('visible');
            jQuery('#' + id).addClass('visible');
        }
    </script>
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <img class="logo-img" src="[@spring.url '/resources/assets/images/logo.ico'/]">
                            <span class="red">西瓜专车</span>
                            <span class="white">后台管理</span>
                        </h1>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                [@mc.showAlert /]
                                    <div class="space-6"></div>

                                    <form action="[@spring.url '/login'/]" class="form-horizontal" method="post">
                                        [#--<fieldset>--]
                                        [@spring.bind "user.username"/]
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" value="${user.username!}"
                                                                   class="form-control"
                                                                   placeholder="用户名" required/>
															<i class="icon-user"></i>
														</span>
                                            [@spring.showErrors "username"/]
                                            </label>

                                        [@spring.bind "user.password"/]
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password"
                                                                   value="${user.password!}" class="form-control"
                                                                   placeholder="密码" required/>
															<i class="icon-lock"></i>
														</span>
                                            [@spring.showErrors "password"/]
                                            </label>

                                        [@spring.bind "user.verificationCode"/]
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="verificationCode"
                                                                   class="form-control col-xs-2 col-sm-2"
                                                                   style="width: 190px"
                                                                   placeholder="验证码" required/>
                                                        [@mc.verificationCode /]
														</span>
                                            [@spring.showErrors "verificationCode"/]
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                </label>

                                                <button type="submit"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i>
                                                    登录
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div><!-- /widget-main -->
                            </div><!-- /widget-body -->
                        </div><!-- /login-box -->
                    </div><!-- /position-relative -->
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</div><!-- /.main-container -->
</body>
</html>
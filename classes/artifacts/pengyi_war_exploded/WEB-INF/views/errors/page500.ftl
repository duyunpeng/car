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

    <title>Car 后台管理 - 500</title>
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


<body>
<div class="main-container" id="main-container">
    <div class="main-container-inner">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->

                    <div class="error-container">
                        <div class="well">
                            <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-random"></i>
												500
											</span>
                                Something Went Wrong
                            </h1>

                            <hr/>
                            <h3 class="lighter smaller">
                                [@mc.showAlert /]
                                <i class="icon-wrench icon-animated-wrench bigger-125"></i>
                                on it!
                            </h3>

                            <div class="space"></div>

                            <div>
                                <h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

                                <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                    <li>
                                        <i class="icon-hand-right blue"></i>
                                        Read the faq
                                    </li>

                                    <li>
                                        <i class="icon-hand-right blue"></i>
                                        Give us more info on how this specific error occurred!
                                    </li>
                                </ul>
                            </div>

                            <hr/>
                            <div class="space"></div>

                            <div class="center">
                                <a href="#" class="btn btn-grey">
                                    <i class="icon-arrow-left"></i>
                                    刷新
                                </a>

                                <a href="/" class="btn btn-primary">
                                    <i class="icon-dashboard"></i>
                                    返回首页
                                </a>
                            </div>
                        </div>
                    </div>

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div><!-- /.main-container-inner -->
</div>
</body>
</html>

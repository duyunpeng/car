<!DOCTYPE html>
<!--
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
-->
<html lang="zh_cn" xmlns="http://www.w3.org/1999/html">
<head>
[@block name="Meta"]
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content="YJH"/>
[/@block]
    <title>西瓜专车 后台管理 - [@block name="title"][/@block]</title>
    <link rel="shortcut icon" href="[@spring.url '/resources/assets/images/favicon.ico' /]" type="image/x-icon"/>
    <!-- basic styles -->
    <link href="[@spring.url '/resources/assets/css/bootstrap.min.css' /]" rel="stylesheet"/>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/font-awesome.min.css' /]"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/font-awesome-ie7.min.css'/]"/>
    <![endif]-->

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/ace-ie.min.css' /]"/>
    <![endif]-->

    <!--[if lt IE 9]>
    <script src="[@spring.url '/resources/assets/js/html5shiv.js'/]"></script>
    <script src="[@spring.url '/resources/assets/js/respond.min.js'/]"></script>
    <![endif]-->

[@block name = "topResources"]
    <!-- ace styles -->
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/ace.min.css'/]"/>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/ace-rtl.min.css'/]"/>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/ace-skins.min.css'/]"/>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/app/css/app.css'/]"/>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/minimal.css/' /]"/>
    <link rel="stylesheet" href="[@spring.url '/resources/assets/css/reset.css/' /]"/>
    <script src="[@spring.url '/resources/assets/js/ace-extra.min.js'/]"></script>
    <script src="[@spring.url '/resources/assets/js/common.js'/]"></script>
[/@block]


</head>

<body>
<!-- 头部 -->
[@block name="header"]
    [#include "/shared/header.ftl" /]
[/@block]
<!-- end 头部 -->

<!-- 页面主内容 -->
<div class="main-container" id="main-container">

    <!-- 页面主内容(内) -->
    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <!-- 侧边栏 -->
    [#include "/shared/slider-bar.ftl"]
        <!-- end 侧边栏 -->

        <div class="main-content">
            <!-- 面包屑导航 -->
            <div class="breadcrumbs" id="breadcrumbs">

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="/">首页</a>
                    </li>
                [@block name="breadcrumbTitle"]
                [/@block]
                </ul><!-- .breadcrumb -->
            </div>
            <!-- end 面包屑导航 -->

            <!-- 页面中间内容 -->
            <div class="page-content">
                <!-- 头部 -->
                <div class="page-header">
                    <h1>
                    [@block name="pageHeaderTitle"]

                    [/@block]
                    </h1>
                </div>
                <!-- end 头部 -->

            [@block name="subContent"]

            [/@block]
            </div>
            <!-- end 页面中间内容 -->
        </div>

        <div class="ace-settings-container" id="ace-settings-container">
            <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                <i class="icon-cog bigger-150"></i>
            </div>

            <div class="ace-settings-box" id="ace-settings-box">
                <div>
                    <div class="pull-left">
                        <select id="skin-colorpicker" class="hide">
                            <option data-skin="default" value="#438EB9">#438EB9</option>
                            <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                            <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                            <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                        </select>
                    </div>
                    <span>&nbsp; 选择皮肤</span>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>
                    <label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>
                    <label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>
                    <label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"/>
                    <label class="lbl" for="ace-settings-rtl">切换到左边</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>
                    <label class="lbl" for="ace-settings-add-container">
                        切换窄屏
                        <b></b>
                    </label>
                </div>
            </div>
        </div><!-- /#ace-settings-container -->
    </div>
    <!-- end 页面主内容(内) -->
</div>
<!-- end 页面主内容 -->

<!-- 返回顶部 -->
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
<!-- end 返回顶部 -->

[@block name="bottomResources"]
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='/resources/assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='resources/assets/js/jquery-1.10.2.min.js'>" + "<" + "script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='resources/assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
</script>
<script src="[@spring.url '/resources/assets/js/bootstrap.min.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/typeahead-bs2.min.js'/]"></script>

<!--[if lte IE 8]>
<script src="[@spring.url '/resources/assets/js/excanvas.min.js'/]"></script>
<![endif]-->
<!-- ace scripts -->
<script src="[@spring.url '/resources/assets/js/ace-elements.min.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/ace.min.js'/]"></script>
<script>
    $('body').addClass(getCookie("themeName"));
    $('.change-bg').addClass(getCookie("themeName"));
</script>
<script src="[@spring.url '/resources/assets/app/js/app.js'/]"></script>
[/@block]

</body>
</html>


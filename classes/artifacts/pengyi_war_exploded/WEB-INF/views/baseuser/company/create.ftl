[@override name="title"]公司用户管理-创建公司用户[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css" href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/company/list'/]">公司用户管理</a></li>
<li class="active">创建公司用户</li>
[/@override]

[@override name="pageHeaderTitle"]
创建公司用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/user/company/create" class="form-horizontal" id="form-create" method="post">

            [@spring.bind "command.userName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名* </label>

                <div class="col-sm-9">
                    <input type="text" id="userName" name="userName"
                           placeholder="用户名(手机号)" pattern="^1[345678][0-9]{9}$"
                           class="col-xs-10 col-sm-5"
                           required/>
                    [@spring.showErrors "userName"/]
                </div>
            </div>

            [@spring.bind "command.password"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密码* </label>

                <div class="col-sm-9">
                    <input type="password" id="password" name="password"
                           placeholder="密码" class="col-xs-10 col-sm-5" minlength="6" required/>
                    [@spring.showErrors "password"/]
                </div>
            </div>

            [@spring.bind "command.confirmPassword"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 确认密码* </label>

                <div class="col-sm-9">
                    <input type="password" id="confirmPassword" name="confirmPassword"
                           placeholder="确认密码" class="col-xs-10 col-sm-5" minlength="6"
                           onchange="checkPasswords()" required/>
                    [@spring.showErrors "confirmPassword"/]
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司名称* </label>

                <div class="col-sm-9">
                    <input type="text" id="name" name="name"
                           placeholder="公司名称" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司注册时间* </label>

                <div class="col-sm-9">
                    <input type="text" id="registerDate" name="registerDate"
                           placeholder="公司注册时间" class="col-xs-10 col-sm-5" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 注册地点* </label>

                <div class="col-sm-5">
                    <div class="area_box_1">
                        <select class="col-xs-3 area_data" name="registerAddress">
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 运营地点* </label>

                <div class="col-sm-5">
                    <div class="area_box_2">
                        <select class="col-xs-3 area_data" name="operateAddress">
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司资质文件* </label>

                <div class="col-sm-9">
                    <button type="button" class="btn btn-sm btn-primary input-file-hidden"
                            id="folderUpload">点击上传照片
                    </button>
                    <input type="hidden" id="folder" name="folder"
                           placeholder="公司资质文件" class="form-control col-xs-10 col-sm-5" required/>
                    <p class="img-box"></p>
                </div>
            </div>


            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        创建
                    </button>
                    <button class="btn" type="reset">
                        <i class="icon-undo bigger-110"></i>
                        重置
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
[#--文件上传进度--]
<div class="file_upload_load"></div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/js/upload/webuploader.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/layer/layer.js'/]"></script>
<script src="[@spring.url '/resources/assets/app/js/area.js'/]"></script>
<script src="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">
    function checkPasswords() {
        var pass1 = document.getElementById("password");
        var pass2 = document.getElementById("confirmPassword");

        if (pass1.value != pass2.value)
            pass2.setCustomValidity("两次输入的密码不匹配");
        else
            pass2.setCustomValidity("");
    }

    $(".area_box_1").areaCascade("registerAddress");
    $(".area_box_2").areaCascade("operateAddress");

    folder = WebUploader.create({
        // 自动上传。
        auto: true,
        // 文件接收服务端。
        server: '/uploadFile/upload_img',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#folderUpload',
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    folder.on('fileQueued', function (file) {
        folder.makeThumb(file, function (error, src) {
            if (error) {
                return;
            }
//                alert(src);
        });
    });
    folder.on('uploadProgress', function (file, percentage) {
        $('html').addClass('.file_upload_mask');
        $('.file_upload_load').show();
    });
    folder.on('uploadSuccess', function (file, result) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传成功！", {icon: 1});
        var url = result.files[0].url;
        $("#folder").val(url);
        $("#folder").parent().find(".img-box").empty();
        $("#folder").parent().find(".img-box").append('<img src=' + url + '/><button type="button" class="btn btn-danger del-img">删除</button>');
    });
    folder.on('uploadError', function (handler) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传失败！");
    });

    $(".img-box").on("click", "button", function () {
        $(this).parent().parent().find("input").val("");
        $(this).parent().empty();
    })

    $("#form-create").submit(function () {
        if ($("select[name='registerAddress']").val() == "") {
            layer.msg("公司注册地点不能为空");
            return false;
        }
        if ($("select[name='operateAddress']").val() == "") {
            layer.msg("公司运营地点不能为空");
            return false;
        }
        if ($("#folder").val() == "") {
            layer.msg("公司资质图片不能为空");
            return false;
        }
        return true;
    })

    $.datetimepicker.setLocale('en');
    $('#registerDate').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]
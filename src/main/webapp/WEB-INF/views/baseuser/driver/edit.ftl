[@override name="title"]司机用户管理-修改司机用户[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/driver/list'/]">司机用户管理</a></li>
<li class="active">修改公司用户</li>
[/@override]

[@override name="pageHeaderTitle"]
修改公司用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/user/driver/edit" class="form-horizontal" id="form-edit" method="post">

            <input type="hidden" name="id" value="${driver.id!command.id}"/>
            <input type="hidden" name="version" value="${driver.version!command.version}"/>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="userName" value="${driver.userName!command.userName}"
                           class="col-xs-10 col-sm-5" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机名字 </label>

                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${driver.name!command.name}"
                           placeholder="司机名字" class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 所属公司* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="company" name="company"
                            data-id="${driver.company.id!command.company}"
                            required>
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="identityCardPic"> 身份证照片* </label>

                <div class="col-sm-9">
                    <a class="btn btn-sm btn-primary left input-file-hidden"
                       id="identityCardPicUpload">点击上传照片
                    </a>
                    <input type="hidden" id="identityCardPic" name="identityCardPic"
                           value="${driver.identityCardPic!command.identityCardPic}"
                           placeholder="身份证照片" class="form-control col-xs-10 col-sm-5" required/>
                    <p class="img-box">
                        [#if driver.identityCardPic??]
                            <img src="${driver.identityCardPic!command.identityCardPic}">
                            <button type="button" class="btn btn-danger del-img">删除</button>
                        [/#if]
                    </p>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="identityCardPic"> 驾驶证照片* </label>

                <div class="col-sm-9">
                    <a class="btn btn-sm btn-primary left input-file-hidden"
                       id="drivingLicencePicUpload">点击上传照片
                    </a>
                    <input type="hidden" id="drivingLicencePic" name="drivingLicencePic"
                           value="${driver.drivingLicencePic!command.drivingLicencePic}"
                           placeholder="驾驶证照片" class="form-control col-xs-10 col-sm-5" required/>
                    <p class="img-box">
                        [#if driver.drivingLicencePic??]
                            <img src="${driver.drivingLicencePic!command.drivingLicencePic}">
                            <button type="button" class="btn btn-danger del-img">删除</button>
                        [/#if]
                    </p>
                </div>
            </div>

            [@spring.bind "command.driverType"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机类型* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="driverType" name="driverType" required>
                        [#assign driverType = (driver.driverType!command.driverType)?default("") /]
                        <option value="">请选择</option>
                        <option value="GENERATION" [@mc.selected driverType "GENERATION"/]>代驾</option>
                        <option value="LIMOUSINE" [@mc.selected driverType "LIMOUSINE"/]>专车</option>
                        <option value="TAXI" [@mc.selected driverType "TAXI"/]>出租车</option>
                    </select>
                    [@spring.showErrors "driverType"/]
                </div>
            </div>

            <div class="form-group hidden">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 驾照类型* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="drivingLicenceType" name="drivingLicenceType">
                        [#assign drivingLicenceType = (driver.drivingLicenceType!command.drivingLicenceType)?default("") /]
                        <option value="">请选择</option>
                        <option value="A1" [@mc.selected drivingLicenceType "A1"/]>A1</option>
                        <option value="A2" [@mc.selected drivingLicenceType "A2"/]>A2</option>
                        <option value="B1" [@mc.selected drivingLicenceType "B1"/]>B1</option>
                        <option value="B2" [@mc.selected drivingLicenceType "B2"/]>B2</option>
                        <option value="C1" [@mc.selected drivingLicenceType "C1"/]>C1</option>
                        <option value="C2" [@mc.selected drivingLicenceType "C2"/]>C2</option>
                    </select>
                </div>
            </div>

            <div class="form-group hidden">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 行驶证* </label>

                <div class="col-sm-9">
                    <a class="btn btn-sm btn-primary left input-file-hidden"
                       id="travelPicUpload">点击上传照片
                    </a>
                    <input type="hidden" id="travelPic" name="travelPic" value="${driver.travelPic!command.travelPic}"
                           placeholder="行驶证" class="col-xs-10 col-sm-5" required/>
                    <p class="img-box">
                        [#if driver.travelPic??]
                            <img src="${driver.travelPic!command.travelPic}">
                            <button type="button" class="btn btn-danger del-img">删除</button>
                        [/#if]
                    </p>
                </div>
            </div>

            <div class="form-group hidden">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 营业资格证* </label>

                <div class="col-sm-9">
                    <a class="btn btn-sm btn-primary left input-file-hidden"
                       id="businessPicUpload">点击上传照片
                    </a>
                    <input type="hidden" id="businessPic" name="businessPic"
                           value="${driver.businessPic!command.businessPic}"
                           class="col-xs-10 col-sm-5" required/>
                    <p class="img-box">
                        [#if driver.businessPic??]
                            <img src="${driver.businessPic!command.businessPic}">
                            <button type="button" class="btn btn-danger del-img">删除</button>
                        [/#if]
                    </p>
                </div>
            </div>

            <div class="form-group hidden">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 从业资格证* </label>

                <div class="col-sm-9">
                    <a class="btn btn-sm btn-primary left input-file-hidden" id="workPicUpload">
                        点击上传照片
                    </a>
                    <input type="hidden" id="workPic" name="workPic" value="${driver.workPic!command.workPic}"
                           class="col-xs-10 col-sm-5" required/>
                    <p class="img-box">
                        [#if driver.workPic??]
                            <img src="${driver.workPic!command.workPic}">
                            <button type="button" class="btn btn-danger del-img">删除</button>
                        [/#if]
                    </p>
                </div>
            </div>

            [@spring.bind "command.bankCardNo"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 银行卡号* </label>

                <div class="col-sm-9">
                    <input type="text" id="bankCardNo" name="bankCardNo" value="${driver.bankCardNo!command.bankCardNo}"
                           placeholder="银行卡号" class="col-xs-10 col-sm-5" minlength="6" required/>
                    [@spring.showErrors "bankCardNo"/]
                </div>
            </div>

            [@spring.bind "command.bankName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 银行名称* </label>

                <div class="col-sm-9">
                    <input type="text" id="bankName" name="bankName" value="${driver.bankName!command.bankName}"
                           placeholder="银行名称" class="col-xs-10 col-sm-5" minlength="6" required/>
                    [@spring.showErrors "bankName"/]
                </div>
            </div>


            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        修改
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
<script src="[@spring.url '/resources/assets/app/js/driverCreate.js'/]"></script>
<script type="text/javascript">

    $.ajax({
        type: "post",
        url: "/user/company/all_list",
        dataType: "json",
        success: function (data) {
            $("#company").empty();
            $("#company").append("<option value=''>请选择</option>");
            $.each(data, function (a, b) {
                if (b.id == $("#company").attr("data-id")) {
                    $("#company").append("<option value='" + b.id + "' selected>" + b.userName + "</option>");
                } else {
                    $("#company").append("<option value='" + b.id + "'>" + b.userName + "</option>");
                }
            })
        }
    })

    var $travelPic = $("#travelPic");
    var $businessPic = $("#businessPic");
    var $workPic = $("#workPic");
    var $drivingLicenceType = $("#drivingLicenceType");
    bindChange();
    $("#driverType").change(function () {
        bindChange();
    })
    function bindChange() {
        var selected = $('#driverType option:selected').val();
        if (selected == "LIMOUSINE") {
            $travelPic.parent().parent().removeClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
            bindTravelPic();
        } else if (selected == "GENERATION") {
            $drivingLicenceType.parent().parent().removeClass("hidden");
            $travelPic.parent().parent().addClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
        } else if (selected == "TAXI") {
            $travelPic.parent().parent().removeClass("hidden");
            $businessPic.parent().parent().removeClass("hidden");
            $workPic.parent().parent().removeClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
            bindWorkPic();
            bindBusinessPic();
            bindTravelPic();
        } else {
            $travelPic.parent().parent().addClass("hidden");
            $businessPic.parent().parent().addClass("hidden");
            $workPic.parent().parent().addClass("hidden");
            $drivingLicenceType.parent().parent().addClass("hidden");
        }
    }

    $("#form-edit").submit(function () {
        if ($("#identityCardPic").val() == "") {
            layer.msg("身份证照片不能为空");
            return false;
        }
        if ($("#drivingLicencePic").val() == "") {
            layer.msg("驾驶证照片不能为空");
            return false;
        }
        if ($("#driverType").val() == "LIMOUSINE") {
            if ($travelPic.val() == "") {
                layer.msg("行驶证照片不能为空");
                return false;
            }
        } else if ($("#driverType").val() == "TAXI") {
            if ($travelPic.val() == "") {
                layer.msg("行驶证照片不能为空");
                return false;
            }
            if ($businessPic.val() == "") {
                layer.msg("营业证照片不能为空");
                return false;
            }
            if ($workPic.val() == "") {
                layer.msg("从业证照片不能为空");
                return false;
            }
        } else if ($("#driverType").val() == "GENERATION") {
            if ($("#drivingLicenceType").val() == "") {
                layer.msg("驾照类型不能为空");
                return false;
            }
        }
        return true;
    })
</script>
[/@override]

[@extends name="/decorator.ftl"/]


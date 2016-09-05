[@override name="title"]救援管理-修改救援信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/rescue/list'/]">权限管理</a></li>
<li class="active">修改救援</li>
[/@override]

[@override name="pageHeaderTitle"]
修改救援信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/rescue/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${rescue.id!command.id}" />
            <input type="hidden" name="version" value="${rescue.version!command.version}" />

            [@spring.bind "command.permissionName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 申请人* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="applyUser" value="${rescue.applyUser!command.applyUser}"
                           placeholder="申请人" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "applyUser"/]
                </div>
            </div>

            [@spring.bind "command.type"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 救援类型* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="type" value="${rescue.type!command.type}"
                           placeholder="申请人" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "type"/]
                </div>
            </div>

            [@spring.bind "command.description"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 救援说明* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="description" value="${rescue.description!command.description}"
                           placeholder="说明" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "description"/]
                </div>
            </div>

            [@spring.bind "command.driver"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 救援司机* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="driver" value="${rescue.driver!command.description}"
                           placeholder="司机" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "driver"/]
                </div>
            </div>

            [@spring.bind "command.status"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 救援状态* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="status" required>
                        [#assign status = (command.status!)?default("") /]
                        <option value="">请选择</option>
                        <option value="WAIT_RESCUE" [@mc.selected status "WAIT_RESCUE"/]>待救援</option>
                        <option value="IN_RESCUE" [@mc.selected status "IN_RESCUE"/]>救援中</option>
                        <option value="SUCCESS_RESCUE" [@mc.selected status "SUCCESS_RESCUE"/]>救援完成</option>
                    </select>
                    [@spring.showErrors "status"/]
                </div>
            </div>

            [@spring.bind "command.rescueTime"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 救援时间* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="rescueTime" value="${rescue.rescueTime!command.rescueTime}"
                           placeholder="救援时间" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "rescueTime"/]
                </div>
            </div>


            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit"><i class="icon-ok bigger-110"></i>修改</button>
                    <button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
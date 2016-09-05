[@override name="title"]权限管理-创建权限信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/permission/list'/]">权限管理</a></li>
<li class="active">创建权限</li>
[/@override]

[@override name="pageHeaderTitle"]
创建权限信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/permission/create" class="form-horizontal" method="post">

            [@spring.bind "command.permissionName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 权限名称* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="permissionName" value="${command.permissionName!}"
                           placeholder="权限名称" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "permissionName"/]
                </div>
            </div>

            [@spring.bind "command.description"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 描述* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="description" value="${command.description!}"
                           placeholder="描述" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "description"/]
                </div>
            </div>

            [@spring.bind "command.status"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 状态* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" name="status" required>
                        [#assign status = (command.status!)?default("") /]
                        <option value="">请选择</option>
                        <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>
                        <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>
                    </select>
                    [@spring.showErrors "status"/]
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
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
[@override name="title"]App版本管理-创建app版本[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/app_version/list'/]">App版本管理</a></li>
<li class="active">创建app版本</li>
[/@override]

[@override name="pageHeaderTitle"]
创建app版本
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/app_version/create" class="form-horizontal" method="post">

            [@spring.bind "command.appVersion"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> app版本号* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-appVersion" name="appVersion" value="${command.appVersion!}"
                           placeholder="版本号" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "appVersion"/]
                </div>
            </div>

            [@spring.bind "command.updateContent"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-updateContent"> app更新内容* </label>

                <div class="col-sm-9">
                    <textarea id="form-field-updateContent" name="updateContent" class="col-xs-10 col-sm-5" style="height: 200px;"
                              placeholder="app更新内容"></textarea>
                    [@spring.showErrors "updateContent"/]
                </div>
            </div>

            [@spring.bind "command.status"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-status"> 状态* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5 " name="status" id="form-field-status" required>
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
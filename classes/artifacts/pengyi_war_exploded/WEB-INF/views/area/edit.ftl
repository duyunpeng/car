[@override name="title"]区域管理-修改区域[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/area/list'/]">区域管理</a></li>
<li class="active">修改区域</li>
[/@override]

[@override name="pageHeaderTitle"]
修改区域
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/area/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${area.id!command.id}" />
            <input type="hidden" name="version" value="${area.version!command.version}" />

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 区域名* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="name" value="${area.name!command.name}"
                           placeholder="区域名" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "name"/]
                </div>
            </div>

            [@spring.bind "command.priority"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 区域优先级* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-password" name="priority" value="${area.priority!command.priority}"
                           placeholder="区域优先级" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "priority"/]
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 上级区域* </label>

                <div class="col-sm-4">
                    <div class="area_box">
                        <select class="col-xs-3 area_data">
                        </select>
                    </div>
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
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/app/js/area.js'/]"></script>
<script>
    $(".area_box").areaCascade();
</script>
[/@override]

[@extends name="/decorator.ftl"/]
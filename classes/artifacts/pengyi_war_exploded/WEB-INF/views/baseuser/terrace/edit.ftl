[@override name="title"]平台用户管理-修改公司用户[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/terrace/list'/]">平台用户管理</a></li>
<li class="active">修改平台用户</li>
[/@override]

[@override name="pageHeaderTitle"]
修改平台用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/user/terrace/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${terrace.id!command.id}" />
            <input type="hidden" name="version" value="${terrace.version!command.version}" />

            [@spring.bind "command.email"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 邮箱* </label>

                <div class="col-sm-9">
                    <input type="email" id="form-field-1" name="email" value="${terrace.email!command.email}"
                           placeholder="邮箱" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "email"/]
                </div>
            </div>

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 平台昵称* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="name" value="${terrace.name!command.name}"
                           placeholder="平台昵称" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "name"/]
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
<script>
</script>
[/@override]

[@extends name="/decorator.ftl"/]
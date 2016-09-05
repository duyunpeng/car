[@override name="title"]用户管理-重置用户密码用户[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/base_user/list'/]">用户管理</a></li>
<li class="active">重置用户密码用户</li>
[/@override]

[@override name="pageHeaderTitle"]
重置用户密码用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/base_user/reset_password" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${baseUser.id!command.id}"/>
            <input type="hidden" name="version" value="${baseUser.version!command.version}"/>

            [@spring.bind "command.password"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密码* </label>

                <div class="col-sm-9">
                    <input type="password" id="form-field-password" name="password" value="${command.password!}"
                           placeholder="密码" minlength="6" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "password"/]
                </div>
            </div>

            [@spring.bind "command.confirmPassword"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 确认密码* </label>

                <div class="col-sm-9">
                    <input type="password" id="form-field-confirmPassword" name="confirmPassword"
                           value="${command.confirmPassword!}"
                           placeholder="确认密码" onchange="checkPasswords()" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "confirmPassword"/]
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        修改
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

    function checkPasswords() {
        var pass1 = document.getElementById("form-field-password");
        var pass2 = document.getElementById("form-field-confirmPassword");

        if (pass1.value != pass2.value)
            pass1.setCustomValidity("两次输入的密码不匹配");
        else
            pass1.setCustomValidity("");
    }

</script>
[/@override]

[@extends name="/decorator.ftl"/]
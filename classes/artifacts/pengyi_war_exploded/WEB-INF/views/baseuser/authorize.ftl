[@override name="title"]用户管理-用户授权[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/base_user/list'/]">用户管理</a></li>
<li class="active">用户授权</li>
[/@override]

[@override name="pageHeaderTitle"]
用户授权
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/base_user/authorize" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${baseUser.id!command.id}"/>
            <input type="hidden" name="version" value="${baseUser.version!command.version}" />

            [@spring.bind "command.userRole"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户角色* </label>

                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="user-role" name="userRole" data-id="${baseUser.userRole.id!command.userRole!}">

                    </select>
                    [@spring.showErrors "userRole"/]
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        授权
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
    function initTypeDate(){
        var typeId = $("#user-role").attr("data-id");
        $.ajax({
            url: "/role/all_list",
            type: "POST",
            dataType: "JSON",
            success: function (result) {
                if (typeof result == 'object') {
                    result = result.data;
                } else {
                    result = JSON.parse(result.data);
                }

                $("#user-role").empty();
                $("#user-role").append("<option value=''>清空角色</option>");

                var typeData = result;
                $.each(typeData, function (index, data) {
                    if(data.id == typeId){
                        $("#user-role").append("<option value='"+data.id+"' selected>"+data.roleName+"</option>");
                    }else{
                        $("#user-role").append("<option value='"+data.id+"'>"+data.roleName+"</option>");
                    }
                });

                $("#user-role").trigger("chosen:updated");
            }
        })
    }
    initTypeDate();
</script>
[/@override]

[@extends name="/decorator.ftl"/]
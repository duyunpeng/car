[@override name="title"]权限管理-查看权限信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/permission/list'/]">权限管理</a></li>
<li class="active">查看权限</li>
[/@override]

[@override name="pageHeaderTitle"]
查看权限信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 权限名称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${permission.permissionName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 描述</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${permission.description!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(permission.status.getName())!}</span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/permission/create" class="btn btn-success btn-block">在创建一个</a>
            <a href="/permission/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
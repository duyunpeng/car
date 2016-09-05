[@override name="title"]角色管理-查看角色[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/role/list'/]">角色管理</a></li>
<li class="active">查看角色</li>
[/@override]

[@override name="pageHeaderTitle"]
查看角色
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 角色名称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${role.roleName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 描述</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${role.description!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(role.status.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 角色权限</div>

                <div class="profile-info-value">
                    <ul class="list-view">
                        [#if role.permissions!]
                            [#list role.permissions as permission]
                                <li>
                                    <span>${permission.permissionName!}</span>
                                    <span>${permission.description!}</span>
                                </li>
                            [/#list]
                        [/#if]
                    </ul>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/role/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
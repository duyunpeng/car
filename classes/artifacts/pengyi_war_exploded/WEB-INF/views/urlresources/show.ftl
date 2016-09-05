[@override name="title"]访问资源路径管理-查看访问资源路径信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/url_resources/list'/]">访问资源路径管理</a></li>
<li class="active">查看访问资源路径</li>
[/@override]

[@override name="pageHeaderTitle"]
查看访问资源路径信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 访问资源路径名称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${urlResources.urlName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 描述</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${urlResources.description!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(urlResources.status.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 权限</div>

                <div class="profile-info-value">
                    <ul class="list-view">
                        [#if urlResources.urlPermission!]
                            [#list urlResources.urlPermission as permission]
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
            <a href="/url_resources/create" class="btn btn-success btn-block">在创建一个</a>
            <a href="/url_resources/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
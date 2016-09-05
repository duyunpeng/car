[@override name="title"]区域管理-查看区域[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/area/list'/]">区域管理</a></li>
<li class="active">查看区域</li>
[/@override]

[@override name="pageHeaderTitle"]
查看区域
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 区域名称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${area.name!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 区域优先级</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${area.priority!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">[@mc.showCascade area.parent/]</span>
                </div>
            </div>

        <br>
        <br>
        <div class="col-xs-12">
            <a href="/area/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
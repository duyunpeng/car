[@override name="title"]平台用户管理-查看平台用户[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/terrace/list'/]">平台用户管理</a></li>
<li class="active">查看平台用户</li>
[/@override]

[@override name="pageHeaderTitle"]
查看平台用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 用户名</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${terrace.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 余额</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${terrace.balance!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(terrace.status.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(terrace.userType.getName())!} 用户</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 邮箱</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${terrace.email!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户角色</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${terrace.userRole.roleName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 创建时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(terrace.createDate)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 平台昵称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(terrace.name)!}</span>
                </div>
            </div>

        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/user/terrace/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
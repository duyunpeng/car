[@override name="title"]用户(user)管理-查看用户(user)[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/list'/]">用户(user)管理</a></li>
<li class="active">查看用户(user)</li>
[/@override]

[@override name="pageHeaderTitle"]
查看用户(user)
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 用户名</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${user.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 余额</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${user.balance!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(user.status.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(user.userType.getName())!} 用户</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 邮箱</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${user.email!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户角色</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${user.userRole.roleName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 创建时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(user.createDate)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户昵称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(user.name)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 性别</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(user.sex.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 积分</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${user.integral!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 举报次数</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${user.reportCount!}</span>
                </div>
            </div>

        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/user/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
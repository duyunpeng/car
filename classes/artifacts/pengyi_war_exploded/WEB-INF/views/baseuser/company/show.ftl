[@override name="title"]公司用户管理-查看公司用户[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/company/list'/]">公司用户管理</a></li>
<li class="active">查看公司用户</li>
[/@override]

[@override name="pageHeaderTitle"]
查看公司用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 用户名</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${company.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 余额</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${company.balance!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(company.status.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(company.userType.getName())!} 用户</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 邮箱</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${company.email!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户角色</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${company.userRole.roleName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 创建时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(company.createDate)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 公司名称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(company.name)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 公司注册时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(company.registerDate)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 公司注册地点</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">[@mc.showCascade company.registerAddress/]</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 公司注册地点</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">[@mc.showCascade company.operateAddress/]</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 公司等级</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${company.level!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 公司资质图片</div>

                <div class="profile-info-value">
                    <span class="editable editable-click img-box"
                          id="age"><img src="${company.folder!}"/></span>
                </div>
            </div>


        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/user/company/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
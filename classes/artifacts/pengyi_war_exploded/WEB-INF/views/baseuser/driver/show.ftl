[@override name="title"]司机用户管理-查看司机用户[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/user/driver/list'/]">司机用户管理</a></li>
<li class="active">查看司机用户</li>
[/@override]

[@override name="pageHeaderTitle"]
查看司机用户
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 用户名</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${driver.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 余额</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${driver.balance!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(driver.status.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(driver.userType.getName())!} 用户</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 邮箱</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${driver.email!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 用户角色</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${driver.userRole.roleName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 创建时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(driver.createDate)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 司机名称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(driver.name)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 所属公司</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(driver.company.name)!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 性别</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(driver.sex.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 等级</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${driver.level!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 举报次数</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${driver.reportCount!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 是否在线</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${driver.online!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 司机类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${(driver.driverType.getName())!}</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 身份证照片</div>

                <div class="profile-info-value">
                    <span class="editable editable-click img-box"
                          id="age"><img src="${driver.identityCardPic!}"/></span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 驾驶证照片</div>

                <div class="profile-info-value">
                    <span class="editable editable-click img-box"
                          id="age"><img src="${driver.drivingLicencePic!}"/></span>
                </div>
            </div>
            [#if driver.driverType == "LIMOUSINE"]
                <div class="profile-info-row">
                    <div class="profile-info-name"> 行驶证</div>

                    <div class="profile-info-value">
                    <span class="editable editable-click img-box"
                          id="age"><img src="${driver.travelPic!}"/></span>
                    </div>
                </div>
            [#elseif driver.driverType == "GENERATION"]
                <div class="profile-info-row">
                    <div class="profile-info-name"> 驾照类型</div>

                    <div class="profile-info-value">
                    <span class="editable editable-click img-box"
                          id="age">${driver.drivingLicenceType!}</span>
                    </div>
                </div>
            [#else]
                <div class="profile-info-row">
                    <div class="profile-info-name"> 行驶证</div>

                    <div class="profile-info-value">
                    <span class="editable editable-click img-box"
                          id="age"><img src="${driver.travelPic!}"/></span>
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name"> 营业资格证</div>

                    <div class="profile-info-value">
                    <span class="editable editable-click img-box"
                          id="age"><img src="${driver.businessPic!}"/></span>
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name"> 从业资格证</div>

                    <div class="profile-info-value">
                    <span class="editable editable-click img-box"
                          id="age"><img src="${driver.workPic!}"/></span>
                    </div>
                </div>
            [/#if]

            <div class="profile-info-row">
                <div class="profile-info-name"> 银行卡号</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${driver.bankCardNo!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 银行名称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">${driver.bankName!}</span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="${returnPath!}" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
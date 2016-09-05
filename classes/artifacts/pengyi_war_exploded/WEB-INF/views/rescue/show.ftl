[@override name="title"]救援管理-查看救援信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/rescue/list'/]">救援管理</a></li>
<li class="active">查看救援</li>
[/@override]

[@override name="pageHeaderTitle"]
查看救援信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 申请人</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="name">${rescueRepresentation.applyUser.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 申请时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${rescueRepresentation.applyTime!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 救援类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${rescueRepresentation.type!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 救援说明</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${rescueRepresentation.description!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 救援司机</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${rescueRepresentation.driver.userName!}</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 救援时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${rescueRepresentation.rescueTime!}</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 救援状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(rescueRepresentation.status.getName())!}</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 完成时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${rescueRepresentation.finishTime!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 公司资质图片</div>

                <div class="profile-info-value">
                    <span class="editable editable-click"
                          id="age">
                        [#list rescueRepresentation.images!?split(",") as image]
                            <img src="${image!}"/>
                        [/#list]
                    </span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/rescue/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
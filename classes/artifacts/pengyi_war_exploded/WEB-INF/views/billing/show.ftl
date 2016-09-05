[@override name="title"]计费管理-查看计费信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/billing/list'/]">计费管理</a></li>
<li class="active">查看计费</li>
[/@override]

[@override name="pageHeaderTitle"]
查看计费信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 根据公里计费</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${billing.kmBilling!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 起步公里</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${billing.startKm!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 根据分钟计费</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${billing.minuteBilling!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 起步分钟</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${billing.startMin!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 起步价</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${billing.startingPrice!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 所属公司</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${billing.company.name!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 司机类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${(billing.driverType.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${(billing.status.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 车辆类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click">${(billing.carType.getName())!}</span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/billing/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
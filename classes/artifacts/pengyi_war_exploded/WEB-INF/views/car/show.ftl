[@override name="title"]车辆管理-查看车辆信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/car/list'/]">车辆管理</a></li>
<li class="active">查看车辆</li>
[/@override]

[@override name="pageHeaderTitle"]
查看车辆信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 车辆名称</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="name">${car.name!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 车牌号</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${car.carNumber!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 司机</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(car.driver.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${(car.carType.getName())!}</span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/car/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
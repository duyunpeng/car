[@override name="title"]订单管理-订单详情[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/order/list'/]">订单管理</a></li>
<li class="active">订单详情</li>
[/@override]

[@override name="pageHeaderTitle"]
订单详情
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 订单号</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.orderNumber!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 下单人</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.orderUser.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 接单人</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.receiveUser.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 下单时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.createDate!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 接单时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.receiveDate!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 预约时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.subscribeDate!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 开始时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.beginTime!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 订单类型</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${(order.driverType.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 订单完成时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.endTime!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name">公里数</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.km!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 应付</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.shouldMoney!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 调度费</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.extraMoney!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 支付时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${order.payTime!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 订单状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${(order.orderStatus.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 评价状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${(order.evaluateStatus.getName())!}</span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/order/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
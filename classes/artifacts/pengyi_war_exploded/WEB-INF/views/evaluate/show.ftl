[@override name="title"]评价管理-查看评价信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/evaluate/list'/]">评价管理</a></li>
<li class="active">查看评价</li>
[/@override]

[@override name="pageHeaderTitle"]
查看评价信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 评价人</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="evaluateUser">${evaluate.evaluateUser.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 订单</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="order">${evaluate.order.id!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 评价内容</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="content">${evaluate.content!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 评级</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="level">${evaluate.level!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="createDate">${evaluate.createDate!}</span>
                </div>
            </div>

        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/evaluate/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
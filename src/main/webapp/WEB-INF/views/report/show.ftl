[@override name="title"]举报管理-查看举报信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/report/list'/]">举报管理</a></li>
<li class="active">查看举报信息</li>
[/@override]

[@override name="pageHeaderTitle"]
查看举报信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 举报人</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${report.reportUser.userName!}</span>
                </div>
            </div>


            <div class="profile-info-row">
                <div class="profile-info-name"> 举报订单</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${report.order.orderNumber!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 举报时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${report.reportTime!}</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 说明</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${report.description!}</span>
                </div>
            </div>


            <div class="profile-info-row">
                <div class="profile-info-name"> 处理结果</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${report.handleResult!}</span>
                </div>
            </div>


        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/report/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
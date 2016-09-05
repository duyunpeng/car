[@override name="title"]资金流向管理-查看资金流向[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/money_detailed/list'/]">资金流向管理</a></li>
<li class="active">查看资金流向</li>
[/@override]

[@override name="pageHeaderTitle"]
查看资金流向
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 用户名</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${moneyDetailed.baseUser.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 资金流向</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${(moneyDetailed.flowType.getName())!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 金额</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${moneyDetailed.money!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 说明</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${moneyDetailed.description!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 原有金额</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${moneyDetailed.oldMoney!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 现有金额</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${moneyDetailed.newMoney!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 创建时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${moneyDetailed.createDate!}</span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/money_detailed/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
[@override name="title"]扣款管理-扣款详情[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/withhold/list'/]">扣款管理</a></li>
<li class="active">扣款详情</li>
[/@override]

[@override name="pageHeaderTitle"]
扣款详情
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 被扣款人</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${withhold.username!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 扣款时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="createTime">${withhold.createTime!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 金额</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="money">${withhold.money!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 详情</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="detail">${withhold.detail!}</span>
                </div>
            </div>

        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/withhold/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
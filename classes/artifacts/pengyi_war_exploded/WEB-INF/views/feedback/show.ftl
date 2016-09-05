[@override name="title"]意见反馈管理-查看意见反馈[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/feed_back/list'/]">意见反馈管理</a></li>
<li class="active">查看意见反馈</li>
[/@override]

[@override name="pageHeaderTitle"]
查看意见反馈
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 联系邮箱</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${feedBack.email!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 联系电话</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${feedBack.phone!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 联系QQ</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${feedBack.qq!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 意见内容</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${feedBack.content!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 创建时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${feedBack.createDate!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 处理状态</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${(feedBack.status.getName())!}</span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/feed_back/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
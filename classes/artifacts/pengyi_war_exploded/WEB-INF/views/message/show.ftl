[@override name="title"]站内信息管理-查看站内信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/message/list'/]">信息管理</a></li>
<li class="active">查看信息</li>
[/@override]

[@override name="pageHeaderTitle"]
查看站内信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name"> 发件人</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="username">${message.sendBaseUser.userName!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 内容</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="country">${message.content!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name">发送时间</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${message.sendDate!}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name">接收人</div>

                <div class="profile-info-value">
                    <span class="editable editable-click" id="age">${message.receiveBaseUser.userName!}</span>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="col-xs-12">
            <a href="/message/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
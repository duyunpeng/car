[@override name="title"]站内信息-信息列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css" href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">站内信息管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
站内信息列表
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="table-responsive">
            <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                <!-- 查询条件 -->
                <div class="row">
                    <form>
                        <div class="col-sm-6">
                            <div id="sample-table-2_length" class="dataTables_length">
                                <label>
                                    开始<input type="text" value="${command.beginTime!}" id="beginTime" name="beginTime" />
                                </label>
                                <label>
                                    结束<input type="text" value="${command.endTime!}" id="endTime" name="endTime" />
                                </label>
                                <label>发送人<input type="text" value="${command.sendBaseUser!}" name="sendBaseUser"/></label>
                                <label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>
                            </div>
                        </div>
                    </form>
                </div>
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>+
                    <tr role="row">
                        <th>发送人</th>
                        <th>内容</th>
                        <th>接收人</th>
                        <th>接收人角色</th>
                        <th>发送时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as message ]
                            <tr class="even">
                                <td>${message.sendBaseUser.userName!}</td>
                                <td>${message.content!}</td>
                                <td>${message.receiveBaseUser.userName}</td>
                                <td>${message.receiveBaseUser.userRole.roleName}</td>
                                <td>${message.sendDate!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/message/show/${message.id!}'/]">查看</a>
                                            </li>
                                            <li>
                                                <a class="green" href="[@spring.url '/message/delete/${message.id}'/]">删除</a>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/message/list?beginTime=${command.beginTime!}&endTime=${command.endTime!}&sendBaseUser=${command.sendBaseUser!}' /]
                [/#if]

            </div>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">
    $.datetimepicker.setLocale('en');
    $('#beginTime').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
    });
    $('#endTime').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]
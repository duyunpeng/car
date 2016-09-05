[@override name="title"]举报管理-举报列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css" href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">举报管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
举报信息列表
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
                                <label>开始<input type="text" value="${command.startDealTime!}" id="startDealTime" name="startDealTime"/></label>
                                <label>结束<input type="text" value="${command.endDealTime!}" id="endDealTime" name="endDealTime"/></label>
                                <label>处理状态
                                    <select name="status">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="PENDING" [@mc.selected status "PENDING"/]>待处理</option>
                                        <option value="IN_PROCESS" [@mc.selected status "IN_PROCESS"/]>正在处理</option>
                                        <option value="FIGURE_OUT" [@mc.selected status "FIGURE_OUT"/]>处理完成</option>
                                    </select>
                                </label>
                                <label>
                                    <button type="submit" class="btn btn-app btn-sm btn-success">查询</button>
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>举报人</th>
                        <th>举报订单</th>
                        <th>举报时间</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as report ]
                            <tr class="even">
                                <td>${report.reportUser.userName!}</td>
                                <td>${report.order.orderNumber!}</td>
                                <td>${report.reportTime!}</td>
                                <td>${(report.status.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue"
                                                   href="[@spring.url '/report/show/${report.id!}'/]">查看</a>
                                            </li>
                                            [#if report.status == "PENDING"]
                                                <li>
                                                    <a class="blue" href="[@spring.url '/report/handle_report?id=${report.id}&version=${report.version}'/]">开始处理</a>
                                                </li>
                                            [#elseif report.status == "IN_PROCESS"]
                                                <li>
                                                    <a class="blue" id="success-process" data-id="${report.id!}"
                                                       data-version="${report.version!}"
                                                       data-orderNo="${report.order.orderNumber!}">完成处理</a>
                                                </li>
                                            [/#if]
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/report/list?beginTime=${command.beginTime!}&endTime=${command.endTime}&status=${command.status!}' /]
                [/#if]

            </div>
        </div>
    </div>
</div>

<div class="modal" id="modalSearch">
    <div class="large-dialog">
        <div class="change-bg">
            <div class="modal-header">
                <p class="modal-title thin">填写举报处理结果
                    <small class="text-muted"></small>
                </p>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form class="form-horizontal" action="/report/success_report" id="from-edit" method="post">
                            <input type="hidden" name="id" value=""/>
                            <input type="hidden" name="version" value=""/>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                    举报订单 </label>

                                <div class="col-sm-9">
                                    <input class="form-control" name="orderNo" value="" disabled/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                    处理结果* </label>

                                <div class="col-sm-9">
                                    <textarea type="text" style="height: 150px;" name="handleResult"
                                              class="form-control" required></textarea>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-4">
                                    <button class="btn btn-info" type="submit">
                                        <i class="icon-ok bigger-110"></i>
                                        提交
                                    </button>
                                    <button class="btn" type="button" data-dismiss="modal">
                                        <i class="icon-undo bigger-110"></i>
                                        关闭
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">
    $("#success-process").click(function () {
        var id = $(this).attr("data-id");
        var version = $(this).attr("data-version");
        var orderNo = $(this).attr("data-orderNo");
        $("#from-edit").find("input[name='id']").val(id);
        $("#from-edit").find("input[name='version']").val(version);
        $("#from-edit").find("input[name='orderNo']").val(orderNo);
        $("#modalSearch").modal();
    });

    $.datetimepicker.setLocale('en');
    $('#startDealTime').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
    });
    $('#endDealTime').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]
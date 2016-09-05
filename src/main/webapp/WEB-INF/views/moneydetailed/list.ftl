[@override name="title"]资金流向管理-资金流向列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css"
      href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">资金流向列表</li>
[/@override]

[@override name="pageHeaderTitle"]
资金流向列表
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="table-responsive">
            <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                <!-- 查询条件 -->
                <div class="row">
                    <form role="form">
                        <div class="col-sm-6">
                            <div id="sample-table-2_length" class="dataTables_length">
                                <label>
                                    用户名<input type="text" value="${command.userName!}" name="userName"/>
                                </label>
                                <label>
                                    开始<input type="text" value="${command.startTime!}" id="startTime" name="startTime"/>
                                </label>
                                <label>
                                    结束<input type="text" value="${command.endTime!}" id="endTime" name="endTime"/>
                                </label>

                                <label>
                                    <button type="submit" class="btn btn-app btn-sm btn-success">查询</button>
                                </label>
                                <label>
                                    <a href="/money_detailed/export_excel?userName=${command.userName!}&startTime=${command.startTime!}&endTime=${command.endTime!}"
                                       class="btn-sm btn-success">导出表格</a>
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                <thead>
                <tr role="row">
                    <th>用户名</th>
                    <th>资金流向</th>
                    <th>金额</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody role="alert" aria-live="polite" aria-relevant="all">
                    [#if pagination.data??]
                        [#list pagination.data as moneyDetailed ]
                        <tr class="even">
                            <td>${moneyDetailed.baseUser.userName!}</td>
                            <td>${(moneyDetailed.flowType.getName())!}</td>
                            <td>${moneyDetailed.money!}</td>
                            <td>${moneyDetailed.createDate!}</td>
                            <td>
                                <div class="btn-group">
                                    <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                        操作
                                        <i class="icon-angle-down icon-on-right"></i>
                                    </button>

                                    <ul class="dropdown-menu">
                                        <li>
                                            <a class="blue"
                                               href="[@spring.url '/money_detailed/show/${moneyDetailed.id!}'/]">查看</a>
                                        </li>
                                    [#--<li>--]
                                    [#--<a class="green" href="[@spring.url '/role/edit/${role.id}'/]">编辑</a>--]
                                    [#--</li>--]
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        [/#list]
                    [/#if]
                </tbody>
            </table>
            [#if pagination??]
                [@mc.showPagination '/money_detailed/list?userName=${command.userName!}&startTime=${command.startTime!}&endTime=${command.endTime!}' /]
            [/#if]
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">
    $.datetimepicker.setLocale('en');
    $('#startTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#endTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]
[@override name="title"]扣款管理-扣款列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css" href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">扣款管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
扣款列表
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
                                    开始<input type="text" value="${command.startTime!}" id="startTime" name="startTime" />
                                </label>
                                <label>
                                    结束<input type="text" value="${command.endTime!}" id="endTime" name="endTime" />
                                </label>
                                <label>被扣款人<input type="text" value="${command.baseUser!}" name="baseUser"/></label>
                                <label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>
                            </div>
                        </div>
                    </form>
                </div>
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>被扣款人</th>
                        <th>扣款时间</th>
                        <th>金额</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as withhold ]
                            <tr class="even">
                                <td>${withhold.username!}</td>
                                <td>${withhold.createTime!}</td>
                                <td>${withhold.money!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/withhold/show/${withhold.id!}'/]">详情</a>
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
                    [@mc.showPagination '/withhold/list?startTime=${command.startTime!}&endTime=${command.endTime!}&baseUser=${command.baseUser!}' /]
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
    $('#startTime').datetimepicker({
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
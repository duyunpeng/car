[@override name="title"]App版本-App版本列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css" href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">App版本列表</li>
[/@override]

[@override name="pageHeaderTitle"]
App版本列表
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
                                <label>app版本号<input type="text" value="${command.appVersion!}" name="appVersion" /></label>
                                <label>
                                    更新时间<input type="text" value="${command.startUpdateDate!}" id="startUpdateDate" name="startUpdateDate" />
                                </label>
                                <label>
                                    到<input type="text" value="${command.endUpDateDate!}" id="endUpDateDate" name="endUpDateDate" />
                                </label>
                                <label>状态
                                    <select name="status">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>
                                        <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>
                                    </select>
                                </label>
                                <label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>
                            </div>
                        </div>
                    </form>
                </div>
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>app版本号</th>
                        <th>更新时间</th>
                        <th>更新内容</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as appVersion ]
                            <tr class="even">
                                <td>${appVersion.appVersion!}</td>
                                <td>${appVersion.updateDate!}</td>
                                <td>${appVersion.updateContent!}</td>
                                <td>${(appVersion.status.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                [#if appVersion.status == "ENABLE"]
                                                    <a class="red"
                                                       href="[@spring.url '/app_version/update_status?id=${appVersion.id!}&version=${appVersion.version!}'/]">
                                                        禁用
                                                    </a>
                                                [#else ]
                                                    <a class="red"
                                                       href="[@spring.url '/app_version/update_status?id=${appVersion.id!}&version=${appVersion.version!}'/]">
                                                        启用
                                                    </a>
                                                [/#if]
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
                    [@mc.showPagination '/app_version/list?appVersion=${command.appVersion!}&startUpdateDate=${command.startUpdateDate!}&endUpDateDate=${command.endUpDateDate!}$status=${command.status!}' /]
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
    $('#startUpdateDate').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
    });
    $('#endUpDateDate').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]
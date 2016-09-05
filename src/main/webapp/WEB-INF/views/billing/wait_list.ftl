[@override name="title"]计费-计费列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">计费管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
计费信息列表
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="table-responsive">
            <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                <!-- 查询条件 -->
                <div class="row">
                [#--<form>--]
                        [#--<div class="col-sm-6">--]
                            [#--<div id="sample-table-2_length" class="dataTables_length">--]
                                [#--<label>根据公里计费<input type="text" value="${command.kmBilling!}" name="kmBilling" /></label>--]
                                [#--<label>根据分钟计费<input type="text" value="${command.minuteBilling!}" name="minuteBilling" /></label>--]
                                [#--<label>区域<input type="text" value="${command.area!}" name="area" /></label>--]
                                [#--<label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>--]
                            [#--</div>--]
                        [#--</div>--]
                    [#--</form>--]
                </div>
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>根据公里计费</th>
                        <th>起步公里</th>
                        <th>根据分钟计费</th>
                        <th>起步分钟</th>
                        <th>起步价</th>
                        <th>公司</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as billing ]
                            <tr class="even">
                                <td>${billing.kmBilling!}</td>
                                <td>${billing.startKm!}</td>
                                <td>${billing.minuteBilling!}</td>
                                <td>${billing.startMin!}</td>
                                <td>${billing.startingPrice!}</td>
                                <td>${billing.company.name!}</td>
                                <td>${(billing.status.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue"
                                                   href="[@spring.url '/billing/show/${billing.id!}'/]">查看</a>
                                            </li>
                                        [#--<li>--]
                                        [#--<a class="green"--]
                                        [#--href="[@spring.url '/billing/edit/${billing.id}'/]">编辑</a>--]
                                        [#--</li>--]

                                            <li>
                                                [#if billing.status == "ENABLE"]
                                                    <a href="[@spring.url '/billing/wait_update_status?id=${billing.id!}&version=${billing.version!}'/]"
                                                       data-toggle="tooltip" data-placement="top" title="点击禁用此数据">
                                                        <span class="label label-danger">禁用</span>
                                                    </a>
                                                [#else]
                                                    <a href="[@spring.url '/billing/wait_update_status?id=${billing.id!}&version=${billing.version!}'/]"
                                                       data-toggle="tooltip" data-placement="top" title="点击启用此数据">
                                                        <span class="label label-danger">启用</span>
                                                    </a>
                                                [/#if]
                                            </li>

                                        </ul>
                                    </div>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/billing/wait_list?kmBilling=${command.kmBilling!}&minuteBilling=${command.minuteBilling!}&area=${car.area}' /]
                [/#if]

            </div>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
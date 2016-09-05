[@override name="title"]区域管理-区域列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">区域列表</li>
[/@override]

[@override name="pageHeaderTitle"]
区域列表
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
                                <label>区域名称<input type="text" value="${command.name!}" name="name" /></label>
                                <label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>
                            </div>
                        </div>
                    </form>
                </div>
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>区域名称</th>
                        <th>区域优先级</th>
                        <th>父级区域</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as area ]
                            <tr class="even">
                                <td>${area.name!}</td>
                                <td>${area.priority!}</td>
                                <td>${area.parent.name!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/area/show/${area.id!}'/]">查看</a>
                                            </li>
                                            <li>
                                                <a class="green" href="[@spring.url '/area/edit/${area.id}'/]">编辑</a>
                                            </li>
                                            [#--<li>--]
                                                [#--[#if role.status == "ENABLE"]--]
                                                    [#--<a class="red" href="[@spring.url '/role/update_status?id=${role.id!}&version=${role.version!}'/]">--]
                                                        [#--禁用--]
                                                    [#--</a>--]
                                                [#--[#else ]--]
                                                    [#--<a class="red" href="[@spring.url '/role/update_status?id=${role.id!}&version=${role.version!}'/]">--]
                                                        [#--启用--]
                                                    [#--</a>--]
                                                [#--[/#if]--]
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
                    [@mc.showPagination '/area/list?name=${command.name!}' /]
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
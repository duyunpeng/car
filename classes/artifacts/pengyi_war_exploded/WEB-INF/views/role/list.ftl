[@override name="title"]角色管理-角色列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">角色列表</li>
[/@override]

[@override name="pageHeaderTitle"]
角色列表
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
                                <label>角色名称<input type="text" value="${command.roleName!}" name="roleName" /></label>
                                <label>角色状态
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
                        <th>角色名称</th>
                        <th>角色描述</th>
                        <th>角色状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as role ]
                            <tr class="even">
                                <td>${role.roleName!}</td>
                                <td>${role.description!}</td>
                                <td>${(role.status.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/role/show/${role.id!}'/]">查看</a>
                                            </li>
                                            <li>
                                                <a class="green" href="[@spring.url '/role/edit/${role.id}'/]">编辑</a>
                                            </li>
                                            <li>
                                                [#if role.status == "ENABLE"]
                                                    <a class="red" href="[@spring.url '/role/update_status?id=${role.id!}&version=${role.version!}'/]">
                                                        禁用
                                                    </a>
                                                [#else ]
                                                    <a class="red" href="[@spring.url '/role/update_status?id=${role.id!}&version=${role.version!}'/]">
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
                    [@mc.showPagination '/role/list?roleName=${command.roleName!}&status=${command.status!}' /]
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
[@override name="title"]司机用户管理-待审核司机用户列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">待审核司机用户列表</li>
[/@override]

[@override name="pageHeaderTitle"]
待审核司机用户列表
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="table-responsive">
            <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
                <!-- 查询条件 -->
            [#--<div class="row">--]
            [#--<form>--]
            [#--<div class="col-sm-6">--]
            [#--<div id="sample-table-2_length" class="dataTables_length">--]
            [#--<label>用户名<input type="text" value="${command.userName!}" name="userName" /></label>--]
            [#--<label>公司名<input type="text" value="${command.name!}" name="name" /></label>--]
            [#--<label>用户状态--]
            [#--<select name="status">--]
            [#--[#assign status = (command.status!)?default("") /]--]
            [#--<option value="">全部</option>--]
            [#--<option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>--]
            [#--<option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>--]
            [#--</select>--]
            [#--</label>--]
            [#--<label><button type="submit" class="btn btn-app btn-sm btn-success">查询</button></label>--]
            [#--</div>--]
            [#--</div>--]
            [#--</form>--]
            [#--</div>--]
                <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable text-center">
                    <thead>
                    <tr role="row">
                        <th>用户名</th>
                        <th>公司名称</th>
                        <th>创建时间</th>
                        <th>用户类型</th>
                        <th>司机类型</th>
                        <th>审核状态</th>
                        <th>用户状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as driver ]
                            <tr class="even">
                                <td>${driver.userName!}</td>
                                <td>${driver.company.name!}</td>
                                <td>${driver.createDate!}</td>
                                <td>${(driver.userType.getName())!}</td>
                                <td>${(driver.driverType.getName())!}</td>
                                <td>${(driver.authStatus.getName())!}</td>
                                <td>${(driver.status.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/user/driver/show/${driver.id!}'/]">查看详情</a>
                                            </li>
                                            <li>
                                                <a class="pink" href="[@spring.url '/user/driver/auth/?id=${driver.id!}&version=${driver.version!}'/]">审核通过</a>
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
                    [@mc.showPagination '/user/driver/auth_list' /]
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
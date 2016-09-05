[@override name="title"]用户管理-用户列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">用户列表</li>
[/@override]

[@override name="pageHeaderTitle"]
用户列表
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
                                <label>用户名<input type="text" value="${command.userName!}" name="userName"/></label>
                                <label>用户状态
                                    <select name="status">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>
                                        <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>
                                    </select>
                                </label>
                                <label>用户类型
                                    <select name="userType">
                                        [#assign status = (command.userType!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="TERRACE" [@mc.selected status "TERRACE"/]>平台</option>
                                        <option value="USER" [@mc.selected status "USER"/]>用户</option>
                                        <option value="COMPANY" [@mc.selected status "COMPANY"/]>公司</option>
                                        <option value="DRIVER" [@mc.selected status "DRIVER"/]>司机</option>
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
                        <th>用户名</th>
                        <th>余额</th>
                        <th>创建时间</th>
                        <th>邮箱</th>
                        <th>用户类型</th>
                        <th>用户状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as baseUser ]
                            <tr class="even">
                                <td>${baseUser.userName!}</td>
                                <td>${baseUser.balance!}</td>
                                <td>${baseUser.createDate!}</td>
                                <td>${baseUser.email!}</td>
                                <td>${(baseUser.userType.getName())!}</td>
                                <td>${(baseUser.status.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/base_user/show/${baseUser.id!}'/]">查看</a>
                                            </li>
                                            <li>
                                                <a class="orange"
                                                   href="[@spring.url '/base_user/authorize/${baseUser.id!}'/]">授权</a>
                                            </li>
                                            <li>
                                                <a class="pink"
                                                   href="[@spring.url '/base_user/reset_password/${baseUser.id!}'/]">重置密码</a>
                                            </li>
                                            <li>
                                                <a class="red" href="[@spring.url '/withhold/create/${baseUser.id!}'/]">扣款</a>
                                            </li>
                                            <li>
                                                [#if baseUser.status == "ENABLE"]
                                                    <a class="red"
                                                       href="[@spring.url '/base_user/update_status?id=${baseUser.id!}&version=${baseUser.version!}'/]">
                                                        禁用
                                                    </a>
                                                [#else ]
                                                    <a class="red"
                                                       href="[@spring.url '/base_user/update_status?id=${baseUser.id!}&version=${baseUser.version!}'/]">
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
                    [@mc.showPagination '/base_user/list?userName=${command.userName!}&status=${command.status!}&userType=${command.userType!}' /]
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
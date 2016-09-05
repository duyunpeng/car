[@override name="title"]意见反馈管理-意见反馈列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">意见反馈列表</li>
[/@override]

[@override name="pageHeaderTitle"]
意见反馈列表
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
                        <div class="col-sm-8">
                            <div id="sample-table-2_length" class="dataTables_length">
                                <label>邮箱<input type="text" value="${command.email!}" name="email" /></label>

                                <label>电话<input type="text" value="${command.phone!}" name="phone" /></label>

                                <label>QQ<input type="text" value="${command.qq!}" name="qq" /></label>

                                <label>处理状态
                                    <select name="status">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="WAIT_HANDLE" [@mc.selected status "WAIT_HANDLE"/]>待处理</option>
                                        <option value="OK_HANDLE" [@mc.selected status "OK_HANDLE"/]>处理完成</option>
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
                        <th>联系邮箱</th>
                        <th>联系电话</th>
                        <th>意见内容</th>
                        <th>处理状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as feedBack ]
                            <tr class="even">
                                <td>${feedBack.email!}</td>
                                <td>${feedBack.phone!}</td>
                                <td>${feedBack.content!}</td>
                                <td>${(feedBack.status.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/feed_back/show/${feedBack.id!}'/]">查看</a>
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
                    [@mc.showPagination '/feed_back/list?email=${command.email!}&phone=${command.phone!}&qq=${command.qq}&${command.status!}' /]
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
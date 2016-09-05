[@override name="title"]评价管理-评价列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">评价管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
评价信息列表
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
                                <label>订单<input type="text" value="${command.order!}" name="order"/></label>
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
                        <th>评价人</th>
                        <th>订单</th>
                        <th>评价内容</th>
                        <th>评级</th>
                        <th>评价时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as evaluate ]
                            <tr class="even">
                                <td>${evaluate.evaluateUser.userName!}</td>
                                <td>${evaluate.order.orderNumber!}</td>
                                <td>${evaluate.content!}</td>
                                <td>${evaluate.level!}</td>
                                <td>${evaluate.createDate!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/evaluate/show/${evaluate.id!}'/]">查看</a>
                                            </li>
                                            [#--<li>--]
                                                [#--<a class="green" href="[@spring.url '/evaluate/edit/${evaluate.id}'/]">编辑</a>--]
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
                    [@mc.showPagination '/evaluate/list?evaluateUserId=${command.evaluateUser!}&orderId=${command.order!}' /]
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
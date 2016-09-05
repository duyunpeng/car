[@override name="title"]充值记录列表[/@override]

[@override name="topResources"]
    [@super /]
<link rel="stylesheet" type="text/css"
      href="[@spring.url '/resources/assets/js/datetimepicker/jquery.datetimepicker.css'/]"/>
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">充值记录列表</a></li>
[/@override]

[@override name="pageHeaderTitle"]
充值记录列表
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
                                <label>充值用户名<input type="text" value="${command.userName!}" name="userName"/></label>
                                <label>
                                    创建时间<input type="text" value="${command.startCreateDate!}" id="startCreateDate"
                                               name="startCreateDate"/>
                                </label>
                                <label>
                                    到<input type="text" value="${command.endCreateDate!}" id="endCreateDate"
                                            name="endCreateDate"/>
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
                        <th>充值账户</th>
                        <th>创建时间</th>
                        <th>充值金额</th>
                        <th>支付时间</th>
                        <th>支付方式</th>
                        <th>支付号</th>
                        <th>是否支付</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as recharge ]
                            <tr class="even">
                                <td>${recharge.user.userName!}</td>
                                <td>${recharge.createTime?datetime}</td>
                                <td>${recharge.money!}</td>
                                <td>[@mc.dateShow recharge.payTime /]</td>
                                <td>${(recharge.payType.getName())!}</td>
                                <td>${recharge.payNo!}</td>
                                <td>${recharge.payed?string("已支付","未支付")}</td>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/recharge/list?userName=${command.userName!}&startCreateDate=${command.startCreateDate!}&endCreateDate=${command.endCreateDate!}' /]
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
    $('#startCreateDate').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#endCreateDate').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
</script>
[/@override]

[@extends name="/decorator.ftl"/]
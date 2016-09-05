[@override name="title"]车辆-车辆列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">车辆管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
车辆信息列表
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
                                <label>车辆名称<input type="text" value="${command.name!}" name="name" /></label>
                                <label>车牌号<input type="text" value="${command.carNumber!}" name="carNumber" /></label>
                                <label>司机<input type="text" value="${command.driver!}" name="driver" /></label>
                                <label>救援状态
                                    <select name="carType">
                                        [#assign status = (command.carType!)?default("") /]
                                        <option value="">全部</option>

                                        <option value="ECONMY" [@mc.selected carType "ECONMY"/]>经济型</option>
                                        <option value="COMFORT" [@mc.selected carType "COMFORT"/]>舒适型</option>
                                        <option value="BUSINESS" [@mc.selected carType "BUSINESS"/]>商务型</option>
                                        <option value="LUXURY" [@mc.selected carType "LUXURY"/]>豪华型</option>
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
                        <th>车辆名称</th>
                        <th>车牌号</th>
                        <th>司机</th>
                        <th>类型</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as car ]
                            <tr class="even">
                                <td>${car.name!}</td>
                                <td>${car.carNumber!}</td>
                                <td>${(car.driver.getName())!}</td>
                                <td>${(car.carType.getName())!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue" href="[@spring.url '/car/show/${car.id!}'/]">查看</a>
                                            </li>
                                            <li>
                                                <a class="green" href="[@spring.url '/car/edit/${car.id}'/]">编辑</a>
                                            </li>
                                        </ul>
                                    </div>
                            </tr>
                            [/#list]
                        [/#if]
                    </tbody>
                </table>

                [#if pagination??]
                    [@mc.showPagination '/car/list?name=${command.name!}&carNumber=${command.carNumber!}&driver=${car.driver}&carType=${command.carType!}' /]
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
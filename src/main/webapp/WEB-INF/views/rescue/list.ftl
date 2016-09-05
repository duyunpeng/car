[@override name="title"]救援管理-救援列表[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active">救援管理</a></li>
[/@override]

[@override name="pageHeaderTitle"]
救援信息列表
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
                                <label>申请人<input type="text" value="${command.applyUser!}" name="applyUser"/></label>
                                <label>救援司机<input type="text" value="${command.driver!}" name="driver"/></label>
                                <label>救援状态
                                    <select name="status">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="">全部</option>
                                        <option value="WAIT_RESCUE" [@mc.selected status "WAIT_RESCUE"/]>待救援</option>
                                        <option value="IN_RESCUE" [@mc.selected status "IN_RESCUE"/]>救援中</option>
                                        <option value="WAIT_AUDIT" [@mc.selected status "WAIT_AUDIT"/]>完成待审核</option>
                                        <option value="SUCCESS_RESCUE" [@mc.selected status "SUCCESS_RESCUE"/]>救援完成
                                        </option>
                                    </select>
                                </label>
                                <div class="col-lg-6">
                                    <label>区域
                                        <div class="area_box">
                                            <select class="col-xs-3 area_data">
                                            </select>
                                        </div>
                                    </label>
                                </div>

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
                        <th>申请人</th>
                        <th>申请时间</th>
                        <th>救援类型</th>
                        <th>救援说明</th>
                        <th>救援司机</th>
                        <th>救援时间</th>
                        <th>救援状态</th>
                        <th>救援完成时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>


                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        [#if pagination.data??]
                            [#list pagination.data as rescue ]
                            <tr class="even">
                                <td>${rescue.applyUser.userName!}</td>
                                <td>${rescue.applyTime!}</td>
                                <td>${(rescue.rescueType.getName())!}</td>
                                <td>${rescue.description!}</td>
                                <td>${rescue.driver.userName!}</td>
                                <td>${rescue.rescueTime!}</td>
                                <td>${(rescue.status.getName())!}</td>
                                <td>${rescue.finishTime!}</td>
                                <td>
                                    <div class="btn-group">
                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle btn-sm">
                                            操作
                                            <i class="icon-angle-down icon-on-right"></i>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li>
                                                <a class="blue"
                                                   href="[@spring.url '/rescue/show/${rescue.id!}'/]">查看</a>
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
                    [@mc.showPagination '/rescue/list?applyUser=${command.applyUser!}&applyTime=${command.applyTime!}&type=${command.type!}&description=${command.description!}&driver=${command.driver!}&rescueTime=${command.rescueTime!}&status=${command.status!}&finishTime=${command.finishTime!}' /]
                [/#if]

            </div>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/assets/app/js/area.js'/]"></script>
<script>
    $(".area_box").areaCascade("area");
</script>
[/@override]

[@extends name="/decorator.ftl"/]
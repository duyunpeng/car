[@override name="title"]计费管理-修改计费信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/billing/list'/]">计费管理</a></li>
<li class="active">计费修改</li>
[/@override]

[@override name="pageHeaderTitle"]
修改计费管理
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/billing/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${billing.id!command.id}" />
            <input type="hidden" name="version" value="${billing.version!command.version}" />

            [@spring.bind "command.kmBilling"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 根据公里计费* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="kmBilling" value="${billing.kmBilling!command.kmBilling}"
                           placeholder="根据公里计费" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "kmBilling"/]
                </div>
            </div>

            [@spring.bind "command.startKm"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步公里* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="startKm" value="${billing.startKm!command.startKm}"
                           placeholder="起步公里" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "startKm"/]
                </div>
            </div>

            [@spring.bind "command.minuteBilling"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 根据分钟计费* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="minuteBilling" value="${billing.minuteBilling!command.minuteBilling}"
                           placeholder="根据分钟计费" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "minuteBilling"/]
                </div>
            </div>

            [@spring.bind "command.startMin"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步分钟* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="startMin" value="${billing.startMin!command.startMin}"
                           placeholder="起步分钟" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "startMin"/]
                </div>
            </div>

            [@spring.bind "command.startingPrice"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步价* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="startingPrice" value="${command.startingPrice!}"
                           placeholder="起步价" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "startingPrice"/]
                </div>
            </div>

            [@spring.bind "command.company"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司* </label>

                <div class="col-sm-9">
                    <select class="col-xs-3 area_data">
                    </select>
                    [@spring.showErrors "company"/]
                </div>
            </div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        修改
                    </button>
                    <button class="btn" type="reset">
                        <i class="icon-undo bigger-110"></i>
                        重置
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="modal fade alert-add" id="modalSearch" tabindex="-1" role="dialog" aria-labelledby="new-event" aria-hidden="true">
    <div class="large-dialog">
        <div class="change-bg">
            <div class="modal-header">
                <p class="modal-title thin">车辆列表--勾选添加到已选车辆列表
                    <small class="text-muted"></small>
                </p>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-9">
                        <section class="tile color transparent-black">
                            <div class="tile-header">
                                <div class="controls">
                                    <a href="#" class="minimize"><i class="fa fa-chevron-down"></i></a>
                                    <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
                                    <a href="#" class="remove"><i class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="tile-body">
                                <form class="form-horizontal" role="form"
                                      action="[@spring.url '/billing/billing_list' /]">
                                    <div class="input-list">
                                        <ul>
                                            <li>

                                            <li>
                                                <label>根据公里计费:</label>
                                                    <span>
                                                        <input type="text" class="form-control" id="kmBilling"
                                                               name="kmBilling"
                                                               value="${command.kmBilling!}">
                                                    </span>
                                            </li>

                                            <li>
                                                <label>根据分钟计费:</label>
                                                    <span>
                                                        <input type="text" class="form-control" id="minuteBilling"
                                                               name="minuteBilling"
                                                               value="${command.minuteBilling!}">
                                                    </span>
                                            </li>

                                            <li>
                                                <label>区域:</label>
                                                    <span>
                                                        <input type="text" class="form-control" id="company"
                                                               name="area"
                                                               value="${command.company!}">
                                                    </span>
                                            </li>


                                                <button type="button" class="btn btn-dutch margin-left-15"
                                                        id="evaluateonFind">查询
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </form>
                                <!-- tile body -->
                                <div class="tile-body nopadding">
                                    <table class="table table-bordered table-sortable table-hover">
                                        <thead></thead>
                                        <tbody></tbody>
                                    </table>
                                </div>
                                <!-- tile footer -->
                                <div class="tile-footer bg-transparent-black-2 rounded-bottom-corners">
                                    <div class="row">
                                        <div class="col-sm-4 text-center">
                                            <small class="inline table-options paging-info paging-billing">
                                            </small>
                                        </div>
                                        <div class="col-sm-4 text-right sm-center">
                                            <ul class="pagination pagination-xs nomargin pagination-custom pagination-billing">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- /tile footer -->
                        </section>
                    </div>

                    <div class="col-md-3">
                        <section class="tile color transparent-black">
                            <div class="tile-header">
                                <h3><strong>已选</strong>列表</h3>

                                <div class="controls">
                                    <a href="#" class="minimize"><i class="fa fa-chevron-down"></i></a>
                                    <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
                                    <a href="#" class="remove"><i class="fa fa-times"></i></a>
                                </div>
                            </div>
                            <div class="tile-body selector-box modal-search-selector">
                                <button class="btn margin-top-15 btn-green modal-search-hide-modal">确定</button>
                                <button class="btn margin-top-15 btn-info selector-remove-all">删除全部</button>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
[/@override]

[@extends name="/decorator.ftl"/]
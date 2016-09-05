[@override name="title"]评价管理-修改评价信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/evaluate/list'/]">评价管理管理</a></li>
<li class="active">修改评价</li>
[/@override]

[@override name="pageHeaderTitle"]
修改评价管理
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/evaluate/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${evaluateRepresentation.id!command.id}" />
            <input type="hidden" name="version" value="${evaluateRepresentation.version!command.version}" />

            [#--[@spring.bind "command.evaluateUser"/]--]
            [#--<div class="form-group">--]
                [#--<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 评价人* </label>--]

                [#--<div class="col-sm-9">--]
                    [#--<input type="text" id="form-field-1" name="evaluateUser" value="${evaluate.evaluateUser!command.evaluateUser}"--]
                           [#--placeholder="评价人" class="col-xs-10 col-sm-5" required/>--]
                    [#--[@spring.showErrors "evaluateUser"/]--]
                [#--</div>--]
            [#--</div>--]

            [#--[@spring.bind "command.order"/]--]
            [#--<div class="form-group">--]
                [#--<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 订单* </label>--]

                [#--<div class="col-sm-9">--]
                    [#--<input type="text" id="form-field-1" name="evaluateUser" value="${evaluate.order!command.order}"--]
                           [#--placeholder="订单" class="col-xs-10 col-sm-5" required/>--]
                    [#--[@spring.showErrors "order"/]--]
                [#--</div>--]
            [#--</div>--]

            [@spring.bind "command.content"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 评价内容* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="content" value="${evaluate.content!command.content}"
                           placeholder="评价内容" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "content"/]
                </div>
            </div>

            [@spring.bind "command.level"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 评级* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="level" value="${evaluate.level!command.level}"
                           placeholder="评级" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "level"/]
                </div>
            </div>

            [#--<div>--]
                [#--<div class="form-group">--]
                    [#--<label class="col-sm-3 control-label no-padding-right" for="form-field-1">  </label>--]

                    [#--<div class="col-sm-9">--]
                        [#--<div class="show-evaluate">--]
                            [#--[#if evaluate.evaluateUser!]--]
                                [#--[#list evaluate.evaluateUser as evaluateUser]--]
                                    [#--<div class="check-td-info">${evaluateUser.evaluateUser!}</div>--]
                                    [#--<input type="hidden" name="permissionIds" value="${evaluateUser.id}"/>--]
                                [#--[/#list]--]
                            [#--[/#if]--]
                        [#--</div>--]
                    [#--</div>--]
                [#--</div>--]
            [#--</div>--]

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
                <p class="modal-title thin">评价列表--勾选添加到已选评价列表
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
                                      action="[@spring.url '/evaluate/evaluate_list' /]">
                                    <div class="input-list">
                                        <ul>
                                            <li>
                                                [#--<label>评价人:</label>--]
                                                    [#--<span>--]
                                                        [#--<input type="text" class="form-control" id="evaluateUser"--]
                                                               [#--name="evaluateUser"--]
                                                               [#--value="${command.evaluateUser!}">--]
                                                    [#--</span>--]
                                            [#--</li>--]

                                            [#--<li>--]
                                                [#--<label>订单:</label>--]
                                                    [#--<span>--]
                                                        [#--<input type="text" class="form-control" id="order"--]
                                                               [#--name="order"--]
                                                               [#--value="${command.order!}">--]
                                                    [#--</span>--]
                                            [#--</li>--]


                                            <li>
                                                <label>内容:</label>
                                                    <span>
                                                        <input type="text" class="form-control" id="content"
                                                               name="content"
                                                               value="${command.content!}">
                                                    </span>
                                            </li>

                                            <li>
                                                <label>评级:</label>
                                                    <span>
                                                        <input type="text" class="form-control" id="level"
                                                               name="level"
                                                               value="${command.level!}">
                                                    </span>
                                            </li>

                                            <li>
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
                                            <small class="inline table-options paging-info paging-evaluate">
                                            </small>
                                        </div>
                                        <div class="col-sm-4 text-right sm-center">
                                            <ul class="pagination pagination-xs nomargin pagination-custom pagination-evaluate">
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
[#--<script src="[@spring.url '/resources/assets/js/modal-search-optimize.js' /]" type="text/javascript"></script>--]
[#--<script>--]
    [#--$(document).ready(function () {--]
        [#--var showTdPermission = $(".show-permission");--]
        [#--$("#role-app-key").on("change", function () {--]
            [#--$("#permission-app-key").val("");--]
            [#--var appKey = $(this).val();--]
            [#--if (appKey != "") {--]
                [#--$("#permission-app-key").val(appKey);--]
            [#--}--]
        [#--});--]
        [#--var modalSearch = new ModalSearch({--]
            [#--url: "/permission/permission_list",--]
            [#--pageSize : 6,--]
            [#--isSingle : true,--]
            [#--header :['权限编号','权限名称','权限描述'],--]
            [#--rowData :["id", "permissionName", "description"],--]
            [#--selectorData : ["permissionName"],--]
            [#--hideModalHandler : function(jsonDataArr){--]
                [#--showTdPermission.empty();--]
                [#--for (var key in jsonDataArr) {--]
                    [#--showTdPermission.append("<div class=\"check-td-info\">" + jsonDataArr[key].permissionName + "</div>");--]
                    [#--showTdPermission.append("<input type=\"hidden\" name=\"urlPermission\" value=\"" + jsonDataArr[key].id + "\" />")--]
                [#--}--]
            [#--}--]
        [#--});--]
    [#--})--]
[#--</script>--]
[/@override]

[@extends name="/decorator.ftl"/]
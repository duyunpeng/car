[@override name="title"]计费-创计费信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/billing/list'/]">计费管理</a></li>
<li class="active">创建计费</li>
[/@override]

[@override name="pageHeaderTitle"]
创建计费信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/billing/create" class="form-horizontal" method="post">

            [@spring.bind "command.kmBilling"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 根据公里计费* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="kmBilling" value="${command.kmBilling!}"
                           placeholder="根据公里计费" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "kmBilling"/]
                </div>
            </div>

            [@spring.bind "command.startKm"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步公里* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="startKm" value="${command.startKm!}"
                           placeholder="起步公里" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "startKm"/]
                </div>
            </div>

            [@spring.bind "command.minuteBilling"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 根据分钟计费* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="minuteBilling" value="${command.minuteBilling!}"
                           placeholder="根据分钟计费" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "minuteBilling"/]
                </div>
            </div>

            [@spring.bind "command.startMin"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 起步分钟* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="startMin" value="${command.startMin!}"
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
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 区域* </label>

                <div class="col-sm-5">
                    <a class="btn btn-green modal-search-modal">点击选择公司</a>
                    [@spring.showErrors "company"/]
                </div>
            </div>
            <div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1">  </label>

                    <div class="col-sm-9">
                        <div class="show-permission">

                        </div>
                    </div>
                </div>
            </div>


            <div class="clearfix form-actions">
                <div class="col-md-offset-4">
                    <button class="btn btn-info" type="submit">
                        <i class="icon-ok bigger-110"></i>
                        创建
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
                <p class="modal-title thin">权限列表--勾选添加到已选权限列表
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
                                      action="[@spring.url '/permission/permission_list' /]">
                                    <div class="input-list">
                                        <ul>
                                            <li>
                                                <label>权限名称:</label>
                                                    <span>
                                                        <input type="text" class="form-control" id="permissionName"
                                                               name="permissionName"
                                                               value="${command.permissionName!}">
                                                    </span>
                                            </li>

                                            <li>
                                                <label>状态:</label>
                                                    <span>
                                                       <select class="chosen-transparent form-control"
                                                               id="permissionStatus" name="status">
                                                           [#assign status = (command.status!)?default("ENABLE") /]
                                                           <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>
                                                           <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>
                                                       </select>
                                                    </span>
                                            </li>

                                            <li>
                                                <button type="button" class="btn btn-dutch margin-left-15"
                                                        id="permissionFind">查询
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
                                            <small class="inline table-options paging-info paging-permission">
                                            </small>
                                        </div>
                                        <div class="col-sm-4 text-right sm-center">
                                            <ul class="pagination pagination-xs nomargin pagination-custom pagination-permission">
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

<script src="[@spring.url '/resources/assets/js/modal-search-optimize.js' /]" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        function hasLength(ele, content) {
            var myEle = ele.children();
            var createLabel = ele.prev();
            if (myEle.length > 0) {
                createLabel.text(content)
            } else {
                createLabel.text('')
            }
            ;
        };
        var showTdPermission = $(".show-permission");
        $("#role-app-key").on("change", function () {
            $("#permission-app-key").val("");
            var appKey = $(this).val();
            if (appKey != "") {
                $("#permission-app-key").val(appKey);
            }
        });
        var modalSearch = new ModalSearch({
            url: "/user/company/company_list",
            pageSize : 6,
            isSingle : true,
            header :['公司名称','公司用户名'],
            rowData :["name", "userName"],
            selectorData : ["name"],
            hideModalHandler : function(jsonDataArr){
                showTdPermission.empty();
                for (var key in jsonDataArr) {
                    showTdPermission.append("<div class=\"check-td-info\">" + jsonDataArr[key].name + "</div>");
                    showTdPermission.append("<input type=\"hidden\" name=\"company\" value=\"" + jsonDataArr[key].id + "\" />")
                }
            }
        });
    })
</script>
[/@override]

[@extends name="/decorator.ftl"/]
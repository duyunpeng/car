[@override name="title"]访问资源路径管理-修改访问资源路径信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/url_resources/list'/]">访问资源路径管理</a></li>
<li class="active">修改访问资源路径</li>
[/@override]

[@override name="pageHeaderTitle"]
修改访问资源路径信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/url_resources/edit" class="form-horizontal" method="post">

            <input type="hidden" name="id" value="${urlResources.id!command.id}" />
            <input type="hidden" name="version" value="${urlResources.version!command.version}" />

            [@spring.bind "command.urlName"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 访问资源路径名称* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="urlName" value="${urlResources.urlName!command.urlName}"
                           placeholder="访问资源路径名称" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "urlName"/]
                </div>
            </div>

            [@spring.bind "command.description"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 描述* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="description" value="${urlResources.description!command.description}"
                           placeholder="描述" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "description"/]
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 权限 </label>

                <div class="col-sm-9">
                    <a class="btn btn-primary btn-sm modal-search-modal">关联权限</a>
                </div>
            </div>
            <div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1">  </label>

                    <div class="col-sm-9">
                        <div class="show-permission">
                            [#if urlResources.urlPermission!]
                                [#list urlResources.urlPermission as permission]
                                    <div class="check-td-info">${permission.permissionName!}</div>
                                    <input type="hidden" name="permissionIds" value="${permission.id}"/>
                                [/#list]
                            [/#if]
                        </div>
                    </div>
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
        var showTdPermission = $(".show-permission");
        $("#role-app-key").on("change", function () {
            $("#permission-app-key").val("");
            var appKey = $(this).val();
            if (appKey != "") {
                $("#permission-app-key").val(appKey);
            }
        });
        var modalSearch = new ModalSearch({
            url: "/permission/permission_list",
            pageSize : 6,
            isSingle : true,
            header :['权限编号','权限名称','权限描述'],
            rowData :["id", "permissionName", "description"],
            selectorData : ["permissionName"],
            hideModalHandler : function(jsonDataArr){
                showTdPermission.empty();
                for (var key in jsonDataArr) {
                    showTdPermission.append("<div class=\"check-td-info\">" + jsonDataArr[key].permissionName + "</div>");
                    showTdPermission.append("<input type=\"hidden\" name=\"urlPermission\" value=\"" + jsonDataArr[key].id + "\" />")
                }
            }
        });
    })
</script>
[/@override]

[@extends name="/decorator.ftl"/]
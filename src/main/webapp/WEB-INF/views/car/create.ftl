[@override name="title"]车辆-创建车辆信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/car/list'/]">车辆管理</a></li>
<li class="active">创建车辆</li>
[/@override]

[@override name="pageHeaderTitle"]
创建车辆信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/car/create" class="form-horizontal" method="post">

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 车辆名称* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="name" value="${command.name!}"
                           placeholder="车辆名称" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "name"/]
                </div>
            </div>

            [@spring.bind "command.carNumber"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 车牌号* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="carNumber" value="${command.carNumber!}"
                           placeholder="车牌号" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "carNumber"/]
                </div>
            </div>

            [@spring.bind "command.driver"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 司机* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="driver" value="${command.driver!}"
                           placeholder="司机" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "driver"/]
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
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
[@override name="title"]评价管理-创建评价信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/evaluate/list'/]">评价管理</a></li>
<li class="active">创建评价</li>
[/@override]

[@override name="pageHeaderTitle"]
创建评价信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/evaluate/create" class="form-horizontal" method="post">

            [@spring.bind "command.evaluateUser"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 评价人* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="evaluateUser" value="${command.evaluateUser!}"
                           placeholder="evaluateUser" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "evaluateUser"/]
                </div>
            </div>

            [@spring.bind "command.order"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 订单* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="order" value="${command.order!}"
                           placeholder="order" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "order"/]
                </div>
            </div>

            [@spring.bind "command.content"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 内容* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="content" value="${command.content!}"
                           placeholder="content" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "content"/]
                </div>
            </div>

            [@spring.bind "command.level"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 评级* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="level" value="${command.level!}"
                           placeholder="level" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "level"/]
                </div>
            </div>

            [@spring.bind "command.createDate"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 时间* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="createDate" value="${command.createDate!}"
                           placeholder="createDate" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "createDate"/]
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
[/@override]

[@override name="bottomResources"]
    [@super /]

[/@override]

[@extends name="/decorator.ftl"/]
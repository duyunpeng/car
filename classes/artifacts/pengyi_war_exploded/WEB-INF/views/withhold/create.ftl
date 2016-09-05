[@override name="title"]扣款管理-创建扣款信息[/@override]

[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/withhold/list'/]">扣款管理</a></li>
<li class="active">创建扣款</li>
[/@override]

[@override name="pageHeaderTitle"]
创建扣款信息
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <form action="/withhold/create/${userId!}" class="form-horizontal" method="post">

            [@spring.bind "command.money"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 金额* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="money" value="${command.money!}"
                           placeholder="金额" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "money"/]
                </div>
            </div>

            [@spring.bind "command.detail"/]
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 描述* </label>

                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="detail" value="${command.detail!}"
                           placeholder="描述" class="col-xs-10 col-sm-5" required/>
                    [@spring.showErrors "detail"/]
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
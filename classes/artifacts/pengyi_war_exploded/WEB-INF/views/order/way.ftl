[@override name="title"]订单管理-订单线路[/@override]

[@override name="topResources"]
    [@super /]
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=eYf9sA6yVTFHlh9ytU4a0EYY"></script>

[/@override]

[@override name="breadcrumbTitle"]
<li class="active"><a href="[@spring.url '/order/list'/]">订单管理</a></li>
<li class="active">订单线路</li>
[/@override]

[@override name="pageHeaderTitle"]
订单线路
[/@override]

[@override name="subContent"]
<div class="row">
    <div class="col-xs-12">
        [@mc.showAlert /]
        <div class="profile-user-info profile-user-info-striped">

            <div id="allmap" style="height: 600px"></div>
        </div>
        <br>
        <br>

        <div class="col-xs-12">
            <a href="/order/list" class="btn btn-success btn-block">返回列表</a>
        </div>
    </div>
</div>

[/@override]

[@override name="bottomResources"]
    [@super /]
<script type="text/javascript">

    // 百度地图API功能
    var map = new BMap.Map("allmap");

    map.enableScrollWheelZoom(true);

    var p1 = new BMap.Point(${wayPoints[0].lon},${wayPoints[0].lat});
    var p2 = new BMap.Point(${wayPoints[wayPoints?size -1].lon},${wayPoints[wayPoints?size-1].lat});

    var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
    driving.search(p1,p2,{waypoints:[
        [#list wayPoints as point]
            new BMap.Point(${point.lon},${point.lat}),
        [/#list]
    ]})

</script>
[/@override]

[@extends name="/decorator.ftl"/]
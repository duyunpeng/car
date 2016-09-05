<div class="sidebar" id="sidebar">
    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            自定义内容 待定
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div><!-- #sidebar-shortcuts -->

    <ul class="nav nav-list">
        <li class="active">
            <a href="[@spring.url '/'/]">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> 首页 </span>
            </a>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-user"></i>
                <span class="menu-text"> 用户管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/base_user/list' /]">
                        <i class="icon-calendar"></i>
                        用户列表
                    </a>
                </li>

                <li>
                    <a href="[@spring.url '/base_user/create' /]">
                        <i class="icon-pencil"></i>
                        用户创建
                    </a>
                </li>

                [#--<li>--]
                    [#--<a href="#" class="dropdown-toggle">--]
                        [#--<i class="icon-double-angle-right"></i>--]
                        [#--司机用户管理--]
                        [#--<b class="arrow icon-angle-down"></b>--]
                    [#--</a>--]
                    [#--<ul class="submenu">--]
                        [#--<li>--]
                            [#--<a href="[@spring.url "/user/driver/list"/]">--]
                                [#--<i class="icon-calendar"></i>--]
                                [#--司机用户列表--]
                            [#--</a>--]
                        [#--</li>--]
                    [#--</ul>--]
                [#--</li>--]

            [#--<li>--]
            [#--<a href="#" class="dropdown-toggle">--]
            [#--<i class="icon-double-angle-right"></i>--]
            [#--公司用户管理--]
            [#--<b class="arrow icon-angle-down"></b>--]
            [#--</a>--]
            [#--<ul class="submenu">--]
            [#--<li>--]
            [#--<a href="[@spring.url "/user/company/list"/]">--]
            [#--<i class="icon-calendar"></i>--]
            [#--公司用户列表--]
            [#--</a>--]
            [#--</li>--]
            [#--</ul>--]
            [#--</li>--]

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-double-angle-right"></i>
                        平台用户管理
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url "/user/terrace/list"/]">
                                <i class="icon-calendar"></i>
                                平台用户列表
                            </a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-double-angle-right"></i>
                        用户（user）管理
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="/user/list">
                                <i class="icon-calendar"></i>
                                用户（user）列表
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-list"></i>
                <span class="menu-text"> 订单管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/order/list'/]">
                        <i class="icon-calendar"></i>
                        订单列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-briefcase"></i>
                <span class="menu-text"> 司机管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url "/user/driver/list"/]">
                        <i class="icon-calendar"></i>
                        司机用户列表
                    </a>
                </li>
                <li>
                    <a href="[@spring.url "/user/driver/create"/]">
                        <i class="icon-calendar"></i>
                        创建司机
                    </a>
                </li>
                <li>
                    <a href="[@spring.url "/user/driver/auth_list"/]">
                        <i class="icon-calendar"></i>
                        待审核司机用户列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-briefcase"></i>
                <span class="menu-text"> 公司管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url "/user/company/list"/]" class="dropdown-toggle">
                        <i class="icon-calendar"></i>
                        <span class="menu-text"> 公司列表 </span>
                    </a>
                </li>

                <li>
                    <a href="[@spring.url "/user/company/create"/]" class="dropdown-toggle">
                        <i class="icon-calendar"></i>
                        <span class="menu-text"> 公司创建 </span>
                    </a>
                </li>

                <li>
                    <a href="[@spring.url "/user/company/auth_list"/]" class="dropdown-toggle">
                        <i class="icon-calendar"></i>
                        <span class="menu-text"> 待审核公司列表 </span>
                    </a>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-edit"></i>
                        <span class="menu-text"> 车辆管理 </span>

                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url "/car/list"/]" class="dropdown-toggle">
                                <i class="icon-calendar"></i>
                                <span class="menu-text"> 车辆列表 </span>
                            </a>
                        </li>
                    [#--<li>--]
                    [#--<a href="[@spring.url "/car/create"/]" class="dropdown-toggle">--]
                    [#--<i class="icon-pencil"></i>--]
                    [#--<span class="menu-text"> 车辆创建 </span>--]
                    [#--</a>--]
                    [#--</li>--]

                    </ul>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-exchange"></i>
                <span class="menu-text"> App版本管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/app_version/list'/]">
                        <i class="icon-calendar"></i>
                        App版本列表
                    </a>
                </li>
                <li>
                    <a href="[@spring.url '/app_version/create'/]">
                        <i class="icon-calendar"></i>
                        App版本创建
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-exchange"></i>
                <span class="menu-text"> 资金流向管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/money_detailed/list'/]">
                        <i class="icon-calendar"></i>
                        资金流向列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-exchange"></i>
                <span class="menu-text"> 提现管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/withdraw/list'/]">
                        <i class="icon-calendar"></i>
                        提现列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-exchange"></i>
                <span class="menu-text"> 扣款管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/withhold/list'/]">
                        <i class="icon-calendar"></i>
                        扣款列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-exchange"></i>
                <span class="menu-text"> 计费模板管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/billing/list'/]">
                        <i class="icon-calendar"></i>
                        计费模板列表(启用)
                    </a>
                </li>
                <li>
                    <a href="[@spring.url '/billing/wait_list'/]">
                        <i class="icon-calendar"></i>
                        计费模板列表(禁用)
                    </a>
                </li>
            [#--<li>--]
            [#--<a href="[@spring.url '/billing/create'/]">--]
            [#--<i class="icon-leaf"></i>--]
            [#--创建计费模板--]
            [#--</a>--]
            [#--</li>--]
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-heart"></i>
                <span class="menu-text"> 意见反馈管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/feed_back/list'/]">
                        <i class="icon-calendar"></i>
                        意见反馈列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-info-sign"></i>
                <span class="menu-text"> 投诉管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/report/list'/]">
                        <i class="icon-calendar"></i>
                        投诉列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-envelope"></i>
                <span class="menu-text"> 评价管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/evaluate/list'/]">
                        <i class="icon-calendar"></i>
                        评价列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-envelope"></i>
                <span class="menu-text"> 充值记录管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/recharge/list'/]">
                        <i class="icon-calendar"></i>
                        充值记录列表
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-envelope"></i>
                <span class="menu-text"> 站内信息管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="[@spring.url '/message/list'/]">
                        <i class="icon-calendar"></i>
                        站内信息列表
                    </a>
                </li>

                <li>
                    <a href="[@spring.url'/message/create'/]">
                        <i class="icon-leaf"></i>
                        站内信息创建
                    </a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-legal"></i>
                <span class="menu-text"> 救援管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="/rescue/list">
                        <i class="icon-calendar"></i>
                        救援列表
                    </a>
                </li>

            </ul>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-cog"></i>
                <span class="menu-text"> 系统设置 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-tag"></i>
                        <span class="menu-text"> 区域管理 </span>

                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/area/list'/]">
                                <i class="icon-calendar"></i>
                                区域列表
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/area/create'/]">
                                <i class="icon-pencil"></i>
                                区域创建
                            </a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-tag"></i>
                        <span class="menu-text"> 角色管理 </span>

                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/role/list'/]">
                                <i class="icon-calendar"></i>
                                角色列表
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/role/create'/]">
                                <i class="icon-pencil"></i>
                                角色创建
                            </a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-tag"></i>
                        <span class="menu-text"> 权限管理 </span>

                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/permission/list'/]">
                                <i class="icon-calendar"></i>
                                权限列表
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/permission/create'/]">
                                <i class="icon-pencil"></i>
                                权限创建
                            </a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-tag"></i>
                        <span class="menu-text"> 访问资源路径管理 </span>

                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/url_resources/list'/]">
                                <i class="icon-calendar"></i>
                                访问资源路径列表
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/url_resources/create'/]">
                                <i class="icon-pencil"></i>
                                访问资源路径创建
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>

    <div class="sidebar-collapse" id="sidebar-collapse">

        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
           data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>
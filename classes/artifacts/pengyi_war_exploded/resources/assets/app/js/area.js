/**
 * Created by YJH on 2016/3/21.
 */
$.fn.areaCascade = function (attrName) {
    var _this = $(this);
    var attr_name = attrName;
    var initAjax = function (parentId, event) {
        $.ajax({
            type: "post",
            url: "/area/search_by_parent",
            data: "parentId=" + parentId,
            dataType: "json",
            success: function (data) {
                if (typeof data == 'object') {
                    data = data;
                } else {
                    data = JSON.parse(data);
                }
                if (data.code == "0") {
                    bindingData(data.data, event);
                }
            }
        })
    }

    var loadLowerAjax = function (parentId, event) {
        $.ajax({
            type: "post",
            url: "/area/search_by_parent",
            data: "parentId=" + parentId,
            dataType: "json",
            success: function (data) {
                if (typeof data == 'object') {
                    data = data;
                } else {
                    data = JSON.parse(data);
                }
                if (data.code == "0") {
                    bindLowerData(data.data, event);
                }
            }
        })
    }

    var bindingData = function (data, event) {
        event.empty();
        event.append("<option value=''>请选择</option>");
        $.each(data, function (index, area) {
            event.append("<option value='" + area.id + "'>" + area.name + "</option>");
        })
    }

    var bindLowerData = function (data, event) {
        event.parent().append("<select class='col-xs-3 area_data'></select>");
        var _select = event.next("select");
        _select.empty();
        _select.append("<option value=''>请选择</option>");
        $.each(data, function (index, area) {
            _select.append("<option value='" + area.id + "'>" + area.name + "</option>");
        })

    }

    var init = function () {
        var _select = _this.find(".area_data");
        initAjax("", $(_select[0]));
    }

    $(_this).on("change", "select", function () {
        var _selects = $(_this).find("select");
        var _selectIndex = $(this).index();
        $(this).attr("name", attr_name);
        $.each(_selects, function (index, data) {
            if (index > _selectIndex) {
                data.remove();
            }
            if (index != _selectIndex) {
                $(data).removeAttr("name");
            }
        });
        _selects = $(_this).find("select");
        if (_selects.size() < 3 && $(this).val() != "") {
            loadLowerAjax($(this).val(), $(this));
        }
    })

    init();

}

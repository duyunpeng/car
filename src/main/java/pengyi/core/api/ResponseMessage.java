package pengyi.core.api;

/**
 * Created by YJH on 2016/3/15.
 */
public enum ResponseMessage {

    //注册
    ERROR_10000(10000, "id不能为空"),
    ERROR_10001(10001, "version不能为空"),
    ERROR_10002(10002, "名称不能为空"),
    ERROR_10003(10003, "注册地址不能为空"),
    ERROR_10004(10004, "运营地址不能为空"),
    ERROR_10005(10005, "folder不能为空"),
    ERROR_10006(10006, "公司不能为空"),
    ERROR_10007(10007, "性别不能为空"),
    ERROR_10008(10008, "司机类型不能为空"),
    ERROR_10009(10009, "司机不能为空"),
    ERROR_10010(10010, "用户名不能为空"),
    ERROR_10011(10011, "密码不能为空"),
    ERROR_10012(10012, "状态不能为空"),
    ERROR_10013(10013, "邮箱不能为空"),
    ERROR_10014(10014, "注册时间不能为空"),
    ERROR_10015(10015, "注册时间格式错误(yyyy-MM-dd hh:mm:ss)"),
    ERROR_10016(10016, "旧密码不能为空"),
    ERROR_10017(10017, "新密码不能为空"),
    ERROR_10018(10018, "头像不能为空"),
    ERROR_10019(10019, "验证码不能为空"),
    ERROR_10020(10020, "下单人不能为空"),
    ERROR_10021(10021, "预约时间不能为空"),
    ERROR_10022(10022, "预约时间格式错误(yyyy-MM-dd hh:mm:ss)"),
    ERROR_10023(10023, "接收人不能为空"),
    ERROR_10024(10024, "订单id不能为空"),
    ERROR_10025(10025, "身份证照片不能为空"),
    ERROR_10026(10026, "驾驶证照片不能为空"),
    ERROR_10027(10027, "开始地址不能为空"),
    ERROR_10028(10028, "结束地址不能为空"),
    ERROR_10029(10029, "公里价格不能为空"),
    ERROR_10030(10030, "分钟价格不能为空"),
    ERROR_10031(10031, "页码不能为空"),
    ERROR_10032(10032, "角色名不能为空"),
    ERROR_10033(10033, "qq、电话、邮箱必须有一个不能为空"),
    ERROR_10034(10034, "司机不能为空"),
    ERROR_10035(10035, "起步价不能为空"),
    ERROR_10036(10036, "公里数不能为空"),
    ERROR_10037(10037, "经纬度不完整"),
    ERROR_10038(10028, "起步公里不能为空"),
    ERROR_10039(10039, "起步分钟不能为空"),
    ERROR_10040(10040, "金额不能为空"),
    ERROR_10041(10041, "用户不能为空"),
    ERROR_10042(10042, "姓名不能为空"),
    ERROR_10043(10043, "手机号码不能为空"),
    ERROR_10044(10044, "行驶证不能为空"),
    ERROR_10045(10045, "驾驶证类型不能为空"),
    ERROR_10046(10046, "运营许可证不能为空"),
    ERROR_10047(10047, "从业资格证不能为空"),
    ERROR_10050(10050, "金额不能为空"),
    ERROR_10051(10051, "用户不能为空"),

    //救援
    ERROR_20000(20000, "派送救援司机不能为空"),
    ERROR_20001(20001, "救援状态不能为空"),
    ERROR_20002(20002, "救援申请人不能为空"),
    ERROR_20003(20003, "救援说明不能为空"),
    ERROR_20004(20004, "已安排救援不能取消"),
    ERROR_20005(20005, "申请人不能为空"),
    ERROR_20006(20006, "救援类型不能为空"),
    ERROR_20007(20007, "救援说明不能为空"),
    ERROR_20008(20008, "救援图片证明不能为空"),
    ERROR_20009(20009, "区域不能为空"),

    //车辆
    ERROR_30001(30001, "车牌号不能为空"),
    ERROR_30002(30002, "车辆类型不能为空"),

    //评价
    ERROR_40001(40001, "内容不能为空"),
    ERROR_40002(40002, "等级不能为空"),

    //站内信
    ERROR_50001(50001, "接收人不能为空"),
    ERROR_50002(50002, "类容不能为空"),
    ERROR_50003(50003, "类型不能为空"),
    ERROR_50004(50004, "消息id不能为空"),
    ERROR_50005(50005, "详情不能为空"),
    //举报
    ERROR_60001(60001, "处理结果不能为空");


    private int code;
    private String message;

    ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

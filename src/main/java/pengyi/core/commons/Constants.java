package pengyi.core.commons;

/**
 * Created by YJH on 2016/3/7.
 */
public class Constants {

    public final static String SESSION_USER = "sessionUser";

    public final static String ALIPAY_PARTNER = "2088221376498325";

    public final static String ALIPAY_RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN82s3px7AeyAADSjS56IsEkEo+OhVNpSR1356ZjD+7rXKD+8K1phjUbKV796i3gT/M1jRnFMNPypgJsdn0B6RLZ+J9YgyZNgR1IkJDKOUGJO2vNHa9fxo+RhVz76cZjHo7op7inliD9sE0lAblJ5LDXZ3307tcbqAFD9KKSVuTnAgMBAAECgYBvla7fVTgDGM67moYNZ+0b1Gaa8UphSk7MtnZNTVRXwb0KlqfGaOw4fB2QaSq6SFpvYKPq8BLawYCP0ZziHSRf4xXqxO2XvotiW9LehNu5ca72wdBeHwf4Bn1mZe9YvUivc/3g5g8HAr4XwxBvtZLfo3BYZqEoNwAZzdpDfe8YkQJBAPVjaOW4CnRB6gKkqFsW7wuZQvdGQwWm22Fh1KSgRD/0eSBewfEz8JucwbdyEigSCZZVGtZNsf4aKJSuMrVzJPsCQQDo3c4kNrOMFVOBBDjjc0muZ6E17a2GYZkp9GRa7jCCE69CIAMDFe6rdMb6CddQD6ewd6QvutShd2YF/l3LpcQFAkB7XcL62o/G6uxu78EEKn97YU3dayF+0egxCTISodAuJwZOU9VFIkuwsBpj06F1K7xOK/MWEnGNwjDsrrcnqf/JAkBNMJkQcAF8QeV4MsoYuzKFWVya37pRfTCkM5rAeYwi0huaM7pL341J0F/UqkZxB39yg3YtTCUHkOiEvBijVMNxAkEAyI1uCo+VM6MPcpwRAfUW+E9mWzytVtIuosegwD9qflHh2TyLekr9FRKEPvFVGDodn6EnpJOsteOoog8hmTH1AA==";

    public final static String ALIPAY_RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    public final static String ALIPAY_NOTIFY_VERIFY_URL = "https://mapi.alipay.com/gateway.do";

    public final static String ALIPAY_NOTIFY_VERIFY_PARAM = "service=notify_verify&partner=" + Constants.ALIPAY_PARTNER + "&notify_id=";

    public static String WECHAT_KEY = "402881165388d598015388d7245a0004";           //微信支付key
    public static String WECHAT_APPID_U = "wx7827a70d6465f5df";                       //appid
    public static String WECHAT_MCH_ID_U ="1328747701";                               //商户id

    public static String WECHAT_APPID_D = "wx30564a5076355fc5";                       //appid
    public static String WECHAT_MCH_ID_D ="1332609701";                               //商户id

    public static String WECHAT_UNIFIED_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static String WECHAT_NOTIFY_URL = "http://a.xiguaxing.com/pay/wechat/notify";

    public static String WECHAT_RECHARGE_NOTIFY_URL = "http://a.xiguaxing.com/recharge/wechat/notify";
}

package pengyi.core.commons;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import pengyi.domain.model.user.BaseUser;

/**
 * Created by YJH on 2016/3/7.
 */
public class PasswordHelper {

    private static RandomNumberGenerator generator = new SecureRandomNumberGenerator(); //加密随机数生成器

    private final static String algorithmName = "md5";   //加密类型

    private final static int hashIterations = 2;     //迭代次数

    /**
     * create salt
     *
     * @return
     */
    public static String getSalt() {
        return generator.nextBytes().toHex();
    }

    /**
     * 加密
     *
     * @param password
     * @param credentialsSalt
     * @return
     */
    public static String encryptPassword(String password, String credentialsSalt) {
        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(credentialsSalt),
                hashIterations
        ).toHex();
        return newPassword;
    }

    /**
     * 验证密码是否相等
     *
     * @param user
     * @param password
     * @return
     */
    public static boolean equalsPassword(BaseUser user, String password) {
        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        return user.getPassword().equals(newPassword);
    }

}

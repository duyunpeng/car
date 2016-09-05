package pengyi.core.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
public class CoreDes3Utils {

    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64Util.encode(key);
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64Util.decode(key);
    }

    public static String encryptTripDes(String keyStr, String mainStr) {
        String encodeString_ECB = null;
        try {
            //密钥
            byte[] key = decryptBASE64(keyStr);
            byte[] data = mainStr.getBytes("UTF-8");
            byte[] encodeByte_ECB = des3EncodeECB(key, data);
            // 加密后的String 需把加密的byte[]转base64
            encodeString_ECB = encryptBASE64(encodeByte_ECB);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encodeString_ECB;
    }

    public static String decryptTripDes(String keyStr, String mainStr) {
        String decodeString_ECB = null;
        try {
            // 密钥
            byte[] key = decryptBASE64(keyStr);
            byte[] decodeByteMain_ECB =decryptBASE64(mainStr);
            byte[] decodeByte_ECB = ees3DecodeECB(key, decodeByteMain_ECB);
            decodeString_ECB = new String(decodeByte_ECB,"UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeString_ECB;
    }

    public static byte[] des3EncodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);

        return bOut;
    }

    public static byte[] ees3DecodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);

        return bOut;
    }

    private static class Base64Util {
        private static char[] base64EncodeChars = new char[] {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z', '0', '1', '2', '3',
                '4', '5', '6', '7', '8', '9', '+', '/' };

        private static byte[] base64DecodeChars = new byte[] {
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
                52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
                -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
                15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
                -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };

        public static String encode(byte[] data) {
            StringBuffer sb = new StringBuffer();
            int len = data.length;
            int i = 0;
            int b1, b2, b3;
            while (i < len) {
                b1 = data[i++] & 0xff;
                if (i == len)
                {
                    sb.append(base64EncodeChars[b1 >>> 2]);
                    sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                    sb.append("==");
                    break;
                }
                b2 = data[i++] & 0xff;
                if (i == len)
                {
                    sb.append(base64EncodeChars[b1 >>> 2]);
                    sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                    sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                    sb.append("=");
                    break;
                }
                b3 = data[i++] & 0xff;
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
                sb.append(base64EncodeChars[b3 & 0x3f]);
            }
            return sb.toString();
        }

        public static byte[] decode(String str) throws UnsupportedEncodingException {
            StringBuffer sb = new StringBuffer();
            byte[] data = str.getBytes("US-ASCII");
            int len = data.length;
            int i = 0;
            int b1, b2, b3, b4;
            while (i < len) {
            /* b1 */
                do {
                    b1 = base64DecodeChars[data[i++]];
                } while (i < len && b1 == -1);
                if (b1 == -1) break;
            /* b2 */
                do {
                    b2 = base64DecodeChars[data[i++]];
                } while (i < len && b2 == -1);
                if (b2 == -1) break;
                sb.append((char)((b1 << 2) | ((b2 & 0x30) >>> 4)));
            /* b3 */
                do {
                    b3 = data[i++];
                    if (b3 == 61) return sb.toString().getBytes("ISO-8859-1");
                    b3 = base64DecodeChars[b3];
                } while (i < len && b3 == -1);
                if (b3 == -1) break;
                sb.append((char)(((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
            /* b4 */
                do {
                    b4 = data[i++];
                    if (b4 == 61) return sb.toString().getBytes("ISO-8859-1");
                    b4 = base64DecodeChars[b4];
                } while (i < len && b4 == -1);
                if (b4 == -1) break;
                sb.append((char)(((b3 & 0x03) << 6) | b4));
            }
            return sb.toString().getBytes("ISO-8859-1");
        }
    }
}

package pengyi.core.util;

import javassist.Modifier;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * Author: pengyi
 * Date: 15-12-30
 * Time: 2:32 PM
 */

public class CoreReflectUtils {

    /**
     * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
     */
    public static Object getFieldValue(final Object object,
                                       final String fieldName) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        Object result;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("never happend exception!", e);
        }
        return result;
    }

    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */
    public static void setFieldValue(final Object object,
                                     final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("never happend exception!", e);
        }
    }

    /**
     * 循环向上转型,获取对象的DeclaredField.
     */
    protected static Field getDeclaredField(final Object object,
                                            final String fieldName) {
        Assert.notNull(object);
        return getDeclaredField(object.getClass(), fieldName);
    }

    /**
     * 循环向上转型,获取类的DeclaredField.
     */
    @SuppressWarnings("rawtypes")
    protected static Field getDeclaredField(final Class clazz,
                                            final String fieldName) {
        Assert.notNull(clazz);
        Assert.hasText(fieldName);
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 强制转换field可访问.
     */
    protected static void makeAccessible(final Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

    private static String getReadMethod(String name) {
        return "get" + name.substring(0, 1).toUpperCase(Locale.ENGLISH)
                + name.substring(1);
    }

    private static String getWriteMethod(String name) {
        return "set" + name.substring(0, 1).toUpperCase(Locale.ENGLISH)
                + name.substring(1);
    }
}

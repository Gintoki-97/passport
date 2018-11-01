package cn.gin.passport.common.util;

import org.apache.commons.lang.BooleanUtils;

/**
 * <p>Operations on boolean primitives and Boolean objects.</p>
 */
public class Booleans extends BooleanUtils {

    /**
     * <p>Converts a String to a Boolean.</p>
     *
     * {@code null}, {@code ' '} will return {@code false}.
     * Otherwise, {@code true} is returned.</p>
     *
     * <p>NOTE: This method will return the false instead of throwing exceptions.</p>
     *
     * <pre>
     *   BooleanUtils.toBooleanObject(null)    = Boolean.FALSE
     *   BooleanUtils.toBooleanObject(" ")     = Boolean.FALSE
     *   BooleanUtils.toBooleanObject("  ")    = Boolean.FALSE
     *   BooleanUtils.toBooleanObject(" a ")   = Boolean.TRUE
     *   BooleanUtils.toBooleanObject("0")     = Boolean.TRUE
     * </pre>
     *
     * @param str  the String to check; upper and lower case are treated as the same
     * @return the Boolean value of the string
     */
    public static boolean bool(String str) {

        if (str == null) {
            return Boolean.FALSE;
        }

        return str.trim().length() > 0;
    }

    /**
     * <p>Converts a String Array to a Boolean use the {@code '&&'} logic.</p>
     *
     * @param strs  the Strings to check; upper and lower case are treated as the same
     * @return the Boolean value of the string array
     */
    public static boolean boolAnd(String...strs) {

        if (strs == null) {
            return Boolean.FALSE;
        }

        for (String str : strs) {
            if (!bool(str)) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    /**
     * <p>Converts a String Array to a Boolean use the {@code '||'} logic.</p>
     *
     * @param strs  the Strings to check; upper and lower case are treated as the same
     * @return the Boolean value of the string array
     */
    public static boolean boolOr(String...strs) {

        if (strs == null) {
            return Boolean.FALSE;
        }

        for (String str : strs) {
            if (bool(str)) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }
}
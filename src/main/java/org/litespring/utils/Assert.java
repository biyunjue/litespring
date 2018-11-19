package org.litespring.utils;

/**
 * @author yunfy
 * @create 2018-11-19 23:24
 **/
public class Assert {
    /**
     * Assert that an object is not {@code null} .
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an object is not {@code null} .
     *
     * @param object the object to check
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

}

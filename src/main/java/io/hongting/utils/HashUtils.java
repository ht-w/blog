package io.hongting.utils;

import java.util.Base64;

/**
 * @author hongting
 * @create 2021 11 09 11:47 AM
 */
public final class HashUtils {

    /**
     * Decodes a Base64 string to a byte array. A convenience method for java.util.Base64 decoder.
     *
     * @param string
     *            (in Base64)
     * @return Base64 decoded byte array
     * @see <a href="https://en.wikipedia.org/wiki/Base64">https://en.wikipedia.org/wiki/Base64</a>
     */
    public static byte[] decodeBase64(String string) {
        return Base64.getDecoder()
                .decode(string);
    }

    /**
     * Encodes a byte array into a Base64 string. A convenience method for java.util.Base64 encoder.
     *
     * @param array
     *            (byte array)
     * @return Base64 encoded string
     * @see <a href="https://en.wikipedia.org/wiki/Base64">https://en.wikipedia.org/wiki/Base64</a>
     */
    public static String encodeBase64(byte[] array) {
        return new String(Base64.getEncoder()
                .encode(array));
    }
}

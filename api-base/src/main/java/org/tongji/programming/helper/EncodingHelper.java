package org.tongji.programming.helper;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @author cinea
 */
public class EncodingHelper {
    public static boolean containsIncompatibleGbkChars(String text) {
        try {
            Charset gbkCharset = Charset.forName("GBK");
            String converted = new String(text.getBytes(gbkCharset), gbkCharset);
            return !converted.equals(text);
        } catch (UnsupportedCharsetException e) {
            return true;
        }
    }
}

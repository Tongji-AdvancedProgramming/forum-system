package org.tongji.programming.helper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author cinea
 */
public class Md5Helper {
    public static String getMD5(InputStream inputStream) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int numRead;
            while ((numRead = inputStream.read(buffer)) > 0) {
                md.update(buffer, 0, numRead);
            }
            inputStream.close();

            byte[] md5Bytes = md.digest();
            BigInteger no = new BigInteger(1, md5Bytes);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

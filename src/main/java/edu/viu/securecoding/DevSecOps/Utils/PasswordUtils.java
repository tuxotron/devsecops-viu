package edu.viu.securecoding.DevSecOps.Utils;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Component
public class PasswordUtils {

    public String encryptPassword(final String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return new String(hash, StandardCharsets.UTF_8);

    }


    public String removeCrlf(final String text) {

        /*
        IMPLEMENT THIS METHOD, SO WE CAN USE IT WITH THE LOG DATA TO AVOID CRLF POTENTIAL ISSUES
         */
        return StringUtils.isEmpty(text) ? "" : text.replaceAll("[\r\n]", "");
    }
}

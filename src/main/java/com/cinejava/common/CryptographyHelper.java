package com.cinejava.common;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptographyHelper {
    private static final String ALGORITHM = "AES";

    private static final byte[] FIXED_KEY = new byte[]{0x12, 0x34, 0x56, 0x78, 0x12, 0x34, 0x56, 0x78, 0x12, 0x34, 0x56, 0x78, 0x12, 0x34, 0x56, 0x78};

    private static SecretKey getFixedKey() {
        return new SecretKeySpec(FIXED_KEY, ALGORITHM);
    }

    public static String encrypt(String data) throws Exception {
        SecretKey key = getFixedKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData); 
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKey key = getFixedKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
}

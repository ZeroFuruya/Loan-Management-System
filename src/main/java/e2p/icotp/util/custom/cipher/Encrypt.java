package e2p.icotp.util.custom.cipher;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        // Generating objects of KeyGenerator &
        // SecretKey
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey myDesKey = keygenerator.generateKey();

        return myDesKey;
    }

    public static byte[] encrypt(String input, SecretKey key) {
        String s = "";
        byte[] textEncrypted = s.getBytes();
        try {
            // Creating object of Cipher
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");

            // Creating byte array to store string
            byte[] text = input.getBytes(StandardCharsets.UTF_8);

            // Encrypting text
            desCipher.init(Cipher.ENCRYPT_MODE, key);
            textEncrypted = desCipher.doFinal(text);

            // Converting encrypted byte array to string
            s = new String(textEncrypted);
            System.out.println("Encrypted: " + s);

            decrypt(key, textEncrypted);
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return textEncrypted;
    }

    public static String decrypt(SecretKey key, byte[] textEncrypted)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
            NoSuchPaddingException {
        String s;
        Cipher desCipher;
        desCipher = Cipher.getInstance("DES");

        // Decrypting text
        desCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] textDecrypted = desCipher.doFinal(textEncrypted);

        // Converting decrypted byte array to string
        s = new String(textDecrypted);
        return s;
    }

    public static SecretKey convertStringToSecretKeyto(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }
}

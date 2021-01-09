package signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public final class SignatureOperations {
    public static void encryption(PublicKey publicKey, String inFile, String encFile)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        try {
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(encFile);
            processFile(cipher, in, out);
        }catch (IOException | BadPaddingException | IllegalBlockSizeException e){
            e.printStackTrace();
        }
    }

    public static void decryption(PrivateKey privateKey, String encFile, String outFile)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        try {
            FileInputStream in = new FileInputStream(encFile);
            FileOutputStream out = new FileOutputStream(outFile);
            processFile(cipher, in, out);
        }catch (IOException | BadPaddingException | IllegalBlockSizeException e){
            e.printStackTrace();
        }
    }

    private static void processFile(Cipher ci, InputStream in, OutputStream out)
            throws IOException, BadPaddingException, IllegalBlockSizeException {
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) != -1) {
            byte[] o_buf = ci.update(buf, 0, len);
            if ( o_buf != null ) out.write(o_buf);
        }
        byte[] o_buf = ci.doFinal();
        if ( o_buf != null ) out.write(o_buf);
    }
}

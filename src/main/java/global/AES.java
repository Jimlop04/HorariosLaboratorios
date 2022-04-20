
package global;

import java.io.Serializable;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ebert
 */
public class AES implements Serializable  {
        private static final String salt = "Ch@rl3$L3C/3rK0283&#jhvwje!iWU0P";
    private static final String key = "njJDY1735TEFks&!+dw@3#ueMV/+*y&";
    
    private SecretKey secretKeyTemp;
    
    public AES(){
         SecretKeyFactory secretKeyFactory;
         KeySpec keySpec;
         try{
             secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
             keySpec = new PBEKeySpec(this.key.toCharArray(), this.salt.getBytes(), 65536, 256);
             secretKeyTemp = secretKeyFactory.generateSecret(keySpec);
         }catch(Exception e){
             e.printStackTrace();
         }
    }
    
    public String getAESEncrypt(String data){
        byte[] iv = new byte[16];
        try{
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKey = new SecretKeySpec(secretKeyTemp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public String getAESDecrypt(String data){
        byte[] iv = new byte[16];
        try{
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKey = new SecretKeySpec(secretKeyTemp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(Base64.getDecoder().decode(data)));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

package Utils;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Rijndael {
      
	public static String Decrypt(String text, String key) throws Exception{
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] keyBytes = digest.digest(key.getBytes("UTF-8"));
		keyBytes = Arrays.copyOfRange(keyBytes, 0, 16);
		
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		byte [] emptyVector = new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		IvParameterSpec ivSpec = new IvParameterSpec(emptyVector);
		
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		
		byte [] decrypted = cipher.doFinal(Base64.decodeBase64(text.getBytes()));
		
		return new String(decrypted);
	}  
}

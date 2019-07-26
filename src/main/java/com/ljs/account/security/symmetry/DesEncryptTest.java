/**
 * 
 */
package com.ljs.account.security.symmetry;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author lijunshi
 *
 */
public class DesEncryptTest {

	public static void main(String[] args) {
		try {
			// 创建秘钥生成器
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			//初始化，JDK提供长度为56，BouncyCastle可以提供64位
			keyGenerator.init(56);
			//生成私密秘钥key
			SecretKey secretKey = keyGenerator.generateKey();
			//转换为二进制
			byte[] b =secretKey.getEncoded();
			/**************************************************/
			// 实例化DES秘钥材料
			DESKeySpec dks =new  DESKeySpec(b);
			// 实例化秘钥工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			//生成私密秘钥
			SecretKey  newSecretKey  = keyFactory.generateSecret(dks);
			System.out.println(secretKey);
			System.out.println(newSecretKey);
			Cipher cipher = Cipher.getInstance("DES");
			//初始化加密方式
			cipher.init(Cipher.ENCRYPT_MODE, newSecretKey);
			String data ="你好 ,DES!";
			byte[] encryptData = cipher.doFinal(data.getBytes("UTF-8"));
			Cipher deCipher = Cipher.getInstance("DES");
			//初始化加密方式
			deCipher.init(Cipher.DECRYPT_MODE, newSecretKey);
			byte[] oldData = deCipher.doFinal(encryptData);
			System.out.println(new String(oldData,"UTF-8"));
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

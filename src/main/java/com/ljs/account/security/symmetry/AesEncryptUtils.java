/**
 * 
 */
package com.ljs.account.security.symmetry;

import java.security.Key;

/**
 * @author lijunshi
 *   对称加密算法工具类
 */
public class AesEncryptUtils {

	public static final String  KEY_AES="AES";
	
	
	/**
	 * 加密数据
	* @param data  明文二进制数据
 	* @param byteKey  二进制秘钥
	* @return  密文二进制数据
	 */
	public static byte[] encrypt(byte[] data,byte[]byteKey) {
		
		return null;
	}
	
	/**
	 * 解密数据
	* @param data  待解密数据
	* @param byteKey  二进制秘钥
	* @return  解密后明文
	 */
	public static byte[] decrypt(byte[] data,byte[] byteKey) {
		
		return null;
	}
	
	/**
	 * 将二进制转换为秘钥对象
	* @param byteKey 二进制秘钥
	* @return 返回秘钥对象
	 */
	public static Key transferByte2Key(byte[] byteKey) {
		
		return null;
	}
	
	/**
	 * 生成二进制秘钥
	* @return 二进制秘钥
	 */
	public static byte[] generateByteKey() {
		
		return null;
	} 
}

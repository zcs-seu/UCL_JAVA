package cn.edu.seu.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

import sun.misc.BASE64Encoder;

/**利用CRC进行加密
     * @param str  待加密的字符串
     * @return  加密后的长整型
     */	
public class Encrypt {
	public static long EncoderByCRC(String str)  {
		CRC32 crc32 = new CRC32();  
		crc32.update(str.getBytes()); 
		return crc32.getValue();
	}
	
	/**利用MD5进行加密
	     * @param str  待加密的字符串
	     * @return  加密后的字符串
	     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
	     * @throws UnsupportedEncodingException  
	     */	
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
	}
	
	/**利用SHA-256进行加密
	     * @param str  待加密的字符串
	     * @return  加密后的字符串
	     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
	     * @throws UnsupportedEncodingException  
	     */	
	public static String EncoderBySHA256(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha256=MessageDigest.getInstance("SHA-256");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(sha256.digest(str.getBytes("utf-8")));
        return newstr;
	}
	
	/**利用SHA-512进行加密
	     * @param str  待加密的字符串
	     * @return  加密后的字符串
	     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
	     * @throws UnsupportedEncodingException  
	     */	
	public static String EncoderBySHA512(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha512=MessageDigest.getInstance("SHA-512");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(sha512.digest(str.getBytes("utf-8")));
        return newstr;
	}
	
	public static String encrypt(String str,String encryptType){
		try{
			switch(encryptType){
			case "MD5":   
				return EncoderByMd5(str);
			case "SHA-256":
				return EncoderBySHA256(str);
			case "SHA-512":
				return EncoderBySHA512(str);
			default:
				return ""+EncoderByCRC(str);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String str="0123456789";
		System.out.println(Encrypt.EncoderBySHA512(str));
}
}

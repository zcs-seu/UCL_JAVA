package tools;

import java.io.UnsupportedEncodingException;

public class ConvertCharSet {

	private static String changeCharSet(  
            String str, String newCharset) throws UnsupportedEncodingException {  
        if (str != null) {  
            // 用默认字符编码解码字符串。  
            byte[] bs = str.getBytes();  
            // 用新的字符编码生成字符串  
            return new String(bs, newCharset);  
        }  
        return str;  
    }  
      
    /** 
     * 字符串转化为UTF-8 
     * @param str 
     * @return 
     */  
    public static String toUTF8(String str){  
        String result = str;  
        try {  
            result = changeCharSet(str, "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
      
    /** 
     * 字节数组转化为UTF-8 
     * @param bty 
     * @return 
     */  
    public static String toUTF8(byte[] bty){  
        try {  
            if (bty.length > 0) {  
                return new String(bty, "UTF-8");  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return new String(bty);  
    }  

}

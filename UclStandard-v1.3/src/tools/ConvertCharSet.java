package tools;

import java.io.UnsupportedEncodingException;

public class ConvertCharSet {

	private static String changeCharSet(  
            String str, String newCharset) throws UnsupportedEncodingException {  
        if (str != null) {  
            // ��Ĭ���ַ���������ַ�����  
            byte[] bs = str.getBytes();  
            // ���µ��ַ����������ַ���  
            return new String(bs, newCharset);  
        }  
        return str;  
    }  
      
    /** 
     * �ַ���ת��ΪUTF-8 
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
     * �ֽ�����ת��ΪUTF-8 
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

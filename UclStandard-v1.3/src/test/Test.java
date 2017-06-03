package test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Test {
	private static byte tPart=(byte) 0xB3;
	private static long lPart=0x00000000002413B3;
	
	//first type 4b:可能为语言类型、属性集合类别或属性元素类别
	public static byte getCategory() {
		byte category=(byte) ((tPart&0xF0)>>4);
		return category;
	}
	public static byte getDetail() {
		byte detail=(byte) (tPart&0x0F);
		return detail;
	}
	public static void setCategory(byte category) {
		category<<=4;
		tPart&=0x0F;
		tPart|=category;
	}
	public static void setDetail(byte detail) {
		tPart&=0xF0;
		tPart|=detail;
	}
	//length head:1B
	public static byte getLPartHead(int begin,int end) {
		int num=0;
		for(int i=begin;i<=end;i++){
			num+=Math.pow(2, i);
		}
		long tmp=lPart&num;
		byte lPartHead=(byte) (tmp>>begin);
		return lPartHead;
	}
	public static void setLPartHead(int begin,int end,byte lPartHead) {
		long num=0;
		for(int i=begin;i<=end;i++){
			num+=Math.pow(2, i);
		}
		num=(long)(0x00FFFFFFFFFFFFFFL-num);
		lPart&=num;
		lPartHead<<=begin;
		lPart|=lPartHead;
	}
	public static byte getBytesNumOfLPartValue(){
		return (byte) (getLPartHead(6,7)+1);
	}
	public static void setBytesNumOfLPartValue(byte numsOfLengthValue){
		setLPartHead(6,7,(byte) (numsOfLengthValue-1));
	}
	//length bytes:2b
	public static byte getLPartBytes(){
		byte BytesNumOfLPartValue= getBytesNumOfLPartValue();
		BytesNumOfLPartValue+=3;
		return BytesNumOfLPartValue;
	}
	public static int getTotalLength() {
		byte lPartBytes=getLPartBytes();
		byte BytesNumOfLPartValue= getBytesNumOfLPartValue();
		int begin=8;
		int end=BytesNumOfLPartValue*8+7;
		int num=0;
		for(int i=begin;i<=end;i++){
			num+=Math.pow(2, i);
		}
		long tmp=lPart&num;
		int valueLen=(int)(tmp>>begin);
		valueLen+=(1+lPartBytes);
		return valueLen;
	}
	public static byte getNumsOfPhysicalElements(){
		long num=getMatchNum(3, 5);
		System.out.println(lPart&num);
		long cur=(lPart&num)>>3;
		return (byte) cur;
	}
	public static long getMatchNum(int begin,int end){
		long num=0;
		for(int i=begin;i<=end;i++){
			num+=Math.pow(2, i);
		}
		return num;
	}
	public static void main(String[] args) {
		setCategory((byte) 12);
		setDetail((byte) 12);
		byte category=getCategory();
		byte detail=getDetail();
		System.out.println(category);
		System.out.println(detail);
		System.out.println(Math.pow(2, 3));
		System.out.println(getLPartHead(1,5));
		setLPartHead(1,5,(byte) 18);
		System.out.println(getLPartHead(1,5));
		System.out.println(getBytesNumOfLPartValue());
		setBytesNumOfLPartValue((byte) 1);
		System.out.println(getBytesNumOfLPartValue());
		System.out.println(getLPartBytes());
		System.out.println(getTotalLength());
		System.out.println(getNumsOfPhysicalElements());
		StringBuilder sb=new StringBuilder();
		String s="你好123";
		sb.append((char)(0x10));
		sb.append(s);
		char[] c=sb.toString().toCharArray();
		System.out.println(c.length);
		char num=c[0];
		char[] cur_arr = Arrays.copyOfRange(c, 1,c.length);
		byte rs_num=(byte) num;
		String rs_str=String.valueOf(cur_arr);
		String t = String.valueOf(sb.toString().toCharArray());
		System.out.println(t);
		System.out.println(s);
		char a=0x7f;
		byte b=(byte) a;
		System.out.println(a);
		System.out.println(b);
		System.out.println(0x1<<31);
		String name="1张成帅";
		try {
			System.out.println(name.getBytes("UTF8"));
			System.out.println(new String(name.getBytes("GB18030")));
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("hehe");
		}
	}
}

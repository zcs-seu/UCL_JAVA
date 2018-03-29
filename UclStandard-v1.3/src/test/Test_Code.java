package test;

import ucl.code.UCLCode;

public class Test_Code {
	public void set_code(UCLCode c)
	{
		byte version=1;
		c.setVersion(version);
		System.out.println(c.getVersion());

		byte typeofmedia=0;
		c.setTypeOfMedia(typeofmedia);
		typeofmedia=c.getTypeOfMedia();
		int temp=typeofmedia;
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		byte priority=0x1f;
		c.setPriority(priority);
		priority=c.getPriority();
		temp=priority;
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		byte flag=0x01;
		c.setFlag(flag);
		int temp2=c.getFlag();
		temp2&=0x000000ff;
		System.out.println(Integer.toHexString(temp2));

		byte[] parserule=new byte[2];
		parserule[0]=(byte)(0xf0);
		parserule[1]=0x01;
		c.setParseRule(parserule);
		parserule=c.getParseRule();
		temp=0;
		temp=parserule[0];
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		temp=parserule[1];
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		int sourceofcontent=0x00000003;
		c.setSourceOfContent(sourceofcontent);
		temp=c.getSourceOfContent();
		System.out.println(Integer.toHexString(temp));

		byte category=(byte)(0x01);
		c.setCategory(category);
		temp=c.getCategory();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		byte subcategory=(byte)(0x01);
		c.setSubcategory(subcategory);
		temp=c.getSubcategory();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		int topic=0x00000001;
		c.setTopic(topic);
		System.out.println(Integer.toHexString(c.getTopic()));

		byte typeofcontent=(byte)(0x01);
		c.setTypeOfContent(typeofcontent);
		temp=0;
		temp=c.getTypeOfContent();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));
		
		
		byte language=(byte)(0x08);
		c.setLanguage(language);
		temp=0;
		temp=c.getLanguage();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		String codepackage=c.packcode();
		c.unpackcode(codepackage);
		temp=c.getCodeCheckSum();	
		temp&=0x0000ffff;
		System.out.println(Integer.toHexString(temp));

		c.setCrc16();
		System.out.println(c.checkCrc16());
	}
	public void set_codezc(UCLCode c)
	{
		byte version=1;
		c.setVersion(version);
		//System.out.println(c.getVersion());

		byte typeofmedia=0;
		c.setTypeOfMedia(typeofmedia);
		typeofmedia=c.getTypeOfMedia();
		int temp=typeofmedia;
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));

		byte priority=0x1f;
		c.setPriority(priority);
		priority=c.getPriority();
		temp=priority;
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));

		byte flag=0x01;
		c.setFlag(flag);
		int temp2=c.getFlag();
		temp2&=0x000000ff;
		//System.out.println(Integer.toHexString(temp2));

		byte[] parserule=new byte[2];
		parserule[0]=(byte)(0xf0);
		parserule[1]=0x01;
		c.setParseRule(parserule);
		parserule=c.getParseRule();
		temp=0;
		temp=parserule[0];
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));

		temp=parserule[1];
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		int sourceofcontent=0x00000003;
		c.setSourceOfContent(sourceofcontent);
		temp=c.getSourceOfContent();
		//System.out.println(Integer.toHexString(temp));

		byte category=(byte)(0x01);
		c.setCategory(category);
		temp=c.getCategory();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));

		byte subcategory=(byte)(0x01);
		c.setSubcategory(subcategory);
		temp=c.getSubcategory();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));

		int topic=0x00000001;
		c.setTopic(topic);
		//System.out.println(Integer.toHexString(c.getTopic()));

		byte typeofcontent=(byte)(0x01);
		c.setTypeOfContent(typeofcontent);
		temp=0;
		temp=c.getTypeOfContent();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));
		
		
		byte language=(byte)(0x08);
		c.setLanguage(language);
		temp=0;
		temp=c.getLanguage();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));

		String codepackage=c.packcode();
		c.unpackcode(codepackage);
		temp=c.getCodeCheckSum();	
		temp&=0x0000ffff;
		//System.out.println(Integer.toHexString(temp));

		c.setCrc16();
		//System.out.println(c.checkCrc16());
	}
	public static void test_code(){
		UCLCode c = new UCLCode();
		Test_Code t = new Test_Code();
		t.set_code(c);
		c.showCode();
	}
	public static void test_codezc(){
		UCLCode c = new UCLCode();
		Test_Code t = new Test_Code();
		t.set_codezc(c);
		c.showCodezc();
		//String s=c.packcode();
		//UCLCode c2 = new UCLCode();
		//c2.unpackcode(s);
		//c2.showCodezc();
	}
	public static void main(String[] args) {
		test_codezc();
	}
	
}

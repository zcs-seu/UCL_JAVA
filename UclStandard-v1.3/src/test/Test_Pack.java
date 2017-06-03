package test;

import cn.edu.seu.fnrc.CGPSRequired;
import cn.edu.seu.fnrc.GenerateProperty;
import cn.edu.seu.fnrc.UCLPackage;
import cn.edu.seu.fnrc.code.UCLCode;
import cn.edu.seu.fnrc.code.UCLCodeExtention;
import cn.edu.seu.fnrc.property.UCLPropertyBase;
import cn.edu.seu.fnrc.property.UCLPropertyHead;
import cn.edu.seu.fnrc.property.UCLPropertySet;
import cn.edu.seu.utils.ConvertCharSet;

public class Test_Pack {

	public static void printPackString(String pack){
		char[] packArr = pack.toCharArray();
		for(int i=0; i <packArr.length; i++) {
			char cur=packArr[i];
			
			System.out.print(Integer.toHexString((byte)cur&0xFF));
			System.out.print(":");
	    }
	    System.out.println();
	}

	
	public static void setUclCode(UCLCode c){
		byte version=2;
		c.setVersion(version);
		//System.out.println(c.getVersion());
		byte typeofmedia=0x0f;
		c.setTypeOfMedia(typeofmedia);
		typeofmedia=c.getTypeOfMedia();
		int temp=typeofmedia;
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));
		
		byte precedence=0x0f;
		c.setPrecedence(precedence);
		int temp1=c.getPrecedence();
		temp1&=0x000000ff;
		//System.out.println(Integer.toHexString(temp1));
		
		
		byte flag=0x01;
		c.setFlag(flag);
		int temp2=c.getFlag();
		temp2&=0x000000ff;
		//System.out.println(Integer.toHexString(temp2));
		
	
		boolean firstflag=true;
		c.setFirstFlag(firstflag);
		//System.out.println(c.getFirstFlag());
		
		boolean secondflag=true;
		c.setSecondFlag(secondflag);
		//System.out.println(c.getSecondFlag());
		
		boolean thirdflag=true;
		c.setThirdFlag(thirdflag);
		//System.out.println(c.getThirdFlag());
		
		boolean fourthflag=true;
		c.setFourthFlag(fourthflag);
		//System.out.println(c.getFourthFlag());
		
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
		//System.out.println(Integer.toHexString(temp));
		
	
		c.setFirstPartParseRule((byte) 0x0c);
		temp=0;
		temp=c.getFirstPartParseRule();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));
		
		c.setSecondPartParseRule((byte)0x0a);
		temp=0;
		temp=c.getSecondPartParseRule();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));
		
		int sourceofcontent=0x0e9ffff;
		c.setSourceOfContent(sourceofcontent);
		temp=c.getSourceOfContent();
		//System.out.println(Integer.toHexString(temp));
		
		
		byte firstpartsourceofcontent=(byte)(0x0f);
		c.setFirstPartSourceOfContent(firstpartsourceofcontent);
		temp=c. getSecondPartSourceOfContent();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));
		
		int secondpartsourceofcontent=0x00eff000;
		c.setSecondPartSourceOfContent(secondpartsourceofcontent);
		//System.out.println(Integer.toHexString(c.getSecondPartSourceOfContent()));
		
		byte category=(byte)(0xee);
		c.setCategory(category);
		temp=c.getCategory();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));	
		
		
		byte subcategory=(byte)(0xfe);
		c.setSubcategory(subcategory);
		temp=c.getSubcategory();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));	
		
		int topic=0x1230000;
		c.setTopic(topic);
		//System.out.println(Integer.toHexString(c.getTopic()));	
		
		byte firstpartoftopic=(byte)(0x0e);
		c.setFirstPartOfTopic(firstpartoftopic);
		temp=c.getFirstPartOfTopic();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));
		
		
		int secondpartoftopic=0x0feffff0;
		c.setSecondPartOfTopic(secondpartoftopic);
		//System.out.println(Integer.toHexString(c.getSecondPartOfTopic()));
		
		byte typeofcontent=(byte)(0xef);
		c.setTypeOfContent(typeofcontent);
		temp=0;
		temp=c.getTypeOfContent();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));
		
		byte copyrightlength=(byte)(0xfe);
		c.setCopyrightAndLen(copyrightlength);
		temp=c.getCopyrightAndLen();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));
		
		byte secenerlevcode=(byte)(0xef);
		c.setSecEnerLevCode(secenerlevcode);
		temp=c.getSecEnerLevCode();
		temp&=0x000000ff;
		//System.out.println(Integer.toHexString(temp));

		int[] timestamp=new int[2];
		timestamp[0]=0xfe000000;
		timestamp[1]=0x0000001e;
		c.setTimeStamp(timestamp);
		//System.out.println(Integer.toHexString(c.getTimeStamp()[0]));
		//System.out.println(Integer.toHexString(c.getTimeStamp()[1]));
		
		int serialnumber=0x000effff;
		c.setSerialNumber(serialnumber);
		//System.out.println(Integer.toHexString(c.getSerialNumber()));
		
		int[] reversedbytes=new int[2];
		reversedbytes[0]=0xfe0e0000;
		reversedbytes[1]=0x0000000f;
		c.setReservedBytes(reversedbytes);
		reversedbytes=c.getReservedBytes();
		//System.out.println(Integer.toHexString(reversedbytes[0]));
		//System.out.println(Integer.toHexString(reversedbytes[1]));
		
		short checksum=(short)(0xfe01);
		c.setCodeCheckSum(checksum);
		temp=c.getCodeCheckSum();
		temp&=0x0000ffff;
		//System.out.println(Integer.toHexString(temp));
		
		
		
		String codepackage=c.packcode();
		c.unpackcode(codepackage);
		temp=c.getCodeCheckSum();
		temp&=0x0000ffff;
		//System.out.println(Integer.toHexString(temp));
		
		c.setCrc16();
		//System.out.println(c.checkCrc16());
	}
	
	public static void testUCL()
	{
	    UCLPackage ucl=new UCLPackage();

	    UCLCode code=new UCLCode();
	    setUclCode(code);
	    UCLCodeExtention extention=new UCLCodeExtention();
	    
	    ucl.setUclCode(code);
	    ucl.setUclCodeExtention(extention);
	    UCLPropertySet cdps = GenerateProperty.generateCDPS("SEU");
	    printPackString(cdps.pack());

	    CGPSRequired cr=new CGPSRequired();
	    
	    cr.provenance = "seu";
	    cr.proDes = 1;
	    cr.security = ConvertCharSet.toUTF8("贼高");
	    cr.secHelper = 0;
	    cr.chain = "seu;thing";
	    cr.chainCount = 2;
	    cr.sigUCL = "default";
	    cr.sigU[0] = 2;
	    cr.sigU[1] = 1;
	    UCLPropertySet cgps = GenerateProperty.generateCGPS(cr);
	    printPackString(cgps.pack());

	    ucl.setPropertySet(cdps);
	    ucl.setPropertySet(cgps);
	    ucl.setUCL();
	    String pack = ucl.packPropertySets();
	    printPackString(pack);

	    UCLPackage ucl2=new UCLPackage();
	    ucl2.unpackPropertySets(pack);
	    printPackString(ucl2.packPropertySets());
	    
	    pack=ucl.pack();
	    printPackString(pack);
	    
	    ucl2=new UCLPackage();
	    ucl2.unpack(pack);
	    printPackString(ucl2.pack());
	}
	
	UCLPropertySet testSetUnpack()
	{
	    UCLPropertyBase pe = GenerateProperty.generateSNPSPE((byte) 0xe, "我;爱;你");
	    printPackString(pe.pack());

	    UCLPropertyBase nr = GenerateProperty.generateSNPSNR("abc");
	    printPackString(nr.pack());

	    UCLPropertySet set0=new UCLPropertySet();
	    set0.setHeadCategory((byte) 0x0);
	    set0.setProperty(pe);
	    set0.setProperty(nr);
	    set0.setSet();
	    printPackString(set0.pack());

	    UCLPropertySet set1=new UCLPropertySet();
	    set1.unpack(set0.pack());
	    printPackString(set1.pack());
	    return set1;
	}

	static UCLPackage testUCLPack()
	{
	    //SNPS
	    UCLPropertyBase pe = GenerateProperty.generateSNPSPE((byte) 0xe, "我;爱;你");
	    printPackString(pe.pack());

	    UCLPropertyBase nr = GenerateProperty.generateSNPSNR("abc");
	    printPackString(nr.pack());

	    UCLPropertySet set0=new UCLPropertySet();
	    set0.setHeadCategory((byte) 0x0);
                                                                                                                                                                                                                                                                                                                                                                        	    set0.setProperty(pe);
	    set0.setProperty(nr);
	    set0.setSet();
	    printPackString(set0.pack());

	    //CDPS
	    UCLPropertyBase title = GenerateProperty.generateCDPSTitle("abc");
	    printPackString(title.pack());

	    UCLPropertyBase keywords = GenerateProperty.generateCDPSKeywords((byte) 3, "a;b;c");
	    printPackString(keywords.pack());

	    UCLPropertySet set1=new UCLPropertySet();
	    set1.setHeadCategory((byte) 0x1);
	    set1.setProperty(title);
	    set1.setProperty(keywords);
	    set1.setSet();
	    printPackString(set1.pack());

	    //CGPS
	    UCLPropertyBase provenance = GenerateProperty.generateCGPSProvenance((byte) 0x2, "SEU");
	    printPackString(provenance.pack());

	    UCLPropertyBase contentId = GenerateProperty.generateCGPSContentid("/home/zcs");
	    printPackString(contentId.pack());

	    UCLPropertySet set2=new UCLPropertySet();
	    set2.setHeadCategory((byte) 15);
	    set2.setProperty(provenance);
	    set2.setProperty(contentId);
	    set2.setSet();
	    printPackString(set2.pack());

	    UCLPackage ucl=new UCLPackage();
	    ucl.setPropertySet(set0);
	    ucl.setPropertySet(set1);
	    ucl.setPropertySet(set2);
	    ucl.setUCL();
	    printPackString(ucl.packPropertySets());

//	    cout << ucl.getValue(0, 1) << endl;
//	    map<int, UCLPropertySet> s = ucl.getPropertySets();
//	    s[0].setHeadCategory(0x05);
//	    cout << setbase(10) << s[0].getPropertyHead().getLPart();
	    return ucl;
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//测试属性部分打包解包
	    testUCL();
		//testUCLPack();
	}

}

package test;

import cn.edu.seu.fnrc.CGPSRequired;
import cn.edu.seu.fnrc.UCLPackage;
import cn.edu.seu.fnrc.code.UCLCode;
import cn.edu.seu.fnrc.code.UCLCodeExtention;
import cn.edu.seu.fnrc.property.GenerateProperty;
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
		System.out.println("\n========== UCL test begin==========\n");
	    UCLPackage ucl=new UCLPackage();

	    //code
	    UCLCode code=new UCLCode();
	    setUclCode(code);
	    UCLCodeExtention extention=new UCLCodeExtention();
	    
	    ucl.setUclCode(code);
	    ucl.setUclCodeExtention(extention);
	    
	    //propsets
	    
	    System.out.println("\n##############测试属性##############\n\n");
	    UCLPropertySet cdps = new UCLPropertySet();
	    
	    cdps.setHeadCategory(1);
	    UCLPropertyBase title = GenerateProperty.generateCDPSTitle("江苏今年起实施“12311”计划 培育百个农业特色镇",2);
	    UCLPropertyBase keywords = GenerateProperty.generateCDPSKeywords(3, "江苏;乡村;国家",2);
	    UCLPropertyBase aabstract = GenerateProperty.generateCDPSAbstract("省农委日前在金坛召开全省创意休闲农业工作推进会，决定从今年起实施“12311”创意休闲农业省级特色品牌培育计划",2);
	    UCLPropertyBase author = GenerateProperty.generateCDPSAuthor(2, 2, "邹建丰:新华日报\\r微博;博客:新浪",2);
	    UCLPropertyBase entity = GenerateProperty.generateCDPSEntity(31, "江苏省委\\r2017\\r金坛\\r培育计划\\r美丽",2);
	    UCLPropertyBase tag = GenerateProperty.generateCDPSTag(2, "美丽;乡村" ,2);
	    UCLPropertyBase copyright = GenerateProperty.generateCDPSCopyright("新华日报",2);
	    UCLPropertyBase origin = GenerateProperty.generateCDPSOriginality("皱建丰",2);
	    UCLPropertyBase file = GenerateProperty.generateCDPSFileDescription("文本;10M",2);
	    UCLPropertyBase related = GenerateProperty.generateCDPSRelatedUCL(3, "ucl1;ucl2;ucl3",2);
	    UCLPropertyBase content = GenerateProperty.generateCDPSContentObject("江苏今年起实施“12311”计划, 全省创意休闲农业工作推进会",2);
	    cdps.setProperty(title);
	    cdps.setProperty(keywords);
	    cdps.setProperty(aabstract);
	    cdps.setProperty(author);
	    cdps.setProperty(entity);
	    cdps.setProperty(tag);
	    cdps.setProperty(copyright);
	    cdps.setProperty(origin);
	    cdps.setProperty(file);
	    cdps.setProperty(related);
	    cdps.setProperty(content);
	    
	    System.out.println("--------------CDPS-------------- \n");
	    printPackString(cdps.pack());
	    ucl.setPropertySet(cdps);
	    
	    
	    UCLPropertySet cgps=new UCLPropertySet();
	    cgps.setHeadCategory(15);
	    UCLPropertyBase pro = GenerateProperty.generateCGPSProvenance(1, "http://jiangsu.sina.com.cn/news/b/2017-05-31/detail-ifyfqqyh9080015.shtml",2);
	    cgps.setProperty(pro);
	    UCLPropertyBase contentid = GenerateProperty.generateCGPSContentid("sina",2);
	    cgps.setProperty(contentid);
	    UCLPropertyBase prog = GenerateProperty.generateCGPSPropagation(2, "baidu;sina",2);
	    cgps.setProperty(prog);
	    UCLPropertyBase sigCon = GenerateProperty.generateCGPSSignatureContent("江苏今年起实施“12311”计划, 全省创意休闲农业工作推进会", 3, 0);
	    cgps.setProperty(sigCon);
	    UCLPropertyBase security = GenerateProperty.generateCGPSSecurity("重要",2);
	    cgps.setProperty(security);
	    UCLPropertyBase chain = GenerateProperty.generateCGPSChain(2, "sian;seu",2);
	    cgps.setProperty(chain);
	    UCLPropertyBase sigUCL = GenerateProperty.generateCGPSSignatureUCL(3, 0);
	    cgps.setProperty(sigUCL);
	    System.out.println("--------------CGPS-------------- \n");
	    printPackString(cgps.pack());

	    ucl.setPropertySet(cgps);
	    
	    
	    System.out.println("--------------propertySet--------------\n");
	    printPackString(ucl.packPropertySets());

	    System.out.println("\n##############测试打包##############\n\n");
	    System.out.println("--------------UCLPackage-------------- \n");
	    printPackString(ucl.pack());
	    System.out.println("--------------显示UCL各部分关键信息--------------\n");
	    //ucl.getUclCode().showCode();何伟亮负责
	    ucl.showUCL();

	    String ucl1 = ucl.pack();

	    System.out.println("\n##############测试解包##############\n\n");
	    UCLPackage ucl2=new UCLPackage();
	    ucl2.unpack(ucl1);
	    System.out.println("--------------UCLPackage--------------\n");
	    printPackString(ucl2.pack());
	    System.out.println("--------------解包后UCL各部分关键信息--------------\n");
	    //ucl2.getUclCode().showCode();何伟亮负责
	    ucl2.showUCL();

	    System.out.println("========== UCL test end==========\n");
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//测试属性部分打包解包
	    testUCL();
		//testUCLPack();
	}

}

package test;

import ucl.UCLPackage;
import ucl.code.UCLCode;
import ucl.code.UCLCodeExtention;
import ucl.property.gen.GenCDPSProperty;
import ucl.property.gen.GenCGPSProperty;
import ucl.property.base.UCLPropertyBase;
import ucl.property.base.UCLPropertySet;

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
	public static boolean checkSame(String pack1,String pack2){
		char[] packArr1 = pack1.toCharArray();
		char[] packArr2 = pack2.toCharArray();
		for(int i=0; i <packArr1.length; i++) {
			char cur1=packArr1[i];
			char cur2=packArr2[i];
			if(cur1!=cur2){
				return false;
			}
		}
		return true;
	}


	public static void setUclCode(UCLCode c){  //请何伟亮同学按照C++组代码将两处修改一致
		byte version=1;
		c.setVersion(version);
		System.out.println(c.getVersion());

		byte typeofmedia=9;
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

		int sourceofcontent=0x0e9ffff;
		c.setSourceOfContent(sourceofcontent);
		temp=c.getSourceOfContent();
		System.out.println(Integer.toHexString(temp));

		byte category=(byte)(0xee);
		c.setCategory(category);
		temp=c.getCategory();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		byte subcategory=(byte)(0xfe);
		c.setSubcategory(subcategory);
		temp=c.getSubcategory();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		int topic=0x1230000;
		c.setTopic(topic);
		System.out.println(Integer.toHexString(c.getTopic()));

		byte typeofcontent=(byte)(0xef);
		c.setTypeOfContent(typeofcontent);
		temp=0;
		temp=c.getTypeOfContent();
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

	public static void testUCL() {
		System.out.println("\n========== UCL test begin==========\n");
		UCLPackage ucl=new UCLPackage();

		//ucl.code
		UCLCode code=new UCLCode();
		setUclCode(code);
		UCLCodeExtention extention=new UCLCodeExtention();

		ucl.setUclCode(code);
		ucl.setUclCodeExtention(extention);

		//propsets

		System.out.println("\n##############测试属性##############\n\n");
		UCLPropertySet cdps = new UCLPropertySet();

		cdps.setHeadCategory(1);
		UCLPropertyBase title = GenCDPSProperty.genTitle("江苏今年起实施“12311”计划 培育百个农业特色镇",2);
		UCLPropertyBase keywords = GenCDPSProperty.genKeywords(3, "江苏;乡村;国家",2);
		UCLPropertyBase aabstract = GenCDPSProperty.genAbstract("省农委日前在金坛召开全省创意休闲农业工作推进会，决定从今年起实施“12311”创意休闲农业省级特色品牌培育计划",2);
		UCLPropertyBase author = GenCDPSProperty.genAuthor(3, 2, "邹建丰:新华日报\\r张三;李四:新浪",2);
		UCLPropertyBase entity = GenCDPSProperty.genEntity(31, "江苏省委\\r2017\\r金坛\\r培育计划\\r美丽;nice",2);
		UCLPropertyBase tag = GenCDPSProperty.genTag(2, "美丽;乡村" ,2);
		UCLPropertyBase copyright = GenCDPSProperty.genCopyright("新华日报",2);
		UCLPropertyBase origin = GenCDPSProperty.genOriginality("皱建丰",2);
		UCLPropertyBase file = GenCDPSProperty.genFileDescription("文本;10M",2);
		UCLPropertyBase content = GenCDPSProperty.genContObject("江苏今年起实施“12311”计划, 全省创意休闲农业工作推进会",2);
		cdps.setProperty(title);
		cdps.setProperty(keywords);
		cdps.setProperty(aabstract);
		cdps.setProperty(author);
		cdps.setProperty(entity);
		cdps.setProperty(tag);
		cdps.setProperty(copyright);
		cdps.setProperty(origin);
		cdps.setProperty(file);
		cdps.setProperty(content);

		ucl.setPropertySet(cdps);


		UCLPropertySet cgps=new UCLPropertySet();
		cgps.setHeadCategory(15);
		UCLPropertyBase pro = GenCGPSProperty.genProvenance(1, "http://jiangsu.sina.com.cn/news/b/2017-05-31/detail-ifyfqqyh9080015.shtml",2);
		cgps.setProperty(pro);
		UCLPropertyBase contentid = GenCGPSProperty.genContId("sina",2);
		cgps.setProperty(contentid);
		UCLPropertyBase prog = GenCGPSProperty.genPropagation(2, "baidu;sina",2);
		cgps.setProperty(prog);
		UCLPropertyBase sigCon = GenCGPSProperty.genContSig("江苏今年起实施“12311”计划, 全省创意休闲农业工作推进会", 2, 0);
		cgps.setProperty(sigCon);
		UCLPropertyBase security = GenCGPSProperty.genSecEL("重要",2);
		cgps.setProperty(security);
		UCLPropertyBase chain = GenCGPSProperty.genChainOfRes(2, "sian;seu",2);
		cgps.setProperty(chain);
		UCLPropertyBase sigUCL = GenCGPSProperty.genUCLSig(3, 0);
		cgps.setProperty(sigUCL);

		ucl.setPropertySet(cgps);


		/**
		 * 测试关联UCL
		 */

		String rstr = "";

		UCLCode rCode = code;
		rCode.setFlag((byte)0);
		rstr += rCode.packcode();

		UCLPackage rUCL = generateRUCL();
		rstr += rUCL.pack();

		UCLPropertyBase related = GenCDPSProperty.genRelatedUCL(2, rstr, 1);
		ucl.setProperty(1, related);



		System.out.println("--------------propertySet--------------\n");
		printPackString(ucl.packPropertySets());

		System.out.println("\n##############测试打包##############\n\n");
		System.out.println("--------------ucl.UCLPackage-------------- \n");
		int ccur=ucl.getPropertySets().get(1).getHeadCategory();
		String cur=ucl.pack();
		printPackString(ucl.pack());
		System.out.println("--------------显示UCL各部分关键信息--------------\n");
		//ucl.getUclCode().showCode();何伟亮负责
		ucl.showUCL();

		String ucl1 = ucl.pack();


		System.out.println("\n##############测试解包##############\n\n");
		UCLPackage ucl2=new UCLPackage();
		ucl2.unpack(ucl1);
		System.out.println("--------------ucl.UCLPackage--------------\n");
		printPackString(ucl2.pack());

		boolean b=checkSame(ucl.pack(),ucl2.pack());

		System.out.println("--------------解包后UCL各部分关键信息--------------\n");
		//ucl2.getUclCode().showCode();何伟亮负责
		ucl2.showUCL();

		System.out.println("========== UCL test end==========\n");
	}

	public static UCLPackage generateRUCL() {//请何伟亮同学按照C++组代码将此处CODE部分代码修改一致
		UCLPackage ucl=new UCLPackage();

		UCLCode code_test=new UCLCode();

		setUclCode(code_test);

		code_test.setTypeOfMedia((byte)9);
		code_test.setPriority((byte) 15);
		code_test.setFlag((byte)2); //00000010

		code_test.setVersion((byte)3);//对于已经设置过的域重复设置

		ucl.setUclCode(code_test);

		UCLPropertySet cdps=new UCLPropertySet();
		cdps.setHeadCategory(1);
		UCLPropertyBase title = GenCDPSProperty.genTitle("江苏今年起实施“12311”计划 培育百个农业特色镇",2);
		cdps.setProperty(title);

		ucl.setPropertySet(cdps);

		UCLPropertySet cgps=new UCLPropertySet();
		cgps.setHeadCategory(15);
		UCLPropertyBase pro = GenCGPSProperty.genProvenance(1, "http://jiangsu.sina.com.cn/news/b/2017-05-31/detail-ifyfqqyh9080015.shtml",2);
		cgps.setProperty(pro);
		UCLPropertyBase chain = GenCGPSProperty.genChainOfRes(2, "sian;seu",2);
		cgps.setProperty(chain);
		UCLPropertyBase sigUCL = GenCGPSProperty.genUCLSig(1, 0);
		cgps.setProperty(sigUCL);

		ucl.setPropertySet(cgps);

		UCLPropertySet personal = generatePersonalPropertySet();
		ucl.setPropertySet(personal);

		return ucl;
	}

	public static UCLPropertySet generatePersonalPropertySet() {
		UCLPropertySet personal=new UCLPropertySet();
		personal.setHeadCategory(10);

		UCLPropertyBase name=new UCLPropertyBase();
		name.setProperty(1, 1, "曾朋");
		personal.setProperty(name);

		UCLPropertyBase school=new UCLPropertyBase();
		school.setProperty(2, 1, "SEU");
		personal.setProperty(school);

		return personal;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//测试属性部分打包解包
		testUCL();
		//testUCLPack();
	}
}

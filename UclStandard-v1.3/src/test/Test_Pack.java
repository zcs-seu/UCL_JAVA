package test;

import ucl.UCLPackage;
import ucl.code.UCLCode;
import ucl.code.UCLCodeExtention;
import ucl.property.generate.GenCDPSProperty;
import ucl.property.generate.GenCGPSProperty;
import ucl.property.base.UCLPropertyBase;
import ucl.property.base.UCLPropertySet;
import ucl.property.generate.GenZCPSProperty;

import java.text.SimpleDateFormat;

public class Test_Pack {

	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

	public static UCLCode generateCode(){
		UCLCode code=new UCLCode();
		setUclCode(code);
		return code;
	}


	public static UCLPropertySet generateCDPS(){
		UCLPropertySet cdps = new UCLPropertySet();

		cdps.setHeadCategory(1);
		UCLPropertyBase title = GenCDPSProperty.genTitle("江苏今年起实施“12311”计划 培育百个农业特色镇",2);
		UCLPropertyBase file = GenCDPSProperty.genFileDescription("文本;10M",2);
		UCLPropertyBase content = GenCDPSProperty.genContObject("江苏今年起实施“12311”计划, 全省创意休闲农业工作推进会",2);
		cdps.setProperty(title);
		cdps.setProperty(file);
		cdps.setProperty(content);

		return cdps;
	}


	public static UCLPropertySet generateCGPS(){
		UCLPropertySet cgps=new UCLPropertySet();

		cgps.setHeadCategory(15);

		UCLPropertyBase sigCon = GenCGPSProperty.genContSig("江苏今年起实施“12311”计划, 全省创意休闲农业工作推进会", 2, 1);
		cgps.setProperty(sigCon);

		UCLPropertyBase sigUCL = GenCGPSProperty.genUCLSig(3, 1);
		cgps.setProperty(sigUCL);

		return cgps;
	}


	public static UCLPropertySet generateZCPS(){
		UCLPropertySet zcps = new UCLPropertySet();

		zcps.setHeadCategory(2);

		UCLPropertyBase name = GenZCPSProperty.genName("战场",0);
		zcps.setProperty(name);

		UCLPropertyBase spaceLoc = GenZCPSProperty.genSpaceLoc("北纬N39°40′20.09″ 东经E116°32′13.51",2,0);
		zcps.setProperty(spaceLoc);

		String time_str=df.format(System.currentTimeMillis());
		UCLPropertyBase time = GenZCPSProperty.genTime(time_str,2,0);
		zcps.setProperty(time);

		UCLPropertyBase shape = GenZCPSProperty.genShape("1;北纬N39°40′20.09″ 东经E116°32′13.51",0);
		zcps.setProperty(shape);

		UCLPropertyBase phy = GenZCPSProperty.genPhysical("0;;1;",0);
		zcps.setProperty(phy);

		UCLPropertyBase material = GenZCPSProperty.genMaterial("1",0);
		zcps.setProperty(material);

		UCLPropertyBase pass = GenZCPSProperty.genPassingAbility("1",0);
		zcps.setProperty(pass);

		UCLPropertyBase spaceEnemyS = GenZCPSProperty.genSpaceEnemyS("坦克;;潜艇",0);
		zcps.setProperty(spaceEnemyS);

		UCLPropertyBase amf = GenZCPSProperty.genAbsMotionFea("10;;北纬N39°40′20.09″ 东经E116°32′13.51",1,0);
		zcps.setProperty(amf);

		UCLPropertyBase rmf = GenZCPSProperty.genRelMotionFea("10;100;北纬N39°40′20.09″ 东经E116°32′13.51",1,0);
		zcps.setProperty(rmf);

		UCLPropertyBase tp = GenZCPSProperty.genTravellingPath("10;;北纬N39°40′20.09″ 东经E116°32′13.51",2,0);
		zcps.setProperty(tp);

		return zcps;
	}


	public static void testUCL() {
		System.out.println("\n========== UCL test begin==========\n");
		UCLPackage ucl=new UCLPackage();

		UCLCode code=generateCode();
		ucl.setUclCode(code);

		System.out.println("\n##############测试属性##############\n\n");

		UCLPropertySet cdps=generateCDPS();
		ucl.setPropertySet(cdps);

		UCLPropertySet cgps=generateCGPS();
		ucl.setPropertySet(cgps);

		UCLPropertySet zcps=generateZCPS();
		ucl.setPropertySet(zcps);


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


		System.out.println("--------------UCLPackage-------------- \n");
		printPackString(ucl.pack());
		System.out.println("--------------显示UCL各部分关键信息--------------\n");
		ucl.showUCL();


		System.out.println("\n##############测试解包##############\n\n");
		UCLPackage ucl2=new UCLPackage();
		ucl2.unpack(ucl.pack());
		System.out.println("--------------UCLPackage--------------\n");
		printPackString(ucl2.pack());


		System.out.println("--------------解包后UCL各部分关键信息--------------\n");
		ucl2.showUCL();

		System.out.println("========== ucl test end==========\n");

		System.out.println(ucl.getUCLTotalLength());
		System.out.println(ucl.getUclCode().getSizeOfContent());
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
		UCLPropertyBase sigUCL = GenCGPSProperty.genUCLSig(1, 1);
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

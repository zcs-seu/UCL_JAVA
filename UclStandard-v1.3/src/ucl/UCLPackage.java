package ucl;

import java.util.HashMap;
import java.util.Map;

import ucl.code.UCLCode;
import ucl.property.base.UCLPropertyBase;
import ucl.property.base.UCLPropertyHead;
import ucl.property.base.UCLPropertySet;
import ucl.code.UCLCodeExtention;
import ucl.property.info.UCLPropertiesInfo;
import tools.Encrypt;
import tools.Sig_RSA_DSA_ECDSA;

public class UCLPackage {
	/**
	 * modified by zhangcs at 2018-03-29
	 */

	private UCLCode uclCode;
	private UCLCodeExtention uclCodeExtention;
    private UCLPropertyHead uclPropertyHead;
    private Map<Integer,UCLPropertySet> propertySets;

    public static UCLPropertiesInfo UPI = new UCLPropertiesInfo();

    public static int NO_SIG = 0;
    public static int CRC32 = 1;
    public static int MD5 = 2;
    public static int SHA_256 = 3;
    public static int SHA_512 = 4;
    public static int RSA = 1;
    public static int ECDSA = 2;
    public static int DSA = 3;
    public static int ECC = 4;
    public static int HMAC = 5;
    public static int CRC32_LEN = 4;
    public static int MD5_LEN = 32;
    public static int SHA_256_LEN = 32;
    public static int SHA_512_LEN = 64;

    public static int RSA_LEN = 128;
    public static int ECDSA_LEN = 32;
    public static int DSA_LEN = 128;
    public static int ECC_LEN = 20;
    public static int HMAC_MD5 = 16;
    public static int HMAC_SHA1 = 20;
	
	//构造函数
	public UCLPackage() {
		// TODO 自动生成的构造函数存根
		uclCode=new UCLCode();
		uclCodeExtention=new UCLCodeExtention();
		uclPropertyHead = new UCLPropertyHead();
        propertySets=new HashMap<>();
		uclPropertyHead.setCategory(0x01); //language：中文
		uclPropertyHead.setTotalLength();
	}

    /**
     * UCLCode
     */
	public UCLCode getUclCode() {
		return uclCode;
	}
	public void setUclCode(UCLCode uclCode) {
		this.uclCode = uclCode;
	}
	
	public UCLCodeExtention getUclCodeExtention() {
		return uclCodeExtention;
	}
	public void setUclCodeExtention(UCLCodeExtention uclCodeExtention) {
		this.uclCodeExtention = uclCodeExtention;
	}

    /**
     * property
     */
    //uclPropertyHead
	public UCLPropertyHead getUclPropertyHead() {
		return uclPropertyHead;
	}
	public void setUclPropertyHead(UCLPropertyHead uclPropertyHead) {
		this.uclPropertyHead = uclPropertyHead;
	}


    //propertySets
    public Map<Integer, UCLPropertySet> getPropertySets(){
    	return propertySets;
    }
    public void setPropertySets(Map<Integer, UCLPropertySet> propertySets){
    	this.propertySets=propertySets;
    }
	
	
	//设置、删除属性集合
	public boolean setPropertySet(UCLPropertySet propertySet){
		propertySets.put(propertySet.getHeadCategory(), propertySet);
        setUCL();
		return true;
	}
	public boolean delPropertySet(int category){
		propertySets.remove(category);
		return true;
	}
	
	
	//设置 删除属性
	public boolean setProperty(int setPos, UCLPropertyBase property){
	    propertySets.get(setPos).setProperty(property);
	    setUCL();
	    return true;
	}

	public boolean delProperty(int setPos, int propertyPos){
	    if(propertySets.containsKey(setPos)){
	    	propertySets.get(setPos).delProperty(propertyPos);
	    	setUCL();
	    	return true;
	    }
	    return false;
	}

	public UCLPropertyBase getProperty(int setPos, int propertyPos){
	    if(propertySets.containsKey(setPos)){
	    	return propertySets.get(setPos).getProperty(propertyPos);
	    }
	    return null;
	}
    
    
    //获取第setPos集合的第propertyPos属性的vPart
	public String getValue(int setPos, int propertyPos){
		if(propertySets.containsKey(setPos)){
	    	return propertySets.get(setPos).getPropertyVPart(propertyPos);
	    }
	    return null;
	}

    //设置第setPos集合的第propertyPos属性的vPart
	public void setValue(int setPos, int propertyPos, String value){
		if(propertySets.containsKey(setPos)){
	    	propertySets.get(setPos).setPropertyVPart(propertyPos, value);
	    	setUCL();
	    } 
	}

    //判断数字签名类型
    public void initSignature(int helper,int alg) {
        int signType = helper;//propertySets[15].getProperty(15).getHelper();
        int abstractType = alg;//propertySets[15].getProperty(15).getLPartHead(2,5);
        if(signType == 0x00) {
            //未使用签名需要使用摘要填充
            if(abstractType == CRC32) {
                String str ="";
                for(int i=0;i<CRC32_LEN;i++) {
                    str+='0';
                }
                setValue(15, 15, str);
            } else if(abstractType == MD5) {
                String str ="";
                for(int i=0;i<MD5_LEN;i++) {
                    str+='0';
                }
                setValue(15, 15, str);
            } else if(abstractType == SHA_256) {
                String str ="";
                for(int i=0;i<SHA_256_LEN;i++) {
                    str+='0';
                }
                setValue(15, 15, str);
            } else if(abstractType == SHA_512) {
                String str ="";
                for(int i=0;i<SHA_512_LEN;i++) {
                    str+='0';
                }
                setValue(15, 15, str);
            }
        } else if(signType == RSA) {
            String str ="";
            for(int i=0;i<RSA_LEN;i++) {
                str+='0';
            }
            setValue(15, 15, str);
        } else if(signType == ECDSA) {
            String str ="";
            for(int i=0;i<ECDSA_LEN;i++) {
                str+='0';
            }
            setValue(15, 15, str);
        }
        else if(signType == DSA) {
            String str ="";
            for(int i=0;i<DSA_LEN;i++) {
                str+='0';
            }
            setValue(15, 15, str);
        }
        else if(signType == ECC) {
            String str ="";
            for(int i=0;i<ECC_LEN;i++) {
                str+='0';
            }
            setValue(15, 15, str);
        } else if(signType == HMAC) {
            //HMAC
        }
    }


    //设置uclPropertyHead类别
    public void setHeadCategory(int category){
    	uclPropertyHead.setCategory(category);
    }
    public int getHeadCategory(){
    	return uclPropertyHead.getCategory();
    }
    
    //设置uclPropertyHead helper
    public void setHeadHelper(int helper){
    	uclPropertyHead.setHelper(helper);
    }
    public int getHeadHelper(){
    	return uclPropertyHead.getHelper();
    }

    //根据propertySets生成uclPropertyHead的快速匹配
    public int generateQuickMatcher(){
    	int quickmatcher=0x0;
    	for(int i:propertySets.keySet()){
    		quickmatcher |= (0x01<<i);
    	}
    	return quickmatcher;
    }
    
    //根据propertySets生成uclPropertyHead的vPart
    public String generateHeadVPart(){
    	StringBuilder value=new StringBuilder();
    	int qm = uclPropertyHead.getQuickMatcher();
    	for(int i=0;i<16;i++){
    		if((qm&(0x01<<i))!=0){
    			value.append(propertySets.get(i).pack());
    		}
    	}
    	return value.toString();
    }

    //设置UCL总长度（code和property）
    public boolean setUCLTotalLength(){
        long totalLength = getUCLTotalLength();
        //根据totalLength设置code部分内容长度
        uclCode.setSizeOfContent(totalLength);
        return true;
    }
    //得到UCL总长度
    public long getUCLTotalLength(){
        return uclPropertyHead.getTotalLength() + 32;
    }

    //设置propertySets后必须调用的函数
   public  void setUCL(){
       uclPropertyHead.setQuickMatcher(generateQuickMatcher());
       uclPropertyHead.setVPart(generateHeadVPart());
       uclPropertyHead.setQuickMatcher(generateQuickMatcher());
   }

    
    //属性头部打包
    public String packCode(){
    	return uclCode.packcode();
    }
    //属性头部解包
    public void unpackCode(String codepackage){
    	uclCode.unpackcode(codepackage);
    }
    
    
    //属性集合打包解包
    public String packPropertySets(){
    	return uclPropertyHead.pack();
    }
    public void unpackPropertySets(String properties){
    	uclPropertyHead.unpack(properties);
    	String headVPart = uclPropertyHead.getVPart();
    	int qm = uclPropertyHead.getQuickMatcher();
    	int tmp=0;
    	for(int i=0;i<16;i++){
    		if((qm&(0x0001<<i))!=0){
    			//计算属性集合头部属性长度值字段字节数
                int lValueBytes = ((headVPart.toCharArray()[1+tmp] & 0x0FF) >> 6) + 1;
                //取出长度值字段
                char[] lValue = headVPart.substring(2+tmp, 2+tmp+lValueBytes).toCharArray();
                int lValueNum = 0;
                for(int j=0; j < lValue.length; j++) {
                	int cur = lValue[j] & 0x0FF;
                    switch (j) {
                        case 0:
                            lValueNum = (0x0ffffff00 & lValueNum) | cur;
                            break;
                        case 1:
                            lValueNum = (0x0ffff00ff & lValueNum) | (cur<<8);
                            break;
                        case 2:
                            lValueNum = (0x0ff00ffff & lValueNum) | (cur<<16);
                            break;
                        case 3:
                            lValueNum = (0x000ffffff & lValueNum) | (cur<<24);
                            break;
                    }
                }
                String propertySet = headVPart.substring(tmp, tmp+lValueNum);
                UCLPropertySet proSet=new UCLPropertySet();
                proSet.unpack(propertySet);
                propertySets.put(i, proSet);
                tmp += lValueNum;           
    		}
    	}
    }
    
    
    //ucl　Package打包
    public String pack(){
        Map<Integer, UCLPropertyBase> ps = propertySets.get(15).getProperties();
        //获得数字签名属性集合
        UCLPropertyBase sigUCLP = ps.get(15);

        int helper = sigUCLP.getHelper();
        int alg = sigUCLP.getLPartHead(2, 5);//alg摘要算法ID，temp为签名算法ID

        //根据对应签名长度，先填充对应位数，计算长度后填充签名
        //内容数字签名，类别号12，全UCL包数字签名，类别号15
        initSignature(helper,alg);
        //设定UCL总长度
        setUCLTotalLength();

        setValue(15, 15, "hello");//第一个15表示CGPS属性集合，第二个15表示数字签名,"hello"随意写的因为后面会覆盖
        String temp = packCode() /*+ uclCodeExtension.pack()*/ + packPropertySets();


        //生成摘要
        String hash = genHash(alg, temp);
        //私钥加密摘要（数字签名）
        String uclSigTemp = genSig(helper, hash);  //私钥加密摘要
        //写入签名
        setValue(15, 15, uclSigTemp);

        return packCode() /*+ uclCodeExtension.pack()*/ + packPropertySets();
    }
    
    //ucl　Package解包
    public void unpack(String uclpackage){
    	String code=uclpackage.substring(0,32);
    	String property=uclpackage.substring(32,uclpackage.length());
    	unpackCode(code);
    	unpackPropertySets(property);
    	assert (checkUCL());
    }
    
    
    //检验UCL包数字签名
    public boolean checkUCL(){
        Map<Integer, UCLPropertyBase> ps = propertySets.get(15).getProperties();
        UCLPropertyBase sigUCLP = ps.get(15);

        String uclSig = getValue(15, 15);
        setValue(15, 15, "");
        String originUCL = packCode() /*+ uclCodeExtension.pack()*/ + packPropertySets();

        int helper = sigUCLP.getHelper();
        int alg = sigUCLP.getLPartHead(2, 5);

        String hashFromSig = genSig(helper, uclSig);  //公钥解密成Hash值
        String hashFromTemp = genHash(alg, originUCL);  //比较Hash值
        if(hashFromTemp==hashFromSig){
            return true;
        }else {
            return false;
        }
    }
    //打印UCL各部分
    public void showUCL(){
        uclCode.showCode();
        //uclCodeExtension.showCodeExt();

        System.out.println("--------------属性部分----------------");
        int t = uclPropertyHead.getCategory();
        System.out.println("元语言类型: "+UCLPackage.UPI.getPropertyLangType(uclPropertyHead.getCategory()));
        System.out.println("属性集个数: "+uclPropertyHead.getSize());
        System.out.println("--------------具体属性集----------------");
        for(int propsetID : propertySets.keySet()) {
            System.out.println("**************************");
            propertySets.get(propsetID).showPropertySet();
        }
    }


    public static String genHash(int alg, String originUCL){
        String hash=null;

        switch(alg) {
            case 1: //CRC32
                hash = Encrypt.encrypt(originUCL, "CRC32");
                break;
            case 2: //MD5
                hash = Encrypt.encrypt(originUCL, "MD5");
                break;
            case 3: //SHA-256
                hash = Encrypt.encrypt(originUCL, "SHA-256");
                break;
            case 4: //SHA-512
                hash = Encrypt.encrypt(originUCL, "SHA-512");
                break;
            default: break;
        }
        return hash;
    }
    
    
    public static String genSig(int helper, String s){
        switch(helper) {
            case 1:
                //RSA
                return Sig_RSA_DSA_ECDSA.get_sig(1,s);
            case 2:
                //ECDSA
                return Sig_RSA_DSA_ECDSA.get_sig(2,s);
            case 3:
                //DSA
                return Sig_RSA_DSA_ECDSA.get_sig(3,s);
            case 4:
                //ECC
                break;
            default: break;
        }
        return s;
    }
}

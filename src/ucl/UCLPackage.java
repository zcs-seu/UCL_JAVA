package ucl;

import java.util.HashMap;
import java.util.Map;

import ucl.code.UCLCode;
import ucl.code.UCLCodeExtention;
import ucl.property.info.UCLPropertiesInfo;
import ucl.property.base.UCLPropertyBase;
import ucl.property.base.UCLPropertyHead;
import ucl.property.base.UCLPropertySet;
import tools.Encrypt;
import tools.Sig_RSA_DSA_ECDSA;

public class UCLPackage {
	/**
	* <p>Class description: UCL,UCL package class</p>
	* <p>Copyright 2016: Future network research center, Southeast University</p>
	* @author zhangcs
	* @version 1.0
	* @since 2016-12-05
	* modified by zhangcs at 2016-06-03 
	*/
	
	
	//private UCLCode uclCode;
	//private UCLCodeExtention uclCodeExtention;
	private UCLPropertyHead uclPropertyHead;
	/**
	 * modified by zhangcs at 2016-12-20 
	 */
	//private UCLPropertySet[] uclPropertySets=new UCLPropertySet[16];
	private Map<Integer,UCLPropertySet> propertySets=new HashMap<Integer, UCLPropertySet>();
	private UCLCode uclCode;
	private UCLCodeExtention uclCodeExtention;
    public static UCLPropertiesInfo UPI = new UCLPropertiesInfo();
	
	
	//���캯��
	public UCLPackage() {
		// TODO �Զ����ɵĹ��캯�����
		uclCode=new UCLCode();
		uclCodeExtention=new UCLCodeExtention();
		uclPropertyHead = new UCLPropertyHead();
		uclPropertyHead.setCategory(0x01); //language������
		uclPropertyHead.setTotalLength();
	}
	
	
	
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
	
	
	//uclPropertyHead-revised
	public UCLPropertyHead getUclPropertyHead() {
		
		return uclPropertyHead;
		
	}
	public void setUclPropertyHead(UCLPropertyHead uclPropertyHead) {
		
		this.uclPropertyHead = uclPropertyHead;
		
	}
	
	
	//propertySets-revised
    public Map<Integer, UCLPropertySet> getPropertySets(){
    	
    	return propertySets;
    	
    }
    public void setPropertySets(Map<Integer, UCLPropertySet> propertySets){
    	
    	this.propertySets=propertySets;
    	
    }
	
	
	//���á�ɾ�����Լ���-revised
	public boolean setPropertySet(UCLPropertySet propertySet){
		
		propertySets.put(propertySet.getHeadCategory(), propertySet);
        setUCL();
		return true;
		
	}
	public boolean delPropertySet(int category){
		
		propertySets.remove(category);
		return true;
		
	}
	
	
	//���� ɾ������
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
    
    
    //��ȡ��setPos���ϵĵ�propertyPos���Ե�vPart
	public String getValue(int setPos, int propertyPos){
		if(propertySets.containsKey(setPos)){
	    	return propertySets.get(setPos).getPropertyVPart(propertyPos);
	    }
	    return null;
	}

	public void setValue(int setPos, int propertyPos, String value){
		if(propertySets.containsKey(setPos)){
	    	propertySets.get(setPos).setPropertyVPart(propertyPos, value);
	    	setUCL();
	    } 
	}
	
	
	//����uclPropertyHead���-revised
    public void setHeadCategory(int category){
    	
    	uclPropertyHead.setCategory(category);
    	
    }
    public int getHeadCategory(){
    	
    	return uclPropertyHead.getCategory();
    	
    }
    
    //����uclPropertyHead helper-revised
    public void setHeadHelper(int helper){
    	
    	uclPropertyHead.setHelper(helper);
    	
    }
    public int getHeadHelper(){
    	
    	return uclPropertyHead.getHelper();
    	
    }

    //����propertySets����uclPropertyHead�Ŀ���ƥ��-self
    public int generateQuickMatcher(){
    	
    	int quickmatcher=0x0;
    	for(int i:propertySets.keySet()){
    		quickmatcher |= (0x01<<i);
    	}
    	return quickmatcher;
    	
    }
    
    //����propertySets����uclPropertyHead��vPart
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

    //����propertySets�������õĺ���
   public  void setUCL(){
       uclPropertyHead.setQuickMatcher(generateQuickMatcher());
       uclPropertyHead.setVPart(generateHeadVPart());
       uclPropertyHead.setQuickMatcher(generateQuickMatcher());
   }

    
    //����ͷ�����
    public String packCode(){
    	
    	return uclCode.packcode();
    	
    }
    //����ͷ�����
    public void unpackCode(String codepackage){
    	
    	uclCode.unpackcode(codepackage);
    	
    }
    
    
    //���Լ��ϴ�����
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
    			//�������Լ���ͷ�����Գ���ֵ�ֶ��ֽ���
                int lValueBytes = ((headVPart.toCharArray()[1+tmp] & 0x0FF) >> 6) + 1;
                //ȡ������ֵ�ֶ�
                char[] lValue = headVPart.substring(2+tmp, 2+tmp+lValueBytes).toCharArray();
                int lValueNum = 0;
                for(int j=0; j < lValue.length; j++)
                {
                	int cur = lValue[j] & 0x0FF;
                    switch (j)
                    {
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
    
    
    //UCL��Package���
    public String pack(){
    	
    	setValue(15, 15, "hello");//��һ��15��ʾCGPS���Լ��ϣ��ڶ���15��ʾ����ǩ��

        String temp = packCode() /*+ uclCodeExtension.pack()*/ + packPropertySets();
        Map<Integer, UCLPropertyBase> ps = propertySets.get(15).getProperties();
        //�������ǩ�����Լ���
        UCLPropertyBase sigUCLP = ps.get(15);

        int helper = sigUCLP.getHelper();
        int alg = sigUCLP.getLPartHead(2, 5);//algժҪ�㷨ID��tempΪǩ���㷨ID
        //����ժҪ
        String hash = genHash(alg, temp);
        //˽Կ����ժҪ������ǩ����
        String uclSigTemp = genSig(helper, hash);  //˽Կ����ժҪ

        setValue(15, 15, uclSigTemp);

        return packCode() /*+ uclCodeExtension.pack()*/ + packPropertySets();
    }
    
    //UCL��Package���
    public void unpack(String uclpackage){
    	String code=uclpackage.substring(0,32);
    	String property=uclpackage.substring(32,uclpackage.length());
    	unpackCode(code);
    	unpackPropertySets(property);
    	assert(checkUCL());
    }
    
    
    //����UCL������ǩ��
    public boolean checkUCL(){
        Map<Integer, UCLPropertyBase> ps = propertySets.get(15).getProperties();
        UCLPropertyBase sigUCLP = ps.get(15);

        String uclSig = getValue(15, 15);
        setValue(15, 15, "");
        String temp = packCode() /*+ uclCodeExtension.pack()*/ + packPropertySets();

        int helper = sigUCLP.getHelper();
        int alg = sigUCLP.getLPartHead(2, 5);
        String hashFromSig = genSig(helper, uclSig);  //��Կ���ܳ�Hashֵ
        String hashFromTemp = genHash(alg, temp);  //�Ƚ�Hashֵ

        if(hashFromTemp==hashFromSig){
        	return true;
        }else {
        	return false; 
        }
    }
    //��ӡUCL������
    public void showUCL(){
        uclCode.showCode();
        //uclCodeExtension.showCodeExt();

        System.out.println("--------------���Բ���----------------");
        int t = uclPropertyHead.getCategory();
        System.out.println("Ԫ��������: "+UCLPackage.UPI.getPropertyLangType(uclPropertyHead.getCategory()));
        System.out.println("���Լ�����: "+uclPropertyHead.getSize());
        System.out.println("--------------�������Լ�----------------");
        for(int propsetID : propertySets.keySet()) {
            System.out.println("**************************");
            propertySets.get(propsetID).showPropertySet();
        }
    }


    public static String genHash(int alg, String temp){
        String hash=null;

        switch(alg) {
            case 1: //CRC32
                hash = Encrypt.encrypt(temp, "CRC32");
                break;
            case 2: //MD5
                hash = Encrypt.encrypt(temp, "MD5");
                break;
            case 3: //SHA-256
                hash = Encrypt.encrypt(temp, "SHA-256");
                break;
            case 4: //SHA-512
                hash = Encrypt.encrypt(temp, "SHA-512");
                break;
            default: break;
        }
        return hash;
    }
    
    
    public static String genSig(int helper, String s){
        switch(helper)
        {
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

package cn.edu.seu.fnrc;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cn.edu.seu.fnrc.code.UCLCode;
import cn.edu.seu.fnrc.code.UCLCodeExtention;
import cn.edu.seu.fnrc.property.UCLPropertyBase;
import cn.edu.seu.fnrc.property.UCLPropertyHead;
import cn.edu.seu.fnrc.property.UCLPropertySet;

public class UCLPackage {
	/**
	* <p>Class description: UCL,UCL package class</p>
	* <p>Copyright 2016: Future network research center, Southeast University</p>
	* @author zhangcs
	* @version 1.0
	* @since 2016-12-05
	* modified by zhangcs at 2016-12-24 
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
	
	
	//构造函数
	public UCLPackage() {
		// TODO 自动生成的构造函数存根
		uclCode=new UCLCode();
		uclCodeExtention=new UCLCodeExtention();
		uclPropertyHead = new UCLPropertyHead();
		uclPropertyHead.setCategory(0x2); //language：English
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
	
	
	//设置、删除属性集合-revised
	public boolean setPropertySet(UCLPropertySet propertySet){
		
		propertySets.put(propertySet.getHeadCategory(), propertySet);
		return true;
		
	}
	public boolean delPropertySet(int category){
		
		propertySets.remove(category);
		return true;
		
	}
	
	//设置uclPropertyHead类别-revised
    public void setHeadCategory(int category){
    	
    	uclPropertyHead.setCategory(category);
    	
    }
    public int getHeadCategory(){
    	
    	return uclPropertyHead.getCategory();
    	
    }
    
    //设置uclPropertyHead helper-revised
    public void setHeadHelper(int helper){
    	
    	uclPropertyHead.setHelper(helper);
    	
    }
    public int getHeadHelper(){
    	
    	return uclPropertyHead.getHelper();
    	
    }

    //根据propertySets生成uclPropertyHead的快速匹配-self
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

    //设置propertySets后必须调用的函数
   public  void setUCL(){
	   
	   uclPropertyHead.setQuickMatcher(generateQuickMatcher());
	   uclPropertyHead.setVPart(generateHeadVPart());
	   uclPropertyHead.setTotalLength();
	   
   }

    //获取第setPos集合的第propertyPos属性的vPart
    public String getValue(int setPos, int propertyPos){
    	
    	assert(propertySets.get(setPos)!=null);
    	Map<Integer, UCLPropertyBase> properties = propertySets.get(setPos).getProperties();
    	assert(properties.get(propertyPos)!=null);
    	return properties.get(propertyPos).getVPart();
    	
    }

    
    //属性头部打包
    public String packCode(){
    	
    	return uclCode.packcode();
    	
    }
    //属性头部解包
    public void unpackCode(String codepackage){
    	
    	uclCode.unpackcode(codepackage);
    	
    }
    
    
    //ucl打包
    public String pack(){
    	
    	String code=packCode();
    	String property=packPropertySets();
    	return code+property;
    }
    
    //ucl解包
    public void unpack(String uclpackage){
    	String code=uclpackage.substring(0,32);
    	String property=uclpackage.substring(32,uclpackage.length());
    	unpackCode(code);
    	unpackPropertySets(property);
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
}

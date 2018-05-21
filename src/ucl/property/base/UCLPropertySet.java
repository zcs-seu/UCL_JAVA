package ucl.property.base;

import ucl.UCLPackage;

import java.util.HashMap;
import java.util.Map;

public class UCLPropertySet {
	/**
	* <p>Class description: UCLPropertySet,ucl PropertySet base class</p>
	* <p>Copyright 2016: Future network research center, Southeast University</p>
	* @author zhangcs
	* @version 1.0
	* @since 2016-12-05
	* modified by zhangcs at 2016-12-24
	*/
	
	//the head of UCLPropertySet
	private UCLPropertyHead propertyHead;
	//the propertyPart of UCLPropertySet
	/**
	 * modified by zhangcs at 2016-12-20 
	 */
	//private UCLPropertyBase[] properties=new UCLPropertyBase[16];
	private Map<Integer,UCLPropertyBase> properties=new HashMap<Integer, UCLPropertyBase>();
	
	
	public UCLPropertySet() {
		
		// TODO 自动生成的构造函数存根
		propertyHead = new UCLPropertyHead();
		
	}
	
	
	/**
	 * get the head of UCLPropertySet
	 * @return the head of UCLPropertySet
	 * @author zhangcs
	 * @since 2016-12-13
	 * modified by *** ****-**-** ***************
	 */
	public UCLPropertyHead getPropertyHead() {
		
		return propertyHead;
		
	}
	
	
	/**
	 * set the head of UCLPropertySet
	 * @param propertyHead the head of UCLPropertySet
	 * @author zhangcs
	 * @since 2016-12-13
	 * modified by *** ****-**-** ***************
	 */
	public boolean setPropertyHead(UCLPropertyHead propertyHead) {
		
		this.propertyHead = propertyHead;
		return true;
		
	}
	
	
	/**
	 * get the propertyPart of UCLPropertySet
	 * @return the propertyPart of UCLPropertySet
	 * @author zhangcs
	 * @since 2016-12-13
	 * modified by *** ****-**-** ***************
	 */
	public Map<Integer, UCLPropertyBase> getProperties() {
		
		return properties;
		
	}
	/**
	 * set the propertyPart of UCLPropertySet
	 * @param properties the propertyPart of UCLPropertySet
	 * @author zhangcs
	 * @since 2016-12-13
	 * modified by *** ****-**-** ***************
	 */
	public void setProperties(Map<Integer, UCLPropertyBase> properties) {
		
		this.properties = properties;
		
	}
	
	
	//设置properthHead类别
    public void setHeadCategory(int category){
    	
    	propertyHead.setCategory(category);
    	
    }
    public int getHeadCategory(){
    	
    	return propertyHead.getCategory();
    	
    }
    
    
    //设置propertyHead helper
    public void setHeadHelper(int helper)
    {
        propertyHead.setHelper(helper);
    }

    public int getHeadHelper()
    {
        return propertyHead.getHelper();
    }
    
    
    //设置属性 删除属性
    public boolean setProperty(UCLPropertyBase property){
    	
    	properties.put(property.getCategory(), property);
    	setSet();
    	return true;
    	
    }
    public boolean delProperty(int category){
    	
    	if(properties.get(category)!=null){
    		properties.remove(category);
    	}
    	setSet();
    	return true;
    	
    }
    
    //根据属性号查找属性集中的某个属性
    public UCLPropertyBase getProperty(int category)
    {
        if(properties.containsKey(category)){
        	return properties.get(category);
        }
        return null;
    }
    
    //设置属性号为pos的属性的值为value
    public void setPropertyVPart(int pos, String value)
    {
    	UCLPropertyBase prop = getProperty(pos);
    	if(prop!=null){
    		prop.setVPart(value);
    	}
        setSet();
    }
    
    //根据属性号pos的取得其对应的属性的属性值
    public String getPropertyVPart(int pos)
    {
        return properties.get(pos).getVPart();
    }

    //根据properties生成propertyHead的快速匹配
    public int generateQuickMatcher(){
    	int quickmatcher=0;
    	for(int i:properties.keySet()){
    		quickmatcher |= (0x01<<i);
    	}
    	return quickmatcher;
    }
    
    
    //根据properties生成propertyHead的vPart
    public String generateHeadVPart(){
    	
    	int qm = propertyHead.getQuickMatcher();
    	StringBuilder value = new StringBuilder();
    	for(int i=0; i < 16; i++)
        {
    		if((qm&(0x01<<i))!=0){
    			value.append(properties.get(i).pack());
    		}
        }

        return value.toString();
        
    }

    //初始化集合或设置properties后必须调用的函数
    public void setSet(){
    	
    	propertyHead.setQuickMatcher(generateQuickMatcher());
        propertyHead.setVPart(generateHeadVPart());
		propertyHead.setQuickMatcher(generateQuickMatcher());
    }
    
    
    //属性集合打包解包
    public String pack(){
    	
    	return propertyHead.pack();
    	
    }
    public void unpack(String propertySet){
    	
    	propertyHead.unpack(propertySet);
		String headVPart = propertyHead.getVPart();
    	int qm = propertyHead.getQuickMatcher();
    	int tmp = 0;
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
                            lValueNum = (0xffffff00 & lValueNum) | cur;
                            break;
                        case 1:
                            lValueNum = (0xffff00ff & lValueNum) | (cur << 8);
                            break;
                        case 2:
                            lValueNum = (0xff00ffff & lValueNum) | (cur << 16);
                            break;
                        case 3:
                            lValueNum = (0x00ffffff & lValueNum) | (cur << 24);
                            break;
                    }
                }
                String property  = headVPart.substring(tmp, tmp+lValueNum);
                UCLPropertyBase  pro=new UCLPropertyBase();
                pro.unpack(property);
                properties.put(i, pro);
                tmp += lValueNum;
    		}
    	}
    }
    
    /**
	 * 控制台输出属性集合
	 * @return void
	 * @author zhangcs
	 * @since 2017-5-12
	 */
    public void showPropertySet()
    {

		if (propertyHead.getCategory() == 1 || propertyHead.getCategory() == 15) {
			System.out.println("属性集合名: " + UCLPackage.UPI.getPropertySetCategory(propertyHead.getCategory()));
			System.out.println("属性集类别: " + (int)propertyHead.getCategory()+"    属性个数: "+(int)propertyHead.getSize());
			for(int category:properties.keySet()) {
				System.out.println("属性类别: "+properties.get(category).getCategory()+"    属性名: "+UCLPackage.UPI.getPropertyCategroy
						(propertyHead.getCategory(), properties.get(category).getCategory()));
				UCLPackage.UPI.showProperty(propertyHead.getCategory(), properties.get(category));
				System.out.println("------------------------------------");
			}
		}
		else {
			System.out.println("自定义属性集合");
			System.out.println("属性集类别: " + propertyHead.getCategory() + "    属性个数: " + propertyHead.getSize());

			for(int category:properties.keySet()) {
				System.out.println("属性类别和属性值: "+properties.get(category).getCategory()+
						"   "+properties.get(category).getVPart());
				System.out.println("------------------------------------");
			}
		}
    }
}

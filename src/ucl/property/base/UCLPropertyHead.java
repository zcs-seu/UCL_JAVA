package ucl.property.base;

public class UCLPropertyHead extends UCLPropertyBase {
	/**
	* <p>Class description: UCLPropertyHead,UCL PropertyHead base class</p>
	* <p>Copyright 2016: Future network research center, Southeast University</p>
	* Note:暂定以UCL属性部分总头部实现该类
	* @author zhangcs
	* @version 1.0
	* @since 2016-12-05
	*/
	
	
	public UCLPropertyHead() {
		// TODO 自动生成的构造函数存根
		setQuickMatcherBytesNum(2);
	}
	
	
	//number of UCLPropertySet:4b— —属性集合个数或属性元素个数
	public int getSize(){
		int cur = (int) (lPart&0x3C)>>2;
		return (int) ((cur & 0x0F)+1);
		
	}
	public boolean setSize(int size){
		
		assert(size<=16 && size > 0);
		size--;
	    lPart = (lPart & 0xFFFFFFFFFFFFFFc3L) | (size<<2);
	    return true;
	    
	}
	
	
	//fastMatch:2B
	public int getQuickMatcher(){
		int lPartValueBytesNum = getLPartValueBytesNum();
	    int quickMatcher=0;
	    switch (lPartValueBytesNum){
	        case 1:
	            quickMatcher = (int) ((lPart >> 16) & 0x0FFFF);
	            break;
	        case 2:
	            quickMatcher = (int) ((lPart >> 24) & 0x0FFFF);
	            break;
	        case 3:
	            quickMatcher = (int) ((lPart >> 32) & 0x0FFFF);
	            break;
	        case 4:
	            quickMatcher = (int) ((lPart >> 40) & 0x0FFFF);
	            break;
	    }
	    return quickMatcher;
	}
	public boolean setQuickMatcher(long j){
		//根据quickMatcher信息计算属性元素个数
		long temp = j;
	    byte size = 0;
	    for(int i=0;i<16;i++){
	    	if((temp&(0x01<<i))!=0){
	    		size++;
	    	}
	    }
	    setSize(size);

	    //设置LPart　quickMatcher部分
	    int lPartValueBytesNum = getLPartValueBytesNum();
	    switch (lPartValueBytesNum){
	        case 1:
	            lPart = (lPart & 0xFFFFFFFF0000FFFFL) | (j<<16);
	            break;
	        case 2:
	            lPart = (lPart & 0xFFFFFF0000FFFFFFL) | (j<<24);
	            break;
	        case 3:
	            lPart = (lPart & 0xFFFF0000FFFFFFFFL) | (j<<32);
	            break;
	        case 4:
	            lPart = (lPart & 0xFF0000FFFFFFFFFFL) | (j<<40);
	            break;
	    }
	    return true;
	}
}

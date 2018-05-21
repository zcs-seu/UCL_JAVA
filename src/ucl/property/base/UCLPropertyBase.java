package ucl.property.base;

import java.util.Arrays;

public class UCLPropertyBase {
	/**
	* <p>Class description: UCLProperty,UCL Property base class</p>
	* <p>Copyright 2016: Future network research center, Southeast University</p>
	* Note:Implement the class according to the Head of ALL UCL Properties
	* @author zhangcs
	* @version 1.0
	* @since 2016-12-05
	*/
	//Constant value
	private static final int TPART_BYTESNUM =1;
	private static final int LPARTHEAD_BYTESNUM =1;
	private static final int LPARTVALUE_BYTES_MIN =1;
	private static final int LPARTVALUE_BYTES_MAX =4;
	private static final long UINT32_MAX = 0x0FFFFFFFF;
	private static final long UINT24_MAX = 0x0FFFFFF;
	private static final long UINT16_MAX = 0x0FFFF;
	private static final long UINT8_MAX = 0x0FF;
	//T:1B
	protected int tPart;
	//L:<=7B
	protected long lPart;
	//V:
	protected String vPart;
	//该数据成员专为UCLPropertyHead类设计,为了更好的setTotalLength,getTotalLength
	int quickMatcherBytesNum;
	
	public UCLPropertyBase() {
		
		tPart=0;
		lPart = 0;
		vPart = "";
		quickMatcherBytesNum = 0;
	}
	
	
	/**
	 * get the number of bytes of QuickMatcher
	 * @return the number of bytes of QuickMatcher
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public int getQuickMatcherBytesNum(){
		
		return quickMatcherBytesNum;
		
	}
	/**
	 * set the number of bytes of QuickMatcher
	 * @param quickMatcherBytesNum the number of bytes of QuickMatcher
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setQuickMatcherBytesNum(int quickMatcherBytesNum){
		
		if(quickMatcherBytesNum==2){
			this.quickMatcherBytesNum=quickMatcherBytesNum;
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * get the tPart of UCLProperty(1 Byte)
	 * @return the tPart of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public int getTPart() {
		
		return tPart;
		
	}
	
	/**
	 * set the tPart of UCLProperty(1 Byte)
	 * @param tPart the tPart of UCLProperty to be set tPart
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setTPart(char tPart) {
		
		this.tPart = tPart&0x0FF;
		return true;
		
	}
	

	/**
	 * get the Category of UCLProperty(the first 4 bits of tPart)
	 * @return the Category of UCLProperty:may be Language type, Attribute set type
	 * 		or Attribute element category
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public int getCategory(){
		
		return tPart & 0x0F;
		
	}
	/**
	 * set the Category of UCLProperty(the first 4 bits of tPart)
	 * @param category the Category of UCLProperty to be set:may be Language type, 
	 * 		Attribute set type or Attribute element category
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setCategory(int category){
		
		if(category <= 15 && category >= 0){
			tPart &= 0x0F0;
			tPart |= category;
			return true;
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * get the Helper of UCLProperty(the second 4 bits of tPart)
	 * @return the Helper of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public int getHelper() {
		
		return (tPart>>4)&0x0F;
		
	}
	/**
	 * set the Helper of UCLProperty(the second 4 bits of tPart)
	 * @param helper the Category of UCLProperty to be set
	 * @author zhangcs
	 * @return 
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setHelper(int helper) {
		
		if(helper>=0&&helper<=15){
			tPart = (tPart & 0x0F) | (helper << 4);
			return true;
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * get the lPart of UCLProperty(<=7B)
	 * @return the lPart of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public long getLPart() {
		
		return lPart;
		
	}
	/**
	 * set the lPart of UCLProperty(<=7B)
	 * @param lPart the lPart of UCLProperty
	 * @author zhangcs
	 * @return 
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setLPart(long lPart) {
		
		this.lPart = lPart;
		return true;
		
	}
	
	
	/**
	 * get the Number of bytes of lPart of UCLProperty,containing the lPartHead(1B),
	 * 		LengthValue(1B~4B) and QuickMatcher(2B)
	 * @return the Number of bytes of lPart of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public int getLPartBytesNum(){
		
		return LPARTHEAD_BYTESNUM+getLPartValueBytesNum() + getQuickMatcherBytesNum();
		
	}
	
	
	/**
	 * get the lPartHead of UCLProperty(1B)
	 * @param begin the beginning index of lPartHead to be get
	 * @param end the endinning index of lPartHead to be get
	 * @return the lPartHead of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public int getLPartHead() {
		
		return (int) (lPart & 0x0FF);
		
	}
	
	public byte getLPartHead(int begin,int end) {
		int num=0;
		for(int i=begin;i<=end;i++){
			num+=Math.pow(2, i);
		}
		long tmp=lPart&num;
		byte lPartHead=(byte) (tmp>>begin);
		return lPartHead;
	}
	
	public boolean setLPartHead(int start, int end, int head)//head摘要算法（2,5）
	{
	    assert(start>=0 && start <= 7 && end>=0 && end<=7 && start < end);
	    long num=0,cur=1;
	    for(int i=start;i<=end;i++){
	    	num+=cur;
	    	cur*=2;
	    }
	    num <<= start;
	    head<<=start;
	    num=0x0FF-num;
	    lPart &= num;
	    lPart |= (head);
	    return true;
	}
	
	/**
	 * set the lPartHead of UCLProperty(1B)
	 * @param begin the beginning index of lPartHead to be set
	 * @param end the endinning index of lPartHead to be set
	 * @param end the value of lPartHead to be set
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setLPartHead(int lPartHead) {
		
		lPart = (lPart & 0x0FFFFFFFFFFFFFF00L) | (lPartHead & 0x0FF);
		return true;
		
	}
	
	
	/**
	 * get the number of bytes of LPartValue of UCLProperty
	 * 		(last 2 bits of the first byte of lPart)
	 * @return the number of bytes of LPartValue of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public int getLPartValueBytesNum(){
		
		return  (int) ((((lPart&0x0FF) >> 6) & 0x03) + 1);
		
	}
	/**
	 * set the number of bytes of LPartValue of UCLProperty
	 * 		(last 2 bits of the first byte of lPart)
	 * @param numsOfLengthValue number of bytes of LPartValue of UCLProperty
	 * @author zhangcs
	 * @return 
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setLPartValueBytesNum(int bytesNum){
		
		if(bytesNum>=1&&bytesNum<=4){
			lPart = (lPart & 0xFFFFFFFFFFFFFF3FL) | ((bytesNum - 1) << 6);
		    return true;
		}else{
			return false;
		}
	    
	}
	
	//length value:<=4B
	/**
	 * get the number of bytes of UCLProperty(1B+4~7B+2B)
	 * @return the number of bytes of LPart of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public long getTotalLength() {
		
		return TPART_BYTESNUM + getLPartBytesNum() + getVPartBytesNum();
		
	}
	/**
	 * set the number of bytes of LPart of UCLProperty(1B+4~7B+2B)
	 * @param totalLength the number of bytes of LPart of UCLProperty
	 * @author zhangcs
	 * @return 
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setTotalLength() {
		
		long totalLength = TPART_BYTESNUM + LPARTHEAD_BYTESNUM + getQuickMatcherBytesNum() + getVPartBytesNum();
	    assert((totalLength + LPARTVALUE_BYTES_MAX)<=UINT32_MAX);
	    if((totalLength + LPARTVALUE_BYTES_MIN) <= UINT8_MAX)
	    {
	        setLPartValueBytesNum(1);
	        lPart = (lPart & 0xFFFFFFFFFFFF00FFL) | ((totalLength + LPARTVALUE_BYTES_MIN)<<8);
	    }
	    else if ((totalLength + LPARTVALUE_BYTES_MIN) > UINT8_MAX && (totalLength + 2*LPARTVALUE_BYTES_MIN) <= UINT16_MAX)
	    {
	        setLPartValueBytesNum(2);
	        lPart = (lPart & 0xFFFFFFFFFF0000FFL) | ((totalLength + 2*LPARTVALUE_BYTES_MIN)<<8);
	    }
	    else if ((totalLength + 2*LPARTVALUE_BYTES_MIN) > UINT16_MAX && (totalLength + 3*LPARTVALUE_BYTES_MIN) <= UINT24_MAX)
	    {
	        setLPartValueBytesNum(3);
	        lPart = (lPart & 0xFFFFFFFF000000FFL) | ((totalLength + 3*LPARTVALUE_BYTES_MIN)<<8);
	    }
	    else if ((totalLength + 3*LPARTVALUE_BYTES_MIN) > UINT24_MAX && (totalLength + LPARTVALUE_BYTES_MAX) <= UINT32_MAX)
	    {
	        setLPartValueBytesNum(4);
	        lPart = (lPart & 0xFFFFFF00000000FFL) | ((totalLength + LPARTVALUE_BYTES_MAX)<<8);
	    }
	    return true;
		
	}
	
	
	/**
	 * get the vPart of UCLProperty
	 * @return the vPart of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public String getVPart() {
		
		return vPart;
		
	}
	/**
	 * set the vPart of UCLProperty
	 * @param vPart the vPart of UCLProperty to be set tPart
	 * @author zhangcs
	 * @return 
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public boolean setVPart(String vPart) {
		
		this.vPart = vPart;
		setTotalLength();
		return true;
		
	}
	
	
	/**
	 * get the number of bytes of vPart of UCLProperty
	 * @return the number of bytes of vPart of UCLProperty
	 * @author zhangcs
	 * @since 2016-12-12
	 * modified by zhangcs,2016-12-20,Keep consistent with ucl_cpp
	 */
	public long getVPartBytesNum(){
		
		return vPart.toCharArray().length;
		
	}
	
	
	//打包解包单个属性
	//打包
	public String pack(){
		StringBuilder property=new StringBuilder();
	    property.append((char)(tPart&0x0FF));

	    for(int i=0; i < getLPartBytesNum(); i++){
	    	char cur = (char) ((lPart>>(i*8))&0x0FF);
	        property.append(cur);
	    }
	    property.append(vPart);

	    return property.toString();
	}
	//解包
	public void unpack(String property){
		
		 //tPart
		char[] property_array=property.toCharArray();
	    tPart = property_array[0] & 0x0FF;
	    
	    //lPart
	    lPart = (lPart & 0xFFFFFFFFFFFFFF00L) | (property_array[1] & 0x0FF);
	    int  lPartValueBytesNum = getLPartValueBytesNum();
	    long quickMatcher = 0;
	    int cur0=property_array[TPART_BYTESNUM + LPARTHEAD_BYTESNUM + lPartValueBytesNum];
	    int cur1=property_array[TPART_BYTESNUM + LPARTHEAD_BYTESNUM + lPartValueBytesNum + 1];
	    quickMatcher = (quickMatcher & 0x0FF00) | (cur0 & 0x0FF);
	    quickMatcher = (quickMatcher & 0x0FF) | ((cur1 & 0x0FF) << 8);
	    
	    long totalLen = property_array.length;
	    switch (lPartValueBytesNum-1){
	        case 0:
	            lPart = (lPart & 0xffffffffffff00ffL) | (totalLen << 8);
	            lPart = (lPart & 0xffffffff0000ffffL) | (quickMatcher << 16);
	            break;
	        case 1:
	            lPart = (lPart & 0xffffffffff0000ffL) | (totalLen << 8);
	            lPart = (lPart & 0xffffff0000ffffffL) | (quickMatcher << 24);
	            break;
	        case 2:
	            lPart = (lPart & 0xffffffff000000ffL) | (totalLen << 8);
	            lPart = (lPart & 0xffff0000ffffffffL) | (quickMatcher << 32);
	            break;
	        case 3:
	            lPart = (lPart & 0xffffff00000000ffL) | (totalLen << 8);
	            lPart = (lPart & 0xff0000ffffffffffL) | (quickMatcher << 40);
	            break;
	    }

	    //vPart
	    char[] vPart_arr = Arrays.copyOfRange(property_array, TPART_BYTESNUM + LPARTHEAD_BYTESNUM + 
	    		lPartValueBytesNum + getQuickMatcherBytesNum(),property_array.length);
	    vPart=String.valueOf(vPart_arr);
		
	}
	public void setProperty(int category, int helper, String vPart) {
		assert(helper < 16);
		setCategory(category);
		setHelper(helper);
		setVPart(vPart);
	}
}

package cn.edu.seu.fnrc.code;
import cn.edu.seu.utils.CRC16;
public class UCLCode {
	/**
	* <p>Class description: UCLCode,UCL Code class</p>
	* <p>Copyright 2016: Future network research center, Southeast University</p>
	* @author heweiliang
	* @version 1.0
	* @since 2016-12-19
	*/
	
	private byte[] uclCode=new byte[32];

	
	/**
	 * get the version of UCL
	 * @param 
	 * @return the version of UCL
	 * @author heweiliang
	 * @since 2016-12-19
	 * modified by *** ****-**-** ***************
	 */
	public final byte getVersion(){
		return (byte) (uclCode[0]&0x07);
	}
	
	
	/**
	 * set the version of UCL
	 * @param version of UCL (1,2,3)
	 * @return 
	 * @author heweiliang
	 * @since 2016-12-19
	 * modified by *** ****-**-** ***************
	 */
    public final void setVersion(byte version){
    	uclCode[0]=(byte)(uclCode[0]&0xf8);
    	uclCode[0]|=(version&0x07);
    }
    
    /**
	 * get the type of media
	 * @param 
	 * @return the type of media 
	 * @author heweiliang
	 * @since 2016-12-13
	 * modified by *** ****-**-** ***************
	 */
    public final byte getTypeOfMedia(){
    	return (byte)((uclCode[0]&0xf8)>>3);
    }
    
    /**
   	 * set the type of media 
   	 * @param the type of media
   	 * @return
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
    public final void setTypeOfMedia(byte typeOfMedia){
    	uclCode[0]=(byte)(uclCode[0]&0x07);
    	uclCode[0]|=(typeOfMedia&0x1f)<<3;
    }
    
    
    /**
   	 * get the precedence
   	 * @param 
   	 * @return the precedence
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
    public final byte getPrecedence(){
    	return (byte)(uclCode[1]&0x0f);
    }
    
    /**
   	 * set the precedence
   	 * @param  the precedence
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
    public final void setPrecedence(byte precedence){
    	uclCode[1]=(byte)(uclCode[1]&0xf0);
    	uclCode[1]|=(precedence&0x0f);
    }
    
    
    /**
   	 * get the flag
   	 * @param  
   	 * @return the flag
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
    public final byte getFlag(){
    	return (byte)((uclCode[1]&0xf0)>>4);
    }
    
    /**
   	 * set the flag
   	 * @param  the flag
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setFlag(byte flag){
		uclCode[1]=(byte)(uclCode[1]&0x0f);
		
	    uclCode[1]|=((flag&0x0f)<<4);	
	}
	
	 /**
   	 * set first flag
   	 * @param  the first flag
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setFirstFlag(boolean firstflag ){
		if(firstflag)
		{
			byte flag=getFlag();
			flag|=0x01;
			setFlag(flag);
		}
		else
		{
			byte flag=getFlag();
			flag&=0xfe;
			setFlag(flag);
		}
	}
	
	 /**
   	 * get first flag
   	 * @param  
   	 * @return  the first flag
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final boolean getFirstFlag(){
		byte flag=getFlag();
		if((flag&0x01)==1)
			return true;
		else 
			return false;
	}
	
	
	
	 /**
   	 * set second flag
   	 * @param  the second flag
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setSecondFlag(boolean secondflag ){
		if(secondflag)
		{
			byte flag=getFlag();
			flag|=0x02;
			setFlag(flag);
		}
		else
		{
			byte flag=getFlag();
			flag&=0xfd;
			setFlag(flag);
		}
	}
	
	 /**
   	 * get second flag
   	 * @param  
   	 * @return  the second flag
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final boolean getSecondFlag(){
		byte flag=getFlag();
		if((flag&0x02)!=0)
			return true;
		else 
			return false;
	}

	 /**
   	 * set Third flag
   	 * @param  the Third flag
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setThirdFlag(boolean Thirdflag ){
		if(Thirdflag)
		{
			byte flag=getFlag();
			flag|=0x04;
			setFlag(flag);
		}
		else
		{
			byte flag=getFlag();
			flag&=0xfb;
			setFlag(flag);
		}
	}
	
	 /**
   	 * get third flag
   	 * @param  
   	 * @return  the third flag
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final boolean getThirdFlag(){
		byte flag=getFlag();
		if((flag&0x04)!=0)
			return true;
		else 
			return false;
	}
	
	 /**
   	 * set fourth flag
   	 * @param  the fourth flag
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setFourthFlag(boolean fourthflag ){
		if(fourthflag)
		{
			byte flag=getFlag();
			flag|=0x08;
			setFlag(flag);
		}
		else
		{
			byte flag=getFlag();
			flag&=0xf7;
			setFlag(flag);
		}
	}
	
	 /**
   	 * get fourth flag
   	 * @param  
   	 * @return  the fourth flag
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final boolean getFourthFlag(){
		byte flag=getFlag();
		if((flag&0x08)!=0)
			return true;
		else 
			return false;
	}
	
	
	/**
   	 * get the parserule
   	 * @param  
   	 * @return the parserule
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte[] getParseRule(){
		byte[] parserule=new byte[2];
		parserule[0]=uclCode[2];
		parserule[1]=(byte)(uclCode[3]&0x0f);
		return parserule;
		
	}
	
	/**
   	 * set the parserule
   	 * @param  the parserule
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setParseRule(byte[] parseRule){
		uclCode[2]=parseRule[0];
		uclCode[3]=(byte)(uclCode[3]&0xf0);
		uclCode[3]|=(parseRule[1]&0x0f);
		
	}
	
	/**
   	 * get the first part of parse rule
   	 * @param  
   	 * @return the first part of parse rule
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getFirstPartParseRule(){
		byte[] parserule=getParseRule();
		byte firstpartparserule=(byte) (parserule[0]&0x3f);
		return firstpartparserule;
	}
	
	/**
   	 * set the first part of parse rule
   	 * @param  first part of parse rule
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setFirstPartParseRule(byte firstpartparserule){
		byte[] parserule=getParseRule();
		parserule[0]&=0xc0;
		parserule[0]|=(firstpartparserule&0x3f);
		setParseRule(parserule);
	}
	
	
	/**
   	 * get the second part of parse rule
   	 * @param  
   	 * @return the second part of parse rule
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getSecondPartParseRule(){
		byte[] parserule=getParseRule();
		byte secondpartparserule=0;
		secondpartparserule|=(byte)((parserule[0]>>>6)&0x03);
		secondpartparserule|=(byte)(((parserule[1]&0x0f)<<2)&0x3c);
		/*
		int temp=0;
		temp|=(parserule[0]);
		temp&=0x000000ff;
		System.out.println("parserule:");
		System.out.println(Integer.toHexString(temp));
		temp=0;
		temp|=(parserule[0]>>>6);
		temp&=0x000000ff;
		System.out.println("temp:");
		System.out.println(Integer.toHexString(temp));
		*/
		return secondpartparserule;
	}
	
	/**
   	 * set the second part of parse rule
   	 * @param  second part of parse rule
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-16
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setSecondPartParseRule(byte secondpartparserule){
		byte[] parserule=getParseRule();
		parserule[0]&=0x3f;
		parserule[0]|=(secondpartparserule<<6);
		parserule[1]|=(byte)((secondpartparserule>>>2)&0x0f);
		setParseRule(parserule);
	}
	
	
	
	/**
   	 * get the source of content
   	 * @param  
   	 * @return the source of content
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final int getSourceOfContent(){
		int sourceofcontent=0;
		sourceofcontent|=((uclCode[3]&0xf0)>>>4);
		int temp=0;
		temp|=uclCode[4];
		temp&=0x000000ff;
		sourceofcontent|=(temp<<4);
		temp=0;
		temp|=uclCode[5];
		temp&=0x000000ff;
		sourceofcontent|=(temp<<12);
		temp=0;
		temp|=uclCode[6];
		temp&=0x000000ff;
		sourceofcontent|=(temp<<20);
		return sourceofcontent;
	}
	
	/**
   	 * set the source of content
   	 * @param  source of content
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setSourceOfContent(int sourceOfContent){
		byte temp=(byte)(sourceOfContent&0x0000000f);
		uclCode[3]=(byte)(uclCode[3]&0x0f);
		uclCode[3]|=(temp<<4);
		uclCode[4]=(byte)((sourceOfContent&0x00000ff0)>>>4);
		uclCode[5]=(byte)((sourceOfContent&0x000ff000)>>12);
		uclCode[6]=(byte)((sourceOfContent&0x0ff00000)>>20);
	}
	
	
	/**
   	 * get  first part source of content
   	 * @param  
   	 * @return the first part of source of content
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getFirstPartSourceOfContent(){
		byte firstpartsourceofcontent=(byte)(getSourceOfContent()&0x0000000f);
		return firstpartsourceofcontent;
	}
	
	/**
   	 * set first part source of content
   	 * @param  first part source of content
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setFirstPartSourceOfContent(byte firstpartsourceofcontent){
		int sourceofcontent=getSourceOfContent();
		sourceofcontent&=0xfffffff0;
		int temp=firstpartsourceofcontent;
		temp&=0x0000000f;
		sourceofcontent|=temp;
		setSourceOfContent(sourceofcontent);
		
	}

	
	/**
   	 * get  second part source of content
   	 * @param  
   	 * @return the second part of source of content
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final int getSecondPartSourceOfContent(){
		int firstpartsourceofcontent=(getSourceOfContent()&0x0ffffff0);
		firstpartsourceofcontent>>=4;
		return firstpartsourceofcontent;
	}
	
	/**
   	 * set Second part source of content
   	 * @param  Second part source of content
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setSecondPartSourceOfContent(int secondpartsourceofcontent){
		secondpartsourceofcontent&=0x00ffffff;
		secondpartsourceofcontent<<=4;
		int sourceofcontent=getSourceOfContent();
		sourceofcontent&=0x0000000f;
		sourceofcontent|=secondpartsourceofcontent;
		setSourceOfContent(sourceofcontent);
		
	}

	/**
   	 * get category
   	 * @param  
   	 * @return  category
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getCategory(){
		return uclCode[7];
	}
	
	/**
   	 * set category
   	 * @param  category
   	 * @return  
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setCategory(byte category){
		uclCode[7]=category;
	}
	
	/**
   	 * get subcategory
   	 * @param  
   	 * @return subcategory 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getSubcategory(){
		return uclCode[8];
	}
	
	/**
   	 * set subcategory
   	 * @param  subcategory 
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setSubcategory(byte subcategory){
		uclCode[8]=subcategory;
	}

	/**
   	 * get topic
   	 * @param  
   	 * @return topic
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final int getTopic(){
		int topic=0;
		topic|=uclCode[9];
		topic&=0x000000ff;
		
		int temp=0;
		temp|=uclCode[10];
		temp&=0x000000ff;
		temp<<=8;
		topic|=temp;
		
		temp=0;
		temp|=uclCode[11];
		temp&=0x000000ff;
		temp<<=16;
		topic|=temp;
		
		temp=0;
		temp|=uclCode[12];
		temp&=0x000000ff;
		temp<<=24;
		topic|=temp;
		return topic;
	}
	
	/**
   	 * set topic
   	 * @param  topic
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setTopic(int topic){
		uclCode[9]=(byte)(topic&0x000000ff);
		uclCode[10]=(byte)((topic&0x0000ff00)>>>8);
		uclCode[11]=(byte)((topic&0x00ff0000)>>>16);
		uclCode[12]=(byte)((topic&0xff000000)>>>24);
	}
	
	/**
   	 * get first part of topic
   	 * @param  
   	 * @return first part of topic
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getFirstPartOfTopic(){
		int topic=getTopic();
		return (byte)(topic&0x0000000f);
	}
	
	/**
   	 * set first part of topic
   	 * @param  first part of topic
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setFirstPartOfTopic(byte firstpartoftopic){
		int topic=getTopic();
		topic&=0xfffffff0;
		int temp=firstpartoftopic;
		temp&=0x0000000f;
		topic|=temp;
		setTopic(topic);
	}
	

	/**
   	 * get  second part of topic
   	 * @param  
   	 * @return second part of topic
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final int getSecondPartOfTopic(){
		int topic=getTopic();
		topic&=0xfffffff0;
		topic>>>=4;
		topic&=0x0ffffffff;
		return topic;
		
	}
	
	/**
   	 * set second part of topic
   	 * @param  second part of topic
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setSecondPartOfTopic(int  secondpartoftopic){
		int topic=getTopic();
		topic&=0x0000000f;
		secondpartoftopic<<=4;
		topic|=secondpartoftopic;
		setTopic(topic);
	}
	/**
   	 * get  type of content
   	 * @param  
   	 * @return type of content
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getTypeOfContent(){
		return uclCode[13];
	}
	
	/**
   	 * set  type of content
   	 * @param  type of content
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setTypeOfContent(byte typeOfContent){
		uclCode[13]=typeOfContent;
	}
	
	
	
	/**
   	 * get  copyright and len
   	 * @param  
   	 * @return copyright and len
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getCopyrightAndLen(){
		return uclCode[14];
	}
	
	/**
   	 * set  copyright and len
   	 * @param  copyright and len
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setCopyrightAndLen(byte copyrightAndLen){
		uclCode[14]=copyrightAndLen;
	}

	/**
   	 * get security energy level code
   	 * @param  
   	 * @return security energy level code
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final byte getSecEnerLevCode(){
		return uclCode[15];
	}
	
	/**
   	 * set security energy level code
   	 * @param  security energy level code
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setSecEnerLevCode(byte secEnerLevCode){
		uclCode[15]=secEnerLevCode;
	}

	/**
   	 * get time stamp
   	 * @param  security energy level code
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final int[] getTimeStamp(){
		int[] timestamp=new int[2];
		timestamp[0]=timestamp[1]=0;
		int temp=0;
		temp|=uclCode[16];
		temp&=0x000000ff;
		timestamp[0]|=temp;
		
		temp=0;
		temp|=uclCode[17];
		temp&=0x000000ff;
		temp<<=8;
		timestamp[0]|=temp;
		
		temp=0;
		temp|=uclCode[18];
		temp&=0x000000ff;
		temp<<=16;
		timestamp[0]|=temp;
		
		temp=0;
		temp|=uclCode[19];
		temp&=0x000000ff;
		temp<<=24;
		timestamp[0]|=temp;
		
		temp=0;
		temp|=uclCode[20];
		temp&=0x000000ff;
		timestamp[1]|=temp;
		
		temp=0;
		temp|=uclCode[21];
		temp&=0x000000ff;
		temp<<=8;
		timestamp[1]|=temp;
		
		temp=0;
		temp|=(uclCode[22]&0x03);
		temp&=0x000000ff;
		temp<<=16;
		timestamp[1]|=temp;
		
		return timestamp;
	}
	
	
	/**
   	 * set time stamp
   	 * @param  time stamp
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-19
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setTimeStamp(int[] timeStamp){
		uclCode[16]=(byte)(timeStamp[0]&0x000000ff);
		uclCode[17]=(byte)((timeStamp[0]&0x0000ff00)>>>8);
		uclCode[18]=(byte)((timeStamp[0]&0x00ff0000)>>>16);
		uclCode[19]=(byte)((timeStamp[0]&0xff000000)>>>24);
		
		uclCode[20]=(byte)(timeStamp[1]&0x000000ff);
		uclCode[21]=(byte)((timeStamp[1]&0x0000ff00)>>>8);
		uclCode[22]&=0xfc;
		uclCode[22]|=(byte)((timeStamp[1]&0x00030000)>>>16);
	}
	
	/**
   	 * get serial number
   	 * @param  
   	 * @return serial number
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final int getSerialNumber(){
		int serialnumber=0;
		int temp=0;
		temp|=(uclCode[22]&0xfc);
		temp&=0x000000ff;
		temp>>>=2;
		serialnumber|=temp;
		
		temp=0;
		temp|=uclCode[23];
		temp&=0x000000ff;
		temp<<=6;
		serialnumber|=temp;
		
		temp=0;
		temp|=uclCode[24];
		temp&=0x000000ff;
		temp<<=14;
		serialnumber|=temp;
		
		return serialnumber;
	}
	
	/**
   	 * set serial number
   	 * @param  
   	 * @return serial number
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setSerialNumber(int serialNumber){
		uclCode[22]&=0x03;
		uclCode[22]|=(byte)((serialNumber&0x0000003f)<<2);
		uclCode[23]=(byte)((serialNumber>>>6)&0x000000ff);
		uclCode[24]=(byte)((serialNumber>>>14)&0x000000ff);
	}

	/**
   	 * get reversed bytes
   	 * @param  
   	 * @return reversed bytes
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final int[] getReservedBytes(){
    	int[] reversedbytes=new int[2];
    	int temp=0;
    	temp|=uclCode[25];
    	temp&=0x000000ff;
    	reversedbytes[0]|=temp;
    	
    	temp=0;
    	temp|=uclCode[26];
    	temp&=0x000000ff;
    	temp<<=8;
    	reversedbytes[0]|=temp;
    	
    	temp=0;
    	temp|=uclCode[27];
    	temp&=0x000000ff;
    	temp<<=16;
    	reversedbytes[0]|=temp;
    	
    	temp=0;
    	temp|=uclCode[28];
    	temp&=0x000000ff;
    	temp<<=24;
    	reversedbytes[0]|=temp;
    	
    	
    	temp=0;
    	temp|=uclCode[29];
    	temp&=0x000000ff;
    	reversedbytes[1]|=temp;
    	
    	return reversedbytes;
    	
    	
    }
	
	/**
   	 * set reversed bytes
   	 * @param  reversed bytes
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setReservedBytes(int[] reservedBytes){
    	uclCode[25]=(byte)(reservedBytes[0]&0x000000ff);
    	uclCode[26]=(byte)((reservedBytes[0]>>>8)&0x000000ff);
    	uclCode[27]=(byte)((reservedBytes[0]>>>16)&0x000000ff);
    	uclCode[28]=(byte)((reservedBytes[0]>>>24)&0x000000ff);
    	
    	uclCode[29]=(byte)((reservedBytes[1])&0x000000ff);
    }

	/**
   	 * get Code CheckSum
   	 * @param  
   	 * @return Code CheckSum
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final short getCodeCheckSum(){
    	short checksum=0;
    	checksum|=uclCode[30];
    	checksum&=(short)(0x00ff);
    	
    	short temp=0;
    	temp|=uclCode[31];
    	temp<<=8;
    	checksum|=temp;
    	
    	return checksum;
    }
	
	/**
   	 * set Code CheckSum
   	 * @param  
   	 * @return Code CheckSum
   	 * @author heweiliang
   	 * @since 2016-12-13
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setCodeCheckSum(short codeCheckSum){
		uclCode[30]=(byte)(codeCheckSum&0x00ff);
		uclCode[31]=(byte)((codeCheckSum>>>8)&0x00ff);
	}
	
	
	/**
   	 * set Code CheckSum crc16
   	 * @param  
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-27
   	 * modified by *** ****-**-** ***************
   	 */
	public final void setCrc16(){
    	byte[] temp=new byte[30];
    	for(int i=0;i<30;i++)
    	{
    		temp[i]=uclCode[i];
    	}
    	int crccode=CRC16.calcCrc16(temp);
    	setCodeCheckSum((short)(crccode));
    }
	
	/**
   	 * check Crc16 
   	 * @param  
   	 * @return if the crc16 is the same return true else return false
   	 * @author heweiliang
   	 * @since 2016-12-27
   	 * modified by *** ****-**-** ***************
   	 */
	public final boolean checkCrc16(){
		byte[] temp=new byte[30];
    	for(int i=0;i<30;i++)
    	{
    		temp[i]=uclCode[i];
    	}
    	int crccodeInt=CRC16.calcCrc16(temp);
    	short crccodeShortComputed=(short)(crccodeInt);
    	short crccodeShort=getCodeCheckSum();
    	return crccodeShortComputed==crccodeShort;
	}
	
	
	
	
	/**
   	 * package code
   	 * @param  
   	 * @return string 
   	 * @author heweiliang
   	 * @since 2016-12-26
   	 * modified by *** ****-**-** ***************
   	 */
	public final String packcode()
	{
		char[] codepackage=new char[32];
		for(int i=0;i<32;i++)
		{
			codepackage[i]=(char)((uclCode[i])&0x000000ff);
		}
		return new String(codepackage);
	}
	
	/**
   	 * unpackage code
   	 * @param  string 
   	 * @return 
   	 * @author heweiliang
   	 * @since 2016-12-26
   	 * modified by *** ****-**-** ***************
   	 */
	public final void unpackcode(String codepackage)
	{
		char[] codepackageArr=codepackage.toCharArray();
		for(int i=0;i<32;i++)
		{
			uclCode[i]=(byte)(codepackageArr[i]);
		}
	}
}
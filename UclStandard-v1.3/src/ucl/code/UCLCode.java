package ucl.code;
import cn.edu.seu.utils.CRC16;
public class UCLCode {
	/**
	 * <p>Class description: UCLCode,ucl Code class</p>
	 * <p>Copyright 2016: Future network research center, Southeast University</p>
	 * @author heweiliang
	 * @version 1.0
	 * @since 2016-12-19
	 */

	private byte[] uclCode=new byte[32];

	/**
	 * get the version of ucl
	 * @param
	 * @return the version of ucl
	 * @author heweiliang
	 * @since 2016-12-19
	 * modified by *** ****-**-** ***************
	 */
	public final byte getVersion(){
		return (byte) (uclCode[0]&0x07);
	}


	/**
	 * set the version of ucl
	 * @param version of ucl (1,2,3)
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
	 * get the priority
	 * @param
	 * @return the priority
	 * @author heweiliang
	 * @since 2017-06-01
	 * modified by *** ****-**-** ***************
	 */
	public final byte getPriority(){
		return (byte)(uclCode[1]);
	}

	/**
	 * set the priority
	 * @param the priority
	 * @return
	 * @author heweiliang
	 * @since 2017-06-01
	 * modified by *** ****-**-** ***************
	 */
	public final void setPriority(byte priority){
		uclCode[1]=priority;
	}

	/**
	 * get the first part of priority
	 * @param
	 * @return the first part of priority
	 * @author heweiliang
	 * @since 2017-06-01
	 * modified by *** ****-**-** ***************
	 */
	public final byte getFirstPriority(){
		return (byte)(uclCode[1]&0x07);
	}

	/**
	 * set the first part of priority
	 * @param first part of priority
	 * @return
	 * @author heweiliang
	 * @since 2017-06-01
	 * modified by *** ****-**-** ***************
	 */
	public final void setFirstPriority(byte priority){
		uclCode[1]&=0xf8;
		uclCode[1]=(byte) (uclCode[1]|(priority&0x07));
	}


	/**
	 * get the Second part of priority
	 * @param
	 * @return the Second part of priority
	 * @author heweiliang
	 * @since 2017-06-01
	 * modified by *** ****-**-** ***************
	 */
	public final byte getSecondPriority(){
		byte secondpart= (byte)(uclCode[1]&0x18);
		secondpart=(byte) (secondpart>>>3);
		secondpart=(byte) (secondpart&0x03);
		return secondpart;
	}


	/**
	 * set the second part of priority
	 * @param second part of priority
	 * @return
	 * @author heweiliang
	 * @since 2017-06-01
	 * modified by *** ****-**-** ***************
	 */
	public final void setSecPriority(byte priority){
		uclCode[1]&=0xe7;
		priority=(byte) (priority<<3);
		uclCode[1]|=priority;
	}


	/**
	 * get the third part of priority
	 * @param
	 * @return the third part of priority
	 * @author heweiliang
	 * @since 2017-06-01
	 * modified by *** ****-**-** ***************
	 */
	public final byte getThirdPriority(){
		byte thirdpart= (byte)(uclCode[1]&0xe0);
		thirdpart=(byte) (thirdpart>>>5);
		thirdpart=(byte) (thirdpart&0x07);
		return thirdpart;
	}


	/**
	 * set the third part of priority
	 * @param third part of priority
	 * @return
	 * @author heweiliang
	 * @since 2017-06-01
	 * modified by *** ****-**-** ***************
	 */
	public final void setThirdPriority(byte priority){
		uclCode[1]&=0x1e;
		priority=(byte) (priority<<5);
		uclCode[1]|=priority;
	}


	/**
	 * get the flag
	 * @param
	 * @return the flag
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getFlag(){
		return uclCode[2];
	}

	/**
	 * set the flag
	 * @param  the flag
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setFlag(byte flag){
		uclCode[2]=flag;
	}

	/**
	 * set first flag
	 * @param  the first flag
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setFirstFlag(byte firstflag ){
		uclCode[2]&=0xfc;
		uclCode[2]|=(firstflag&0x03);
	}


	/**
	 * get first flag
	 * @param
	 * @return  the first flag
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getFirstFlag(){
		return (byte) (uclCode[2]&0x03);
	}


	/**
	 * set second flag
	 * @param  the second flag
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setSecondFlag(byte secondflag ){
		uclCode[2]&=0x03;
		secondflag<<=2;
		secondflag&=0x0c;
		uclCode[2]|=secondflag;
	}


	/**
	 * get second flag
	 * @param
	 * @return  the second flag
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getSecondFlag(){
		byte secondflag=(byte) (uclCode[2]&0x0c);
		return (byte) ((secondflag>>>2)&0x03);
	}


	/**
	 * set Third flag
	 * @param  the Third flag
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setThirdFlag(byte secondflag ){
		secondflag&=0x0f;
		secondflag<<=4;
		uclCode[2]|=secondflag;
	}


	/**
	 * get third flag
	 * @param
	 * @return  the third flag
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getThirdFlag(){
		byte thirdflag=(byte) (uclCode[2]&0xf0);
		return (byte) ((thirdflag>>>4)&0x0f);
	}


	/**
	 * get the parserule
	 * @param
	 * @return the parserule
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte[] getParseRule(){
		byte[] parserule=new byte[2];
		parserule[0]=uclCode[3];
		parserule[1]=(byte)(uclCode[4]&0x0f);
		return parserule;

	}


	/**
	 * set the parserule
	 * @param  the parserule
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setParseRule(byte[] parseRule){
		uclCode[3]=parseRule[0];
		uclCode[4]=(byte)(uclCode[4]&0xf0);
		uclCode[4]|=(parseRule[1]&0x0f);
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
	 * @since 2017-11-16
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
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getSecondPartParseRule(){
		byte[] parserule=getParseRule();
		byte secondpartparserule=0;
		secondpartparserule|=(byte)((parserule[0]>>>6)&0x03);
		secondpartparserule|=(byte)(((parserule[1]&0x0f)<<2)&0x3c);
		return secondpartparserule;
	}


	/**
	 * set the second part of parse rule
	 * @param  second part of parse rule
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
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
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final int getSourceOfContent(){
		int sourceofcontent=0;
		sourceofcontent|=((uclCode[4]&0xf0)>>>4);
		int temp=0;
		temp|=uclCode[5];
		temp&=0x000000ff;
		sourceofcontent|=(temp<<4);
		temp=0;
		temp|=uclCode[6];
		temp&=0x000000ff;
		sourceofcontent|=(temp<<12);
		temp=0;
		temp|=uclCode[7];
		temp&=0x000000ff;
		sourceofcontent|=(temp<<20);
		return sourceofcontent;
	}

	/**
	 * set the source of content
	 * @param  source of content
	 * @return
	 * @author heweiliang
	 * @since 2017-06-13
	 * modified by *** ****-**-** ***************
	 */
	public final void setSourceOfContent(int sourceOfContent){
		byte temp=(byte)(sourceOfContent&0x0000000f);
		uclCode[4]=(byte)(uclCode[4]&0x0f);
		uclCode[4]|=(temp<<4);
		uclCode[5]=(byte)((sourceOfContent&0x00000ff0)>>>4);
		uclCode[6]=(byte)((sourceOfContent&0x000ff000)>>12);
		uclCode[7]=(byte)((sourceOfContent&0x0ff00000)>>20);
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
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final int getSecondPartSourceOfContent(){
		int secondpartsourceofcontent=(getSourceOfContent()&0x0ffffff0);
		secondpartsourceofcontent>>=4;
		return secondpartsourceofcontent;
	}


	/**
	 * set Second part source of content
	 * @param  Second part source of content
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
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
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getCategory(){
		return uclCode[8];
	}


	/**
	 * set category
	 * @param  category
	 * @return
	 * @author heweiliang
	 * @since 2017-11-13
	 * modified by *** ****-**-** ***************
	 */
	public final void setCategory(byte category){
		uclCode[8]=category;
	}


	/**
	 * get subcategory
	 * @param
	 * @return subcategory
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getSubcategory(){
		return uclCode[9];
	}


	/**
	 * set subcategory
	 * @param  subcategory
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setSubcategory(byte subcategory){
		uclCode[9]=subcategory;
	}


	/**
	 * get topic
	 * @param
	 * @return topic
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final int getTopic(){
		int topic=0;
		topic|=uclCode[10];
		topic&=0x000000ff;

		int temp=0;
		temp|=uclCode[11];
		temp&=0x000000ff;
		temp<<=8;
		topic|=temp;

		temp=0;
		temp|=uclCode[12];
		temp&=0x000000ff;
		temp<<=16;
		topic|=temp;

		temp=0;
		temp|=uclCode[13];
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
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setTopic(int topic){
		uclCode[10]=(byte)(topic&0x000000ff);
		uclCode[11]=(byte)((topic&0x0000ff00)>>>8);
		uclCode[12]=(byte)((topic&0x00ff0000)>>>16);
		uclCode[13]=(byte)((topic&0xff000000)>>>24);
	}


	/**
	 * get first part of topic
	 * @param
	 * @return first part of topic
	 * @author heweiliang
	 * @since 2017-06-11
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
	 * @since 2017-11-19
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
	 * @since 2017-06-11
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
	 * @since 2017-06-11
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
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getTypeOfContent(){
		return uclCode[14];
	}


	/**
	 * set  type of content
	 * @param  type of content
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setTypeOfContent(byte typeOfContent){
		uclCode[14]=typeOfContent;
	}


	/**
	 * get  first part type of content
	 * @param
	 * @return first part type of content
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getFirstTypeOfContent(){
		return (byte) (uclCode[14]&0x0f);
	}


	/**
	 * set  first part type of content
	 * @param  first part type of content
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setFirstTypeOfContent(byte firsttypeOfContent){
		firsttypeOfContent&=0x0f;
		uclCode[14]&=0xf0;
		uclCode[14]|=firsttypeOfContent;
	}


	/**
	 * get  Second part type of content
	 * @param
	 * @return Second part type of content
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getSecondTypeOfContent(){
		return (byte) (((uclCode[14]&0xf0)>>>4)&0x0f);
	}


	/**
	 * set  second part type of content
	 * @param  second part type of content
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setSecondTypeOfContent(byte secondtypeOfContent){

		secondtypeOfContent&=0x0f;
		secondtypeOfContent<<=4;
		uclCode[14]&=0x0f;
		uclCode[14]|=secondtypeOfContent;
	}


	/**
	 * get security energy level code
	 * @param
	 * @return security energy level code
	 * @author heweiliang
	 * @since 2017-06-11
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
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setSecEnerLevCode(byte secEnerLevCode){
		uclCode[15]=secEnerLevCode;
	}


	/**
	 * set language
	 * @param language
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setLanguage(byte language){
		uclCode[16]=language;
	}


	/**
	 * get language
	 * @param
	 * @return language
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getLanguage(){
		return uclCode[16];
	}


	/**
	 * set size of content
	 * @param size of content
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final void setSizeOfContent(byte sizeofcontent){
		uclCode[17]&=0xe0;
		sizeofcontent&=0x1f;
		uclCode[17]|=sizeofcontent;
	}


	/**
	 * get size of content
	 * @param
	 * @return language
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final byte getSizeOfContent(){
		return (byte) (uclCode[17]&0x1f);
	}


	/**
	 * get time stamp
	 * @param  time stamp
	 * @return
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final int[] getTimeStamp(){
		int[] timestamp=new int[2];
		timestamp[0]=timestamp[1]=0;
		int temp=0;
		temp|=uclCode[17];
		temp>>>=5;
		temp&=0x00000003;
		timestamp[0]|=temp;

		temp=0;
		temp|=uclCode[18];
		temp&=0x000000ff;
		temp<<=3;
		timestamp[0]|=temp;

		temp=0;
		temp|=uclCode[19];
		temp&=0x000000ff;
		temp<<=11;
		timestamp[0]|=temp;

		temp=0;
		temp|=uclCode[20];
		temp&=0x000000ff;
		temp<<=19;
		timestamp[0]|=temp;

		temp=0;
		temp|=uclCode[21];
		temp&=0x0000001f;
		temp<<=27;
		timestamp[0]|=temp;


		temp=0;
		temp|=uclCode[21];
		temp>>>=5;
		temp&=0x00000007;
		timestamp[1]|=temp;

		temp=0;
		temp|=uclCode[22];
		temp&=0x000000ff;
		temp<<=3;
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
		uclCode[17]&=0x1f;

		uclCode[17]|=(byte)(timeStamp[0]&0x00000007);
		uclCode[18]=(byte)((timeStamp[0]>>>3)&0x000000ff);
		uclCode[19]=(byte)((timeStamp[0]>>>11)&0x000000ff);
		uclCode[20]=(byte)((timeStamp[0]>>>19)&0x000000ff);
		uclCode[21]&=0xe0;
		uclCode[21]|=(byte)((timeStamp[0]>>>27)&0x0000001f);
		uclCode[21]&=0x1f;

		uclCode[21]=(byte)((timeStamp[1]&0x00000007)<<5);
		uclCode[22]=(byte)((timeStamp[1]&0x0000ff00)>>>8);
	}


	/**
	 * get o serial number
	 * @param
	 * @return serial number
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final int getOneSerialNumber(){
		int serialnumber=0;
		serialnumber|=uclCode[23];

		return serialnumber;
	}


	public final int getTwoSerialNumber(){
		int serialnumber=0;
		int temp=0;
		serialnumber|=uclCode[23];
		temp|=uclCode[24];
		temp<<=8;
		serialnumber|=temp;
		return serialnumber;
	}


	public final int getThreeSerialNumber(){
		int serialnumber=0;
		int temp=0;
		serialnumber|=uclCode[23];
		temp|=uclCode[24];
		temp<<=8;
		serialnumber|=temp;

		temp=0;
		temp|=uclCode[25];
		temp<<=16;
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
	public final void setOneSerialNumber(int serialNumber){
		uclCode[23]=(byte)(serialNumber&0x000000ff);
	}


	public final void setTwoSerialNumber(int serialNumber){
		uclCode[23]=(byte)(serialNumber&0x000000ff);
		uclCode[24]=(byte)(serialNumber>>>8);
	}


	public final void setThreeSerialNumber(int serialNumber){
		uclCode[23]=(byte)(serialNumber&0x000000ff);
		uclCode[24]=(byte)(serialNumber>>>8);
		uclCode[25]=(byte)(serialNumber>>>16);
	}


	/**
	 * get multiplex bytes
	 * @param
	 * @return reversed bytes
	 * @author heweiliang
	 * @since 2017-06-11
	 * modified by *** ****-**-** ***************
	 */
	public final int[] getmultiplexBytes(){
		int[] reversedbytes=new int[2];
		int temp=0;
		temp|=uclCode[24];
		temp&=0x000000ff;
		reversedbytes[0]|=temp;

		temp=0;
		temp|=uclCode[25];
		temp&=0x000000ff;
		temp<<=8;
		reversedbytes[0]|=temp;

		temp=0;
		temp|=uclCode[26];
		temp&=0x000000ff;
		temp<<=16;
		reversedbytes[0]|=temp;

		temp=0;
		temp|=uclCode[27];
		temp&=0x000000ff;
		temp<<=24;
		reversedbytes[0]|=temp;
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
		uclCode[24]=(byte)(reservedBytes[0]&0x000000ff);
		uclCode[25]=(byte)((reservedBytes[0]>>>8)&0x000000ff);
		uclCode[26]=(byte)((reservedBytes[0]>>>16)&0x000000ff);
		uclCode[27]=(byte)((reservedBytes[0]>>>24)&0x000000ff);
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
		for(int i=0;i<30;i++) {
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
		for(int i=0;i<30;i++) {
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
	public final String packcode() {
		char[] codepackage=new char[32];
		for(int i=0;i<32;i++) {
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
	public final void unpackcode(String codepackage) {
		char[] codepackageArr=codepackage.toCharArray();
		for(int i=0;i<32;i++) {
			uclCode[i]=(byte)(codepackageArr[i]);
		}
	}

	/**
	 * show code
	 * @param
	 * @return
	 * @author heweiliang
	 * @since 2017-06-19
	 * modified by *** ****-**-** ***************
	 */
	public final void showCode()
	{

		System.out.print("Version:");
		System.out.println(this.getVersion());


		System.out.print("Type of Media:");
		byte typeofmedia;
		typeofmedia=this.getTypeOfMedia();
		int temp=typeofmedia;
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));



		System.out.print("Prio and Poli:");
		byte priority;
		priority=this.getPriority();
		temp=priority;
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));



		System.out.print("Flag:");
		int temp2=this.getFlag();
		temp2&=0x000000ff;
		System.out.println(Integer.toHexString(temp2));



		System.out.print("Parse Rule:");
		byte[] parserule=new byte[2];
		parserule=this.getParseRule();
		temp=0;
		temp=parserule[0];
		temp&=0x000000ff;
		System.out.print(Integer.toHexString(temp)+"\t");
		temp=parserule[1];
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));



		System.out.print("Source of Content:");
		temp=this.getSourceOfContent();
		System.out.println(Integer.toHexString(temp));



		System.out.print("Category:");
		temp=this.getCategory();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		System.out.print("Subcategory:");
		temp=this.getSubcategory();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));

		System.out.print("Topic:");
		System.out.println(Integer.toHexString(this.getTopic()));


		System.out.print("Copyright and Type of Cont:");
		temp=0;
		temp=this.getTypeOfContent();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));




		System.out.print("Security Energy Level Code:");
		temp=0;
		temp=this.getSecEnerLevCode();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));



		System.out.print("Language:");
		temp=0;
		temp=this.getLanguage();
		temp&=0x000000ff;
		System.out.println(Integer.toHexString(temp));



		System.out.print("Code Check:");
		temp=0;
		temp=this.getCodeCheckSum();
		temp&=0x0000ffff;
		System.out.println(Integer.toHexString(temp));


	}
}
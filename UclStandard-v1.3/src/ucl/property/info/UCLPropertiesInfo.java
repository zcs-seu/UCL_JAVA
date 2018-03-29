package ucl.property.info;

import ucl.UCLPackage;
import ucl.code.UCLCode;
import ucl.property.base.UCLPropertyBase;

import java.util.HashMap;
import java.util.Map;

import static test.Test_Pack.printPackString;

/**
 * Created by seu on 2017/6/14.
 */
public class UCLPropertiesInfo {
    public Map<Integer, String> propertyHeadMap = new HashMap<>();
    public Map<Integer, String> propertySetCategoryMap = new HashMap<>();
    public Map<Integer, Map<Integer, String>> propertyCategoryMap = new HashMap<>();

    public Map<Integer, String> entity = new HashMap<>();
    public Map<Integer, String> promap = new HashMap<>(); //provenance
    public Map<Integer, String> contentIdRulemap = new HashMap<>();
    public Map<Integer, String> seliMap = new HashMap<>();

    public Map<Integer, String> signatureMap = new HashMap<>();
    public Map<Integer, String> hash = new HashMap<>();


    public UCLPropertiesInfo() {
        initPropertyHeadMap();
        initPropertySetCategoryMap();
        initPropertyCategroyMap();

        initInfo();
    }

    public String[] split(String str, String pattern) {
        String[] res = str.split(pattern);
        return res;
    }

    public void initPropertyHeadMap() {
        propertyHeadMap.put(0,"保留");
        propertyHeadMap.put(1,"中文");
        propertyHeadMap.put(2,"英文");
        propertyHeadMap.put(3,"法文");
        propertyHeadMap.put(4,"俄文");
        propertyHeadMap.put(5,"阿拉伯文");
        propertyHeadMap.put(6,"西班牙文");
        propertyHeadMap.put(7,"葡萄牙文");
        propertyHeadMap.put(8,"德文");
        propertyHeadMap.put(9,"日文");
        // 10-14暂未定义
        propertyHeadMap.put(15,"其它语种");
    }

    public String getPropertyLangType(int type) {
        if (!propertyHeadMap.containsKey(type)){
            return "自定义类型";
        } else{
            return propertyHeadMap.get(type);
        }
    }

    public void initPropertySetCategoryMap() {
        // 0的属性集合保留，2～14属性集合未定义
        propertySetCategoryMap.put(1,"内容描述属性集合");
        propertySetCategoryMap.put(15,"内容管理属性集合");
    }

    public String getPropertySetCategory(int type) {
        if (!propertySetCategoryMap.containsKey(type))
            return "自定义集合";
        else
            return propertySetCategoryMap.get(type);
    }

    public void initPropertyCategroyMap(){
        Map<Integer, String> cdps = new HashMap<>();
        cdps.put(1,"内容标题") ;
        cdps.put(2,"内容关键词") ;
        cdps.put(3,"内容摘要") ;
        cdps.put(4,"内容作者") ;
        cdps.put(5,"内容实体") ;
        cdps.put(6,"内容标记") ;
        cdps.put(7,"版权信息") ;
        cdps.put(8,"原创声明") ;
        cdps.put(9,"文件信息") ;
        cdps.put(14,"关联UCL") ;
        cdps.put(15,"内容对象") ;

        propertyCategoryMap.put(1,cdps) ;

        Map<Integer, String> cgps = new HashMap<>();
        cgps.put(3,"内容出处") ;
        cgps.put(4,"内容ID") ;
        cgps.put(5,"传播路径") ;
        cgps.put(12,"内容数字签名") ;
        cgps.put(13,"安全能级信息") ;
        cgps.put(14,"内容责任链") ;
        cgps.put(15,"全UCL包数字签名") ;

        propertyCategoryMap.put(15,cgps);
    }

    public String getPropertyCategroy(int categroy, int proCategory) {
        return propertyCategoryMap.get(categroy).get(proCategory);
    }


    public void initInfo() {
        entity.put(0,"人");
        entity.put(1,"时");
        entity.put(2,"地");
        entity.put(3,"事");
        entity.put(4,"因");
        entity.put(5,"其他");

        promap.put(1,"网址或域名");
        promap.put(2,"机构名");
        promap.put(3,"应用相关");


        contentIdRulemap.put(1,"URI");
        contentIdRulemap.put(2,"DOI");
        contentIdRulemap.put(3,"ISBN");
        contentIdRulemap.put(4,"ISRC");


        seliMap.put(0,"自行标定");
        seliMap.put(1,"第一级内容提供商认证");
        seliMap.put(2,"第二级内容提供商认证");
        seliMap.put(14,"权威内容中心认证");


        signatureMap.put(0,"未对内容对象进行数字签名");
        signatureMap.put(1,"RSA");
        signatureMap.put(2,"ECDSA");
        signatureMap.put(3,"DSA");
        signatureMap.put(4,"ECC");
        signatureMap.put(5,"HMAC");


        hash.put(1,"CRC32");
        hash.put(2,"MD5");
        hash.put(3,"SHA-256");
        hash.put(4,"SHA-512");
    }

    public void showProperty(int category, UCLPropertyBase propertyBase){
        if (category == 1) {
            switch (propertyBase.getCategory()) {
                case 1:
                case 3:
                case 7:
                case 8:
                case 15:
                    showPropertyBase(propertyBase);
                    break;
                case 2:
                    showCDPSKeywords(propertyBase);
                    break;
                case 4:
                    showCDPSAuthor(propertyBase);
                    break;
                case 5:
                    showCDPSEntity(propertyBase);
                    break;
                case 6:
                    showCDPSTag(propertyBase);
                    break;
                case 9:
                    showCDPSFileInfo(propertyBase);
                    break;
                case 14:
                    showCDPSRelatedUCL(propertyBase);
                    break;
                default:
                    System.out.println("****自定义属性****");
            }
        }
        if (category == 15) {
            switch (propertyBase.getCategory()) {
                case 3:
                    showCGPSProvenance(propertyBase);
                    break;
                case 4:
                    showCGPSContentId(propertyBase);
                    break;
                case 5:
                    showCGPSPropagationPath(propertyBase);
                    break;
                case 12:
                    showCGPSSignatureContent(propertyBase);
                    break;
                case 13:
                    showCGPSSELI(propertyBase);
                    break;
                case 14:
                    showCGPSChainRespons(propertyBase);
                    break;
                case 15:
                    showCGPSSignatureUP(propertyBase);
                    break;
                default:
                    System.out.println("****自定义属性****");
            }
        }
    }

    /*
     * 包括：
     * CDPS:
     * 1 Title
     * 3 Abstract
     * 7 CopyRight
     * 8 Originality
     * 15 content object
     */
    void showPropertyBase(UCLPropertyBase propertyBase){
        System.out.println("属性值: "+propertyBase.getVPart());
    }

    //CDPS, Keywords
    void showCDPSKeywords(UCLPropertyBase keywords){
        int count = keywords.getLPartHead(3, 5) + 1;
        if (count <=7){
            System.out.println("关键词数量: "+count);
        }
        else{
            System.out.println("关键词数量超过7个");
        }

        System.out.print("关键词: ");

        String[] keys = split(keywords.getVPart(), ";");

        System.out.println(String.join(" ", keys));

    }

    //CDPS, author
    void showCDPSAuthor(UCLPropertyBase author){
        int authorCount = author.getLPartHead(0, 2);
        int comCount = author.getLPartHead(3, 5);
        if (authorCount < 7) {
            System.out.print("作者数量: "+authorCount);
        }
        else {
            System.out.print("作者数量超过6个   ");
        }

        if (comCount < 7) {
            System.out.println("公司数量: "+comCount);
        }
        else {
            System.out.println("公司数量超过七个");
        }

        System.out.println("作者 ----- 公司");
        String[] res = split(author.getVPart(), "\\\\r");

        for(String tmp:res) {
            String[] tr = split(tmp, ":");
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < tr.length; j++) {
                String[] ac = split(tr[j], ";");
                sb.append(String.join(" ",ac));
                if (j != tr.length - 1)
                    sb.append(" ----- ");
            }
            System.out.println(sb.toString());
        }
    }


    //CDPS, entity
    public void showCDPSEntity(UCLPropertyBase ent){

        String[] ev = split(ent.getVPart(), "\\\\r");

        for (int i = 0; i < ev.length; i++) {
            StringBuilder sb=new StringBuilder();
            sb.append(entity.get(i)+" : ");
            String[] evt = split(ev[i], ";");
            sb.append(String.join(" ",evt));
            System.out.println(sb.toString());
        }
    }

    //CDPS, tag
    public void showCDPSTag(UCLPropertyBase tag){
        int count = tag.getLPartHead(3, 5) + 1;
        if (count <=7) {
            System.out.println("标记数量: "+count);
        }
        else {
            System.out.println("标记数量超过7个");
        }

        System.out.print("内容标记: ");
        String[] keys = split(tag.getVPart(), ";");

        System.out.println(String.join(" ", keys));
    }

    //CDPS, fileInfo
    public void showCDPSFileInfo(UCLPropertyBase fileInfo){
        String[] files = split(fileInfo.getVPart(), ";");

        System.out.print("文件信息: ");
        System.out.println(String.join(" ", files));
    }

    //CDPS, relatedUCL
    public void showCDPSRelatedUCL(UCLPropertyBase relatedUCL){
        int count = relatedUCL.getLPartHead(3, 5) + 1;
        if (count <=7) {
            System.out.println("关联UCL数量: "+count);
        }
        else {
            System.out.println("关联UCL数量超过7个");
        }

        System.out.println("--------------------------关联UCL开始-----------------------------");

        //暂未考虑Code扩展
        String rus = relatedUCL.getVPart();
        char[] rusArr=rus.toCharArray();
        int pos = 0;
        while (pos < rus.length()) {
            String code = rus.substring(pos, pos+32);
            pos += 32;
            UCLCode uc=new UCLCode();
            uc.unpackcode(code);
            if ((uc.getFlag() & 0x2) == 0) {
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                uc.showCode();
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            }
            else {
                int lh = rusArr[pos + 1];
                int propertyLenBytes = ((lh >> 6) & 0x3) + 1;
                String lValue = rus.substring(pos + 2, pos+2+propertyLenBytes);
                char[] lValueArr=lValue.toCharArray();
                int len = 0;
                switch (propertyLenBytes)
                {
                    case 1:
                        len = (0xff & lValueArr[0]);
                        break;
                    case 2:
                        len = (0xff & lValueArr[0]) +(0xff00 & (lValueArr[1] << 8));
                        break;
                    case 3:
                        len = (0xff & lValueArr[0]) +(0xff00 & (lValueArr[1] << 8)) +
                                (0xff0000 & (lValueArr[2] << 16));
                        break;
                    case 4:
                        len = (0xff & lValueArr[0]) +(0xff00 & (lValueArr[1] << 8)) +
                                (0xff0000 & (lValueArr[2] << 16)) + (0xff000000 & (lValueArr[3] << 24));
                        break;
                }
                String ucls = code + rus.substring(pos, pos+len);
                UCLPackage ucl=new UCLPackage();
                ucl.unpack(ucls);
                printPackString(ucl.pack());
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                ucl.showUCL();
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                pos += len;
            }
        }

        System.out.println("--------------------------关联UCL结束-----------------------------");
    }

    //CGPS, provenance
    public void showCGPSProvenance(UCLPropertyBase provenance){
        System.out.println("内容出处描述形式:  "+promap.get((int)provenance.getLPartHead(3, 5)));
        System.out.println("内容出处:  "+provenance.getVPart() );
    }

    //CGPS, content id
    public void showCGPSContentId(UCLPropertyBase content){
        System.out.println("内容ID解析规则:  "+contentIdRulemap.get(content.getHelper()));
        System.out.println("详细内容ID信息:  "+content.getVPart() );
    }

    //CGPS, Propagation path
    public void showCGPSPropagationPath(UCLPropertyBase propagationPath){
        int count = propagationPath.getLPartHead(2, 5) + 1;
        if (count <=15) {
            System.out.println("传播路径长度: "+count);
        }
        else{
            System.out.println("传播路径长度超过15 ");
        }

        System.out.print("传播路径: ");
        String[] keys = split(propagationPath.getVPart(), ";");

        System.out.println(String.join(" ", keys));
    }

    //CGPS, Signature of Content
    public void showCGPSSignatureContent(UCLPropertyBase sigContent){
        System.out.println("数字摘要算法:  "+hash.get((int)sigContent.getLPartHead(2, 5)));
        System.out.println("数字签名算法:  "+signatureMap.get(sigContent.getHelper()));
        System.out.println("摘要或签名信息:  "+sigContent.getVPart());
    }

    //CGPS, Security Energy Level Information
    void showCGPSSELI(UCLPropertyBase seli){
        System.out.println("安全能级信息的认证等级:  "+seliMap.get(seli.getHelper()));
        System.out.println("安全能级详细信息:  "+seli.getVPart());
    }

    //CGPS, Chain of Responsibility
    void showCGPSChainRespons(UCLPropertyBase cr){
        int count = cr.getLPartHead(2, 5) + 1;
        if (count <= 15) {
            System.out.println("责任主体数量: "+count);
        }
        else {
            System.out.println("责任主体数量超过15个 ");
        }


        System.out.print("责任主体详细信息: ");
        String[] keys = split(cr.getVPart(), ";");

        System.out.println(String.join(" ", keys));
    }

    //CGPS, Signature of ucl Package
    void showCGPSSignatureUP(UCLPropertyBase sup){
        System.out.println("数字摘要算法:  "+hash.get((int)sup.getLPartHead(2, 5)));
        System.out.println("数字签名算法:  "+signatureMap.get(sup.getHelper()));
        System.out.println("摘要或签名信息:  "+sup.getVPart());
    }
}
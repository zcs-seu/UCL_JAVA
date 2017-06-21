package cn.edu.seu.fnrc.property;

/**
 * Created by seu on 2017/6/21.
 */
public class GenCDPSProperty {

    //title
    public static UCLPropertyBase genTitle(String vPart, int helper){
        UCLPropertyBase title=new UCLPropertyBase();
        title.setProperty(0x1, helper, vPart);

        return title;
    }

    /*
    第一子分量，长度头部字节，它的 0 ～ 7 位含义如下：
    第 0 ～ 2 位保留，含义暂未定义；
    第 3 ～ 5 位，其值加 1 表示内容关键词个数，一般不超过 5 个， 111 表示超过 7 个；
    第 6 ～ 7 位，其值加 1 表示长度值子分量所占字节数， 10、 11 为非法取值
     */
    //keywords
    public static UCLPropertyBase genKeywords(int count, String vPart, int helper){
        assert(count > 0);
        if (count > 7) count = 8;
        UCLPropertyBase keywords=new UCLPropertyBase();
        keywords.setLPartHead(3, 5, count-1);

        keywords.setProperty(0x2, helper, vPart);
        return keywords;
    }

    //abstract
    public static UCLPropertyBase genAbstract(String vPart, int helper){
        UCLPropertyBase aabstract=new UCLPropertyBase();
        aabstract.setProperty(0x3, helper, vPart);
        return aabstract;
    }
    /*
    第一子分量，长度头部字节，它的 0 ～ 7 位含义如下：
    第 0 ～ 2 位，其值表示作者人数， 111 表示超过 6 个；
    第 3 ～ 5 位，其值表示作者单位个数， 111 表示超过 6 个；
    第 6 ～ 7 位，其值加 1 表示长度值子分量所占字节数， 10、 11 为非法取值。
     */

    //author
    public static UCLPropertyBase genAuthor(int persons, int companies, String vPart, int helper){
        assert(persons>=0 && companies>=0);
        persons = persons>7?7:persons;
        companies = companies>7?7:companies;

        UCLPropertyBase author=new UCLPropertyBase();
        author.setLPartHead(0, 2, persons);
        author.setLPartHead(3, 5, companies);

        author.setProperty(0x4, helper, vPart);
        return author;
    }

    /*
    第一子分量，长度头部字节，它的 0 ～ 7 位含义如下：
    第 0 ～ 5 位，表示速配信息，快速指示具体包含有哪些内容实体，
    若其中的第 X 位（0 ≤ X ≤ 5）取 1，表示后续有类别号为 X 的内容实体；
    若其中的第 X 位（0 ≤ X ≤ 5）取 0，表示后续无类别号为 X 的内容实体。
    第 6 ～ 7 位，其值加 1 表示长度值子分量所占字节数， 10、 11 为非法取值。
     */
    //entity
    public static UCLPropertyBase genEntity(int quickMatch, String vPart, int helper){
        assert(quickMatch >= 0 && quickMatch <= 63);

        UCLPropertyBase entity=new UCLPropertyBase();
        entity.setLPartHead(0, 5, quickMatch);
        entity.setProperty(0x5, helper, vPart);

        return entity;
    }

    /*
    第一子分量，长度头部字节，它的 0 ～ 7 位含义如下：
    第 0 ～ 2 位保留，含义暂未定义；
    第 3 ～ 5 位，其值加 1 表示内容标记的个数，一般不超过 5 个， 111 表示超过 7 个；
    第 6 ～ 7 位，其值加 1 表示长度值子分量所占字节数， 10、 11 为非法取值。
     */
    //Tag
    public static UCLPropertyBase genTag(int count, String vPart, int helper){
        assert(count >=1);
        if (count > 7) count = 8;

        UCLPropertyBase tag=new UCLPropertyBase();
        tag.setLPartHead(3, 5, count - 1);
        tag.setProperty(6, helper, vPart);

        return tag;
    }

    //copyright
    public static UCLPropertyBase genCopyright(String vPart, int helper){
        UCLPropertyBase copyright=new UCLPropertyBase();
        copyright.setProperty(7, helper, vPart);

        return copyright;
    }

    //originality
    public static UCLPropertyBase genOriginality(String vPart, int helper){
        UCLPropertyBase originality=new UCLPropertyBase();
        originality.setProperty(8, helper, vPart);

        return originality;
    }

    //file description
    public static UCLPropertyBase genFileDescription(String vPart, int helper){
        UCLPropertyBase file=new UCLPropertyBase();
        file.setProperty(9, helper, vPart);

        return file;
    }


    /*
    第一子分量，长度头部字节，它的 0 ～ 7 位含义如下：
    第 0 ～ 2 位保留，含义暂未定义 ；
    第 3 ～ 5 位，其值加 1 表示 UCL  的个数， 111 表示超过 7 个；
    第 6 ～ 7 位，其值加 1 表示长度值子分量所占字节数， 10、 11 为非法取值。
     */
    //Related UCL
    public static UCLPropertyBase genRelatedUCL(int count, String vPart, int helper){
        assert(count >=1);
        if (count > 7) count = 8;

        UCLPropertyBase relatedUCL=new UCLPropertyBase();
        relatedUCL.setLPartHead(3, 5, count - 1);
        relatedUCL.setProperty(14, helper, vPart);
        return relatedUCL;
    }

    //Content Object
    public static UCLPropertyBase genContObject(String vPart, int helper){
        UCLPropertyBase contentObject=new UCLPropertyBase();
        contentObject.setProperty(15, helper, vPart);

        return contentObject;
    }

}

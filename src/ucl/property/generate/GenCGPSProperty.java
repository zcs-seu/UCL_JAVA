package ucl.property.generate;

import ucl.property.base.UCLPropertyBase;
import ucl.UCLPackage;

/**
 * Created by seu on 2017/6/21.
 */
public class GenCGPSProperty {

    public static UCLPropertyBase genProvenance(int des, String vPart, int helper){
        assert(des < 8);
        UCLPropertyBase provenance=new UCLPropertyBase();
        provenance.setLPartHead(3, 5, des);
        provenance.setProperty(0x3, helper, vPart);

        return  provenance;
    }

    //content id
    public static UCLPropertyBase genContId(String vPart, int helper){
        UCLPropertyBase contentid=new UCLPropertyBase();
        contentid.setProperty(0x4, helper, vPart);

        return contentid;
    }

    //propagation path
    public static UCLPropertyBase genPropagation(int count, String vPart, int helper){
        assert(count > 0);
        if (count > 15) count = 16;

        UCLPropertyBase propagation=new UCLPropertyBase();
        propagation.setLPartHead(2, 5, count-1);

        propagation.setProperty(5, helper, vPart);

        return propagation;
    }

    //signature of content
    public static UCLPropertyBase genContSig(String content, int alg, int helper){
        assert(helper <= 5);
        assert(alg < 5);

        UCLPropertyBase signature=new UCLPropertyBase();
        signature.setLPartHead(2, 5, alg);

        String hash = UCLPackage.genHash(alg, content);
        String signCont = UCLPackage.genSig(helper, hash);
        signature.setProperty(12, helper, signCont);

        return signature;
    }

    //security
    public static UCLPropertyBase genSecEL(String vPart, int helper){
        assert(helper == 0 || helper == 1 || helper == 2 || helper == 14);

        UCLPropertyBase security=new UCLPropertyBase();
        security.setProperty(13, helper, vPart);

        return security;
    }

    //chain  of  responsibility
    public static UCLPropertyBase genChainOfRes(int count, String vPart, int helper) {
        assert(count >=1);
        if (count > 15) count = 16;

        UCLPropertyBase chain=new UCLPropertyBase();
        chain.setLPartHead(2, 5, count-1);
        chain.setProperty(14, helper, vPart);

        return chain;
    }

    //signature of ucl package
    public static UCLPropertyBase genUCLSig(int alg, int helper)//alg摘要，helper签名
    {
        assert(helper <= 5);
        assert(alg < 5);

        UCLPropertyBase signature=new UCLPropertyBase();
        signature.setLPartHead(2, 5, alg);//alg摘要算法,helper签名算法
        signature.setProperty(15, helper, "hello");

        return signature;
    }
}

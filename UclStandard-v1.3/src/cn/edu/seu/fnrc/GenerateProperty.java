package cn.edu.seu.fnrc;

import cn.edu.seu.fnrc.property.UCLPropertyBase;
import cn.edu.seu.fnrc.property.UCLPropertySet;

public class GenerateProperty {
	//
	// Created by zp on 12/21/16.
	//
	
	
	public static void setProperty(UCLPropertyBase property, int category, int helper, String vPart){
		
		assert(helper < 16);
	    property.setCategory(category);
	    property.setHelper(helper);
	    property.setVPart(vPart);
	    property.setTotalLength();
		
	}
    /*
     * SNPS, PhysicalElements, Receiver
     */
    //PhysicalElements
	public static UCLPropertyBase generateSNPSPE(int quickMatcher, String vPart, int helper){
		
		quickMatcher &= 0x0FFFF;
		helper &= 0x0F;
		UCLPropertyBase pe=new UCLPropertyBase();

	    assert(quickMatcher<=0x3f);
	    pe.setLPartHead(0, 5, quickMatcher);

	    setProperty(pe, 0x1, helper, vPart);
	    return pe;
		
	}
	public static UCLPropertyBase generateSNPSPE(int quickMatcher, String vPart){
		
		quickMatcher &= 0x0FFFF;
		UCLPropertyBase pe=new UCLPropertyBase();

	    assert(quickMatcher<=0x3f);
	    pe.setLPartHead(0, 5, quickMatcher);

	    setProperty(pe, 0x1, 0x00, vPart);
	    return pe;
		
	}

    //Nominated Receiver
	public static UCLPropertyBase generateSNPSNR(String vPart, int helper){
		helper &= 0x0F;
		UCLPropertyBase nr=new UCLPropertyBase();
	    setProperty(nr, 0x2, helper, vPart);

	    return nr;
		
	}
	//Nominated Receiver
	public static UCLPropertyBase generateSNPSNR(String vPart){
		
		UCLPropertyBase nr=new UCLPropertyBase();
	    setProperty(nr, 0x2, 0x00, vPart);

	    return nr;
		
	}

    /*
     * CDPS, title, keywords, abstract, author, entity, copyright,  originality, file Description
     */
    //title
	public static UCLPropertyBase generateCDPSTitle(String vPart, int helper){
		
		helper &= 0x0F;
		UCLPropertyBase title=new UCLPropertyBase();
	    setProperty(title, 0x1, helper, vPart);

	    return title;
		
	}
	public static UCLPropertyBase generateCDPSTitle(String vPart){
		
		UCLPropertyBase title=new UCLPropertyBase();
	    setProperty(title, 0x1, 0x00, vPart);

	    return title;
		
	}

    //keywords
	public static UCLPropertyBase generateCDPSKeywords(int count, String vPart, int helper){
		
		helper &= 0x0F;
		assert(count <= 16 && count > 0);
	    UCLPropertyBase keywords=new UCLPropertyBase();
	    keywords.setLPartHead(2, 5, count-1);

	    setProperty(keywords, 0x2, helper, vPart);
	    return keywords;
		
	}
	public static UCLPropertyBase generateCDPSKeywords(int count, String vPart){
		
		assert(count <= 16 && count > 0);
	    UCLPropertyBase keywords=new UCLPropertyBase();
	    keywords.setLPartHead(2, 5, count-1);

	    setProperty(keywords, 0x2, 0x00, vPart);
	    return keywords;
		
	}

    //abstract
	public static UCLPropertyBase generateCDPSAbstract(String vPart, int helper){
		
		helper &= 0x0F;
		UCLPropertyBase ab=new UCLPropertyBase();
	    setProperty(ab, 0x3, helper, vPart);

	    return ab;
		
	}
	public static UCLPropertyBase generateCDPSAbstract(String vPart){
		
		UCLPropertyBase ab=new UCLPropertyBase();
	    setProperty(ab, 0x3, 0x00, vPart);

	    return ab;
		
	}

    //author
	public static UCLPropertyBase generateCDPSAuthor(int persons, int companies, String vPart, int helper){
		
		helper &= 0x0F;
		persons &= 0x0FF;
		companies &= 0x0FF;
		persons = persons>7?7:persons;
	    companies = companies>7?7:companies;

	    UCLPropertyBase author=new UCLPropertyBase();
	    author.setLPartHead(0, 2, persons);
	    author.setLPartHead(3, 5, companies);

	    setProperty(author, 4, helper, vPart);
	    return author;
		
	}
	public static UCLPropertyBase generateCDPSAuthor(int persons, int companies, String vPart){
		
		persons &= 0x0FF;
		companies &= 0x0FF;
		persons = persons>7?7:persons;
	    companies = companies>7?7:companies;

	    UCLPropertyBase author=new UCLPropertyBase();
	    author.setLPartHead(0, 2, persons);
	    author.setLPartHead(3, 5, companies);

	    setProperty(author, 4, 0x00, vPart);
	    return author;
		
	}

    //entity
	public static UCLPropertyBase generateCDPSEntity(int count, String vPart, int helper){
		
		helper &= 0x0F;
		assert(count <= 16 && count > 0);

	    UCLPropertyBase entity=new UCLPropertyBase();
	    entity.setLPartHead(2, 5, count-1);
	    setProperty(entity, 5, helper, vPart);

	    return entity;
	    
	}
	public static UCLPropertyBase generateCDPSEntity(int count, String vPart){
		
		assert(count <= 16 && count > 0);

	    UCLPropertyBase entity=new UCLPropertyBase();
	    entity.setLPartHead(2, 5, count-1);
	    setProperty(entity, 5, 0x00, vPart);

	    return entity;
	    
	}

    //copyright
	public static UCLPropertyBase generateCDPSCopyright(String vPart, int helper){
		
		helper &= 0x0F;
		UCLPropertyBase copyright=new UCLPropertyBase();
	    setProperty(copyright, 6, helper, vPart);

	    return copyright;
		
	}
	public static UCLPropertyBase generateCDPSCopyright(String vPart){
		
		UCLPropertyBase copyright=new UCLPropertyBase();
	    setProperty(copyright, 6, 0x00, vPart);

	    return copyright;
		
	}

    //originality
	public static UCLPropertyBase generateCDPSOriginality(String vPart, int helper){
		
		helper &= 0x0F;
		UCLPropertyBase originality=new UCLPropertyBase();
	    setProperty(originality, 7, helper, vPart);

	    return originality;
		
	}
	public static UCLPropertyBase generateCDPSOriginality(String vPart){
		
		UCLPropertyBase originality=new UCLPropertyBase();
	    setProperty(originality, 7, 0, vPart);

	    return originality;
		
	}

    //file description
	public static UCLPropertyBase generateCDPSFileDescription(String vPart, int helper){
		
		helper &= 0x0F;
		UCLPropertyBase file=new UCLPropertyBase();
	    setProperty(file, 8, helper, vPart);

	    return file;
		
	}
	public static UCLPropertyBase generateCDPSFileDescription(String vPart){
		
		UCLPropertyBase file=new UCLPropertyBase();
	    setProperty(file, 8, 0, vPart);

	    return file;
		
	}

    /*
     * CGPS, provenance, content id, propagation path, signature of content, security energy level information,
     *       chain of responibility, signature of UCL Package
     */
    //provenance
	public static UCLPropertyBase generateCGPSProvenance(int des, String vPart, int helper){
		
		helper &= 0x0F;
		assert(des < 8);
	    UCLPropertyBase provenance=new UCLPropertyBase();
	    provenance.setLPartHead(3, 5, des);
	    setProperty(provenance, 0x3, helper, vPart);

	    return  provenance;
		
	}
	public static UCLPropertyBase generateCGPSProvenance(int des, String vPart){
		
		assert(des < 8);
	    UCLPropertyBase provenance=new UCLPropertyBase();
	    provenance.setLPartHead(3, 5, des);
	    setProperty(provenance, 0x3, 0, vPart);

	    return  provenance;
		
	}

    //content id
	public static UCLPropertyBase generateCGPSContentid(String vPart, int helper){
		
		helper &= 0x0F;
		UCLPropertyBase contentid=new UCLPropertyBase();
	    setProperty(contentid, 0x4, helper, vPart);

	    return contentid;
		
	}
	public static UCLPropertyBase generateCGPSContentid(String vPart){
		
		UCLPropertyBase contentid=new UCLPropertyBase();
	    setProperty(contentid, 0x4, 0, vPart);

	    return contentid;
		
	}

    //propagation path
	public static UCLPropertyBase generateCGPSPropagation(int count, String vPart, int helper){
		
		helper &= 0x0F;
		assert(count <=16 && count > 0);

	    UCLPropertyBase propagation=new UCLPropertyBase();
	    propagation.setLPartHead(3, 5, count-1);

	    setProperty(propagation, 5, helper, vPart);

	    return propagation;
		
	}
	public static UCLPropertyBase generateCGPSPropagation(int count, String vPart){
		
		assert(count <=16 && count > 0);

	    UCLPropertyBase propagation=new UCLPropertyBase();
	    propagation.setLPartHead(3, 5, count-1);

	    setProperty(propagation, 5, 0, vPart);

	    return propagation;
		
	}

    //signature of content
	public static UCLPropertyBase generateCGPSSignatureContent(int alg, String vPart, int helper){
		
		helper &= 0x0F;
		assert(helper < 5);
	    assert(alg < 5);

	    UCLPropertyBase signature=new UCLPropertyBase();
	    signature.setLPartHead(3, 5, alg);
	    setProperty(signature, (byte) 12, helper, vPart);

	    return signature;
		
	}
	public static UCLPropertyBase generateCGPSSignatureContent(byte alg, String vPart){
		
	    assert(alg < 5);

	    UCLPropertyBase signature=new UCLPropertyBase();
	    signature.setLPartHead(3, 5, alg);
	    setProperty(signature, 12, 0, vPart);

	    return signature;
		
	}

    //security
	public static UCLPropertyBase generateCGPSSecurity(String vPart, int helper){
		
		assert(helper == 0 || helper == 1 || helper == 2 || helper == 14);

	    UCLPropertyBase security=new UCLPropertyBase();
	    setProperty(security, 13, helper, vPart);

	    return security;
		
	}
	public static UCLPropertyBase generateCGPSSecurity(String vPart){

	    UCLPropertyBase security=new UCLPropertyBase();
	    setProperty(security, 13, 0, vPart);

	    return security;
		
	}

    //chain of responsibility
	public static UCLPropertyBase generateCGPSChain(int count, String vPart, int helper){
		
		helper &= 0x0F;
		assert(count <= 8 && count >=1);

	    UCLPropertyBase chain=new UCLPropertyBase();
	    chain.setLPartHead(3, 5, count-1);
	    setProperty(chain, (byte) 14, helper, vPart);

	    return chain;
		
	}
	public static UCLPropertyBase generateCGPSChain(int count, String vPart){
		
		assert(count <= 8 && count >=1);

	    UCLPropertyBase chain=new UCLPropertyBase();
	    chain.setLPartHead(3, 5, count-1);
	    setProperty(chain, 14, 0, vPart);

	    return chain;
		
	}

    //signature of UCL package
	public static UCLPropertyBase generateCGPSSignatureUCL(int alg, String vPart, int helper){
		
		assert(helper < 5);
	    assert(alg < 5);

	    UCLPropertyBase signature=new UCLPropertyBase();
	    signature.setLPartHead(3, 5, alg);
	    setProperty(signature, 15, helper, vPart);

	    return signature;
	}

    //生成必选集合
	public static UCLPropertySet generateCDPS(String title){
		
		UCLPropertySet cdps=new UCLPropertySet();
	    cdps.setHeadCategory(1);

	    UCLPropertyBase titlePro = generateCDPSTitle(title);
	    cdps.setProperty(titlePro);
	    cdps.setSet();

	    return cdps;
		
	}
	public static UCLPropertySet generateCGPS(CGPSRequired required){
		
		UCLPropertySet cgps=new UCLPropertySet();
	    cgps.setHeadCategory(15);

	    UCLPropertyBase provenance = generateCGPSProvenance(required.proDes, required.provenance);
	    cgps.setProperty(provenance);

	    UCLPropertyBase security = generateCGPSSecurity(required.security, required.secHelper);
	    cgps.setProperty(security);

	    UCLPropertyBase chain = generateCGPSChain(required.chainCount, required.chain);
	    cgps.setProperty(chain);

	    UCLPropertyBase sigUCL = generateCGPSSignatureUCL(required.sigU[0], required.sigUCL, required.sigU[1]);
	    cgps.setProperty(sigUCL);

	    cgps.setSet();
	    return cgps;
		
	}
	

}

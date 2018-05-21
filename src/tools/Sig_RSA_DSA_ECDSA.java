package tools;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
public class Sig_RSA_DSA_ECDSA {
    //进行数字签名类型选择，看最新版本的ucl   1是RSA 2是ECDSA 3是DSA   暂时后面两个（ECC和HMAC 没有找到方法）
    public static void main(String[] args) {
        String aabstract="abc";
        String sig=Sig_RSA_DSA_ECDSA.get_sig(1,aabstract);
        check(1);
        System.out.println(sig);
    }
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            if (n < b.length - 1)
                hs = hs + ":";
        }
        return hs.toUpperCase();
    }
    public static ArrayList<String> get_type_string(int style){
        try {
            ArrayList<String> strings=new ArrayList<>();
            switch (style) {
                case 1:   //RSA
                    strings.add("RSA");
                    strings.add("MD5withRSA");
                    break;
                case 2:   //ESCDA
                    strings.add("EC");
                    strings.add("SHA1withECDSA");
                    break;
                case 3:   //DSA
                    strings.add("DSA");
                    strings.add("SHA1withDSA");
                    break;
                default:
                    break;
            }

            return strings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 生成一对文件 myprikey.dat 和 mypubkey.dat 私钥和公钥
    // 公钥要用户发送 ( 文件 , 网络等方法 ) 给其它用户 , 私钥保存在本地
    public static boolean generatekey(ArrayList<String> strings ) {
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(strings.get(0));
            //keygen.initialize(512);
            KeyPair keys = keygen.genKeyPair();
            PublicKey pubkey = keys.getPublic();
            PrivateKey prikey = keys.getPrivate();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("myprikey.dat"));
            out.writeObject(prikey);
            out.close();
            System.out.println("写入对象 prikeys ok");
            out = new ObjectOutputStream(new FileOutputStream("mypubkey.dat"));
            out.writeObject(pubkey);
            out.close();
            System.out.println("写入对象 pubkeys ok");
            System.out.println("生成密钥对成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成密钥对失败");
            return false;
        }
    }


    //此函数用于生成数字签名，并将其返回。
    public static String get_sig(int style,String myinfo){//根据传入的类型进行操作
        ArrayList<String> strings=get_type_string(style);
        // 数字签名生成密钥
        // 第一步生成密钥对,如果已经生成过 , 本过程就可以跳过
        // 对用户来讲 myprikey.dat 要保存在本地，而 mypubkey.dat 给发布给其它用户
        if ((new java.io.File("myprikey.dat")).exists() == false) {
            if (generatekey(strings) == false) {
                System.out.println("生成密钥对败");
                return null;
            }
        }

        // 第二步 , 此用户
        // 从文件中读入私钥 , 对一个字符串进行签名后保存在一个文件 (myinfo.dat) 中
        // 并且再把 myinfo.dat 发送出去，为了方便数字签名也放进了 myifno.dat 文件中 , 当然也可分别发送
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("myprikey.dat"));
            PrivateKey myprikey = (PrivateKey) in.readObject();
            in.close();
            //myinfo = "我是来自东南大学的张晓刚，很高兴能够在未来网络研究室工作。"; // 要签名的信息
            // 用私钥对信息生成数字签名
            Signature signet = Signature.getInstance(strings.get(1));
            signet.initSign(myprikey);
            signet.update(myinfo.getBytes());
            byte[] signed = signet.sign(); // 对信息的数字签名
            String sig=byte2hex(signed);
            //System.out.println("signed( 签名内容 )=" + sig);
            // 把信息和数字签名保存在一个文件中
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("myinfo.dat"));
            out.writeObject(myinfo);
            out.writeObject(signed);
            out.close();
            //System.out.println("签名并生成文件成功");
            return sig;
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("签名并生成文件失败");
        }
        return null;

    }
    //此函数用于验证数字签名是否正确
    public static boolean check(int style ) {
        ArrayList<String> strings=get_type_string(style);


        // 第三步 获得信息检查
        // 其他人通过公共方式得到此户的公钥和文件
        // 其他人用此户的公钥 , 对文件进行检查 , 如果成功说明是此用户发布的信息 .
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("mypubkey.dat"));
            PublicKey pubkey = (PublicKey) in.readObject();
            in.close();
            //System.out.println("AA   "+pubkey.getFormat());
            in = new ObjectInputStream(new FileInputStream("myinfo.dat"));
            String info = (String) in.readObject();
            byte[] signed = (byte[]) in.readObject();
            in.close();
            Signature signetcheck = Signature.getInstance(strings.get(1));
            signetcheck.initVerify(pubkey);
            signetcheck.update(info.getBytes());
            if (signetcheck.verify(signed)) {
                //System.out.println("info=" + info);
                //System.out.println("签名正常");
                return true;
            } else
                //System.out.println("非签名正常");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        return false;
    }
}  
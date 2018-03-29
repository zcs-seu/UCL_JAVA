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
    //��������ǩ������ѡ�񣬿����°汾��ucl   1��RSA 2��ECDSA 3��DSA   ��ʱ����������ECC��HMAC û���ҵ�������
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
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;

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
    // ����һ���ļ� myprikey.dat �� mypubkey.dat ˽Կ�͹�Կ
    // ��ԿҪ�û����� ( �ļ� , ����ȷ��� ) �������û� , ˽Կ�����ڱ���
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
            System.out.println("д����� prikeys ok");
            out = new ObjectOutputStream(new FileOutputStream("mypubkey.dat"));
            out.writeObject(pubkey);
            out.close();
            System.out.println("д����� pubkeys ok");
            System.out.println("������Կ�Գɹ�");
            return true;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            System.out.println("������Կ��ʧ��");
            return false;
        }
    }


    //�˺���������������ǩ���������䷵�ء�
    public static String get_sig(int style,String myinfo){//���ݴ�������ͽ��в���
        ArrayList<String> strings=get_type_string(style);
        // ����ǩ��������Կ
        // ��һ��������Կ��,����Ѿ����ɹ� , �����̾Ϳ�������
        // ���û����� myprikey.dat Ҫ�����ڱ��أ��� mypubkey.dat �������������û�
        if ((new java.io.File("myprikey.dat")).exists() == false) {
            if (generatekey(strings) == false) {
                System.out.println("������Կ�԰�");
                return null;
            }
        }

        // �ڶ��� , ���û�
        // ���ļ��ж���˽Կ , ��һ���ַ�������ǩ���󱣴���һ���ļ� (myinfo.dat) ��
        // �����ٰ� myinfo.dat ���ͳ�ȥ��Ϊ�˷�������ǩ��Ҳ�Ž��� myifno.dat �ļ��� , ��ȻҲ�ɷֱ���
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("myprikey.dat"));
            PrivateKey myprikey = (PrivateKey) in.readObject();
            in.close();
            //myinfo = "�������Զ��ϴ�ѧ�������գ��ܸ����ܹ���δ�������о��ҹ�����"; // Ҫǩ������Ϣ
            // ��˽Կ����Ϣ��������ǩ��
            Signature signet = Signature.getInstance(strings.get(1));
            signet.initSign(myprikey);
            signet.update(myinfo.getBytes());
            byte[] signed = signet.sign(); // ����Ϣ������ǩ��
            String sig=byte2hex(signed);
            //System.out.println("signed( ǩ������ )=" + sig);
            // ����Ϣ������ǩ��������һ���ļ���
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("myinfo.dat"));
            out.writeObject(myinfo);
            out.writeObject(signed);
            out.close();
            //System.out.println("ǩ���������ļ��ɹ�");
            return sig;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            //System.out.println("ǩ���������ļ�ʧ��");
        }
        return null;

    }
    //�˺���������֤����ǩ���Ƿ���ȷ
    public static boolean check(int style ) {
        ArrayList<String> strings=get_type_string(style);


        // ������ �����Ϣ���
        // ������ͨ��������ʽ�õ��˻��Ĺ�Կ���ļ�
        // �������ô˻��Ĺ�Կ , ���ļ����м�� , ����ɹ�˵���Ǵ��û���������Ϣ .
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
                //System.out.println("ǩ������");
                return true;
            } else
                //System.out.println("��ǩ������");
            return false;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        ;
        return false;
    }
}  
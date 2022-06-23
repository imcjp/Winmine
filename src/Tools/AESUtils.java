package Tools;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
/**
 *
 * @author Administrator
 *
 */
public class AESUtils {
	private static String bytes2Str(byte[] bs) {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < bs.length; i++) {
			int b=bs[i];
			if (b<0) {
				b+=256;
			}
			int b0=b%16;
			if (b0<10) {
				sb.append(b0);
			}else{
				char c=(char) ('A'+(b0-10));
				sb.append(c);
			}
			b0=b/16;
			if (b0<10) {
				sb.append(b0);
			}else{
				char c=(char) ('A'+(b0-10));
				sb.append(c);
			}
		}
		return sb.toString();
	}
	private static byte[] Str2bytes(String str) {
		List<Byte> ls=new ArrayList<Byte>();
		if (str==null||str.length()%2!=0||!str.matches("[A-F\\d]+")) {
			return null;
		}
		for (int i = 0; i < str.length(); i+=2) {
			int b=0;
			char c=str.charAt(i+1);
			if (c>='A'&&c<='F') {
				b+=10+(c-'A');
			}else {
				b+=(c-'0');
			}
			b*=16;
			c=str.charAt(i);
			if (c>='A'&&c<='F') {
				b+=10+(c-'A');
			}else {
				b+=(c-'0');
			}
			byte b0=0;
			if (b>0) {
				b0=(byte) b;
			}else {
				b0=(byte) (b-256);
			}
			ls.add(b0);
		}
		byte[] res=new byte[ls.size()];
		for (int i = 0; i < res.length; i++) {
			res[i]=ls.get(i);
		}
		return res;
	}
    // ����
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        // �ж�Key�Ƿ���ȷ
        if (sKey == null) {
        	sKey="                ";
        }
        // �ж�Key�Ƿ�Ϊ16λ
        if (sKey.length() != 16) {
        	if (sKey.length()>16) {
				sKey.subSequence(0, 16);
			}else{
				sKey+="                ".substring(sKey.length());
			}
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"�㷨/ģʽ/���뷽ʽ"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return bytes2Str(encrypted);//�˴�ʹ��BASE64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�
    }
 
    // ����
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // �ж�Key�Ƿ���ȷ
            if (sKey == null) {
            	sKey="                ";
            }
            // �ж�Key�Ƿ�Ϊ16λ
            if (sKey.length() != 16) {
            	if (sKey.length()>16) {
					sKey.subSequence(0, 16);
				}else{
					sKey+="                ".substring(sKey.length());
				}
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Str2bytes(sSrc);//����base64����
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                return "�������";
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            return null;
        }
    }
 
}

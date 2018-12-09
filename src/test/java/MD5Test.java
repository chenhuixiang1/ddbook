import com.oraclewfk.bookmarket.util.MD5Util;


public class MD5Test {
    public static void main(String[] args) throws Exception{
        String str= MD5Util.getEncryptedPwd("admin");
        System.out.println(str);
    }
}

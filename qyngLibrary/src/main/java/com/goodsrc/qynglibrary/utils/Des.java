package com.goodsrc.qynglibrary.utils;


import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Des {
    public static final String ALGORITHM_DES = "DES/ECB/PKCS7Padding";

    /**
     * 　　* <对字符串进行Des加密，将字符串转化为字节数组解密>
     */
    public static String encode( String password,String datasource)

    {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());

            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

            SecretKey securekey = keyFactory.generateSecret(desKey);

            //Cipher对象实际完成加密操作

            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);

            //用密匙初始化Cipher对象

            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);

            //现在，获取数据并加密

            //正式执行加密操作
            byte[] bytes = cipher.doFinal(datasource.getBytes());
            return  Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Throwable e)
        {
            e.printStackTrace();
        }
        return null;
    }
}


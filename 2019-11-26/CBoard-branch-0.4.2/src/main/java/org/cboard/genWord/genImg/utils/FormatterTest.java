package org.cboard.genWord.genImg.utils;

import org.cboard.genWord.CoreEngine.entity.User;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by chenf on 2019/11/25.
 */
public class FormatterTest {

    public static String convertD2PlainString(double value,boolean isFormat,int n){

        String res=new BigDecimal(value).toPlainString();
        System.out.println(res);

        if(isFormat){
            StringBuilder sb=new StringBuilder();
            sb.append("#,###,###,###,###,###,###.");
            for (int i = 0; i < n; i++) {
                sb.append('0');
            }
            DecimalFormat df=new DecimalFormat(sb.toString());
            System.out.println(sb.toString());
            res=df.format(value);
        }


        return  res;
    }

    public static int convertD2Int(Object d){
        return (int)(double)d;
    }

    public static void main(String[] args) {
//        System.out.println((int)3219.0);
//        DecimalFormat df =new DecimalFormat("#,###,###,###,###,###.0000");
//        String str=df.format(3219.66);
//        System.out.println(str);

        System.out.println(convertD2PlainString(3.299123E9,true,4));
        double b=719.0;
        System.out.println((int)b);
        User user=new User();
        user.setPassword((int)b+"");
        System.out.println(user);
        System.out.println(convertD2Int(b));


    }

}

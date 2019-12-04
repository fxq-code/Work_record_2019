package org.cboard.genWord.genImg.utils;

import org.cboard.genWord.CoreEngine.entity.User;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.cboard.genWord.genImg.utils.Echarts2WordUtil.genQuarterStr;

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

//        System.out.println(convertD2PlainString(3.299123E9,true,4));
//        double b=719.0;
//        System.out.println((int)b);
//        User user=new User();
//        user.setPassword((int)b+"");
//        System.out.println(user);
//        System.out.println(convertD2Int(b));
//        Object object=2.33123E9;
//        double dobj=(double)object;

//        List list= new ArrayList();
//        int[] arr=new int[]{1, 2, 3, 4, 56, 71, -12};
//        for (int i = 0; i < arr.length; i++) {
//            list.add(arr[i]);
//           // System.out.print(list.get(i));
//        }
//        System.out.println(list);
//        System.out.println(Echarts2WordUtil.getRankFive(list,false));
//        System.out.println(Echarts2WordUtil.getRankFive(list,true));
//
//        System.out.println("abc".substring(1,3));

        //System.out.println(genQuarterStr("2018","18"));
        System.out.println(genQuarterStr("2018","68"));
        System.out.println(genQuarterStr("2018","612"));
        System.out.println(genQuarterStr("2018","7"));
//        System.out.println(genQuarterStr("2018","5"));
//        System.out.println(genQuarterStr("2018","4"));
        System.out.println(genQuarterStr("2018","3"));
        System.out.println(genQuarterStr("2018","2"));
        System.out.println(genQuarterStr("2018","1"));
        System.out.println(genQuarterStr("2018","0"));

    }




}

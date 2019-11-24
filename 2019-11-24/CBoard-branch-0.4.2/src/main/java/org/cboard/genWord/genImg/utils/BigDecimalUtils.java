package org.cboard.genWord.genImg.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by chenf on 2019/11/22.
 */
public class BigDecimalUtils {
    //将科学计数法的字符串转换成为 原来的数字，并以字符串的形式返回
    public static String convertPlainString(Object object){
        return  new BigDecimal(object.toString()).toPlainString();
    }

    /**
     *
     * 这个方法展示的是 在绘图的过程中度量衡的统一会让生成的图片比较好看
     k=10的3次方 千
     m=10的6次方 百万
     b=10的9次方 十亿
     t=10的12次方 万亿
     * */
    public static String changeMeasure(Object object,String mesure){
        double value=Double.parseDouble(BigDecimalUtils.convertPlainString(object));
        if(!mesure.isEmpty()){
            if(mesure.trim().equals("m")){
                value=value/1000000L;
                return  new DecimalFormat("#.00").format(value);
            }else if(mesure.trim().equals("k")){
                value=value/1000L;
                return  new DecimalFormat("#.00").format(value);
            }else if(mesure.trim().equals("b")){
                value=value/1000000000L;
                return  new DecimalFormat("#.00").format(value);
            }else if(mesure.trim().equals("t")){
                value=value/1000000000000L;
                return  new DecimalFormat("#.00").format(value);
            }else if(mesure.trim().equals("")){
                return (String) object;
            }

        }
        return  (String)object;
    }


//    public static void main(String[] args) {
//        System.out.println(
//                BigDecimalUtils.changeMeasure(
//                        "2.849504593571002E8","m"));
//
//        System.out.println(
//                BigDecimalUtils.changeMeasure(
//                        "2.849504593571002E+8","m"));
//        System.out.println(BigDecimalUtils.convertPlainString(2.849504593571002E+8));
//        System.out.println(BigDecimalUtils.convertPlainString(2.849504593571002E8));
//        System.out.println(BigDecimalUtils.convertPlainString(2.849504593571002E9));
//        System.out.println(BigDecimalUtils.convertPlainString(2.849504593571001E9));
//        System.out.println(BigDecimalUtils.convertPlainString(6.73927E7));
//        System.out.println(BigDecimalUtils.convertPlainString(6.73927E+9));
//        DecimalFormat df=new DecimalFormat("0.00000000");
//        String f1=df.format(6.73927E7);
//        System.out.println(f1);
//
//    }

}

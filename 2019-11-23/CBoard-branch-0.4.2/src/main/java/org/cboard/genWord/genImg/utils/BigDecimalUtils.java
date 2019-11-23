package org.cboard.genWord.genImg.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by chenf on 2019/11/22.
 */
public class BigDecimalUtils {
    public static String convertPlainString(Object object){

        return  new BigDecimal(object.toString()).toPlainString();
    }

    public static String changeMeasure(Object object,String mesure){

        if(!mesure.isEmpty()){
            if(mesure.trim().equals("m")){
                double value=Double.parseDouble(BigDecimalUtils.convertPlainString(object));
                System.out.println("value="+value);
                value=value/1000000;

                return  new DecimalFormat("#.00").format(value);
            }

        }
        return  (String )object;
    }


    public static void main(String[] args) {
        System.out.println(
                BigDecimalUtils.changeMeasure(
                        "2.849504593571002E8","m"));

        System.out.println(
                BigDecimalUtils.changeMeasure(
                        "2.849504593571002E+8","m"));
        System.out.println(BigDecimalUtils.convertPlainString(2.849504593571002E+8));
        System.out.println(BigDecimalUtils.convertPlainString(2.849504593571002E8));
        System.out.println(BigDecimalUtils.convertPlainString(2.849504593571002E9));
        System.out.println(BigDecimalUtils.convertPlainString(2.849504593571001E9));
        System.out.println(BigDecimalUtils.convertPlainString(6.73927E7));
        System.out.println(BigDecimalUtils.convertPlainString(6.73927E+9));
        DecimalFormat df=new DecimalFormat("0.00000000");
        String f1=df.format(6.73927E7);
        System.out.println(f1);

    }

}

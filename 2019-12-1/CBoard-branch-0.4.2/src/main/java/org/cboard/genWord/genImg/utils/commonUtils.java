package org.cboard.genWord.genImg.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by chenf on 2019/11/29.
 */
public class commonUtils {


    public static Long[] genIntArr(Object[]Values){

        //将浮点数进行相应的处理
        Long []tempArr=new Long[Values.length];
        for (int i = 0; i < tempArr.length; i++) {
            if((Values[i]+"").contains("E")){
                //说明是科学计数法，需要转换
                DecimalFormat df=new DecimalFormat("#,###,###,###,###,###,###,###,###.0000");
                String tempStr=df.format(new BigDecimal(Values[i]+""));
                //System.out.println(Values[i]);
                System.out.println(Long.parseLong(tempStr.split("\\.")[0].replaceAll(",","")));
                tempArr[i]=Long.parseLong(tempStr.split("\\.")[0].replaceAll(",",""));
               // tempArr[i]=Long.parseLong(tempStr.split("\\.")[0].replaceAll(",",""));

            }else{
                tempArr[i]=Long.parseLong((Values[i]+"").split("\\.")[0]);
            }

        }
        return  tempArr;

    }

    public static Long[] genScalaArr(Long []Values){
        //针对 单个数据量比较大的情况
        Long MaxValue=Long.parseLong(commonUtils.getMax(Values).split("@")[0]);
        Long MaxValueIndex=Long.parseLong(commonUtils.getMax(Values).split("@")[1]);
       // System.out.println("MaxValue "+MaxValue+" MaxValueIndex="+MaxValueIndex);
        int scalaLength=1;
        if(MaxValueIndex!=0){
            scalaLength=((MaxValue/Values[0])+"").length();
        }else{
            scalaLength=((MaxValue/Values[Values.length-1])+"").length();
        }
        //System.out.println("scalaLength="+scalaLength);

        Long mutiplyRate=10L;
        if(scalaLength!=1){
            for (int i = 0; i < scalaLength-1; i++) {
                mutiplyRate*=10;
            }
        }
        Long []errArr=new Long[Values.length+2];
        for (int i = 0; i < Values.length; i++) {
            if(i!=MaxValueIndex){
                errArr[i]=mutiplyRate*Values[i];
               // System.out.println(" errArr="+i+" "+errArr[i]);
            }else{
                errArr[i]=MaxValue;
               // System.out.println(" errArr="+i+" "+errArr[i]);
            }

        }
        errArr[errArr.length-2]=MaxValueIndex;
        errArr[errArr.length-1]=mutiplyRate;
        return  errArr;
    }

    public  static String getMax(Long[]arr){
        long max = 0;
        long maxIndex = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        //System.out.println(max + "----" + maxIndex);
        return  max+"@"+maxIndex;

    }

//    public static void main(String[] args) {
//        System.out.println(Long.parseLong("3455678900000"));
//    }

}

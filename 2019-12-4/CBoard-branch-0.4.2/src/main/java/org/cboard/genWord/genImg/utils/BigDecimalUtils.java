package org.cboard.genWord.genImg.utils;

import com.github.abel533.echarts.data.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;

/**
 * Created by chenf on 2019/11/22.
 */
public class BigDecimalUtils {


    public static String getRateFromNum(double d){
        StringBuilder sb=new StringBuilder();

        sb.append(d*100+"");
        sb.append("%");
        return  sb.toString();
    }


    public static String genFormatterStr2(Object[]originArr,Object[] distArr,int i){
        String formatter2="";
        if(!(((Data)originArr[i]).getValue()+"").toUpperCase().contains("E")){

           // System.out.println(((Data)distArr[i]).getValue()+" "+(new BigDecimal(((Data)distArr[i]).getValue()+"").compareTo(new BigDecimal(100))>0));

            if(new BigDecimal(((Data)distArr[i]).getValue()+"").compareTo(new BigDecimal(100))>0){

                int len=0;
                if(((Data)distArr[i]).getValue() instanceof Double){
                    System.out.println("double ...");
                    len=(((Data)distArr[i]).getValue()+"").split("\\.")[0].length()-2;
                    System.out.println("value="+((Data)distArr[i]).getValue()+",len="+len);

                    if(len<=3){
                        ((Data)distArr[i]).setValue(Double.parseDouble((((Data)distArr[i]).getValue()+""))/getDivideNum(len));
                        // System.out.println("new value ="+((Data)temp[i]).getValue()+"");
                        formatter2="function (value) {" +
                                "switch (value) { " +
                                "case 0: " +
                                "return 0;" +//首位刻度
                                "case 50: " +
                                "return 50*"+getDivideNum(len)+";" +//首位刻度
                                "case 100: " +
                                "return 100*"+getDivideNum(len)+";" +//首位刻度

                                "default: return ''; " +
                                "} " +
                                "}";
                    }else{
                        System.out.println((((Data)distArr[i]).getValue()+"----double----"));
                        System.out.println(new BigDecimal((((Data)distArr[i]).getValue()+"")).divide(new BigDecimal(getDivideNum(len))));
                        //如果数字有点大，则变通一波
                        ((Data)distArr[i]).setValue(Double.parseDouble((((Data)distArr[i]).getValue()+""))/getDivideNum(len));
                        formatter2="function (value) {" +
                                "switch (value) { " +
                                "case 0: " +
                                "return 0;" +//首位刻度
                                "case 50: " +
                                "return 50+'E"+len+"';" +//首位刻度
                                "case 100: " +
                                "return 100+'E"+len+"';" +//首位刻度

                                "default: return ''; " +
                                "} " +
                                "}";

                    }


                }else {
                    System.out.println("Long .....");
                    //说明需要展示的数据项的值是大于100的，这个时候就需要转换了
                     len=(((Data)distArr[i]).getValue()+"").length()-2;
                    //System.out.println("value="+((Data)distArr[i]).getValue()+",len="+len);

                    if(len<=3){
                        ((Data)distArr[i]).setValue(Long.parseLong((((Data)distArr[i]).getValue()+""))/getDivideNum(len));
                        // System.out.println("new value ="+((Data)temp[i]).getValue()+"");
                        formatter2="function (value) {" +
                                "switch (value) { " +
                                "case 0: " +
                                "return 0;" +//首位刻度
                                "case 50: " +
                                "return 50*"+getDivideNum(len)+";" +//首位刻度
                                "case 100: " +
                                "return 100*"+getDivideNum(len)+";" +//首位刻度

                                "default: return ''; " +
                                "} " +
                                "}";
                    }else{
                       // System.out.println((((Data)distArr[i]).getValue()+"--------"));
                        //System.out.println(new BigDecimal((((Data)distArr[i]).getValue()+"")).divide(new BigDecimal(getDivideNum(len))));
                        //如果数字有点大，则变通一波
                        ((Data)distArr[i]).setValue(Long.parseLong((((Data)distArr[i]).getValue()+""))/getDivideNum(len));
                        formatter2="function (value) {" +
                                "switch (value) { " +
                                "case 0: " +
                                "return 0;" +//首位刻度
                                "case 50: " +
                                "return 50+'E"+len+"';" +//首位刻度
                                "case 100: " +
                                "return 100+'E"+len+"';" +//首位刻度

                                "default: return ''; " +
                                "} " +
                                "}";

                    }
                }




            }else{
                //System.out.println("........normal");

                //如果没有超过100,那么就正常显示
                formatter2="function (value) {" +
                        "switch (value) { " +
                        "case 0: " +
                        "return 0;" +//首位刻度
                        "case 50: " +
                        "return 50;" +//首位刻度
                        "case 100: " +
                        "return 1000;" +//首位刻度
                        "default: return ''; " +
                        "} " +
                        "}";
            }

        }else{
           // System.out.println(((Data)distArr[i]).getValue() +"E.....");

            int len=0;

                len= new BigDecimal(((Data)distArr[i]).getValue()+"").toPlainString().split("\\.")[0].length()-2;
               // System.out.println("E"+len);
                ((Data)distArr[i]).setValue(new BigDecimal((((Data)distArr[i]).getValue()+"")).divide(new BigDecimal(getDivideNum(len))));


                System.out.println(((Data)distArr[i]).getValue()+"=======");
                formatter2="function (value) {" +
                        "switch (value) { " +
                        "case 0: " +
                        "return 0;" +//首位刻度
                        "case 50: " +
                        "return 50+'E"+len+"';" +//首位刻度
                        "case 100: " +
                        "return 100+'E"+len+"';" +//首位刻度

                        "default: return ''; " +
                        "} " +
                        "}";




        }

        return  formatter2;

    }



    public static String genFormatterStr(Object[]originArr,Object[] distArr,int i){
        String formatter2="";
        if(!(((Data)originArr[i]).getValue()+"").toUpperCase().contains("E")){

            if(new BigDecimal(((Data)distArr[i]).getValue()+"").compareTo(new BigDecimal(100))>0){
                //说明需要展示的数据项的值是大于100的，这个时候就需要转换了
                int len=(((Data)distArr[i]).getValue()+"").length()-2;

                if(len<=3){
                    // System.out.println("len1=="+len);
                    ((Data)distArr[i]).setValue(Long.parseLong((((Data)distArr[i]).getValue()+""))/getDivideNum(len));
                    // System.out.println("new value ="+((Data)temp[i]).getValue()+"");
                    formatter2="function (value) {" +
                            "switch (value) { " +
                            "case 0: " +
                            "return 0;" +//首位刻度

                            "default: return value*"+getDivideNum(len)+"; " +
                            "} " +
                            "}";
                }else{
                    //如果数字有点大，则变通一波
                    ((Data)distArr[i]).setValue(Long.parseLong((((Data)distArr[i]).getValue()+""))/getDivideNum(len));
                    formatter2="function (value) {" +
                            "switch (value) { " +
                            "case 0: " +
                            "return 0;" +//首位刻度

                            "default: return value+'E"+len+"'; " +
                            "} " +
                            "}";

                }


            }else{


                //如果没有超过100,那么就正常显示
                formatter2="function (value) {" +
                        "switch (value) { " +
                        "case 0: " +
                        "return 0;" +//首位刻度
                        "default: return value; " +
                        "} " +
                        "}";
            }

        }else{
            int len= new BigDecimal(((Data)distArr[i]).getValue()+"").toPlainString().length()-2;
            //System.out.println("E"+len);
            ((Data)distArr[i]).setValue(Long.parseLong((((Data)distArr[i]).getValue()+""))/getDivideNum(len));
            formatter2="function (value) {" +
                    "switch (value) { " +
                    "case 0: " +
                    "return 0;" +//首位刻度

                    "default: return value+'E"+len+"'; " +
                    "} " +
                    "}";
        }

        return  formatter2;

    }


    //根据相应的 位数获得需要操作的相关的10*位数 的数字
    public static Long getDivideNum(int len){
        StringBuilder sb=new StringBuilder();
        sb.append("1");
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }

        return Long.parseLong(sb.toString());

    }

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

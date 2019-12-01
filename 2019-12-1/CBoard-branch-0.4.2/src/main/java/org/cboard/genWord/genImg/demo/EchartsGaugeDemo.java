package org.cboard.genWord.genImg.demo;



import com.github.abel533.echarts.Legend;

import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Gauge;

import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.series.gauge.Detail;
import com.github.abel533.echarts.series.gauge.Pointer;

import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;

import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static org.cboard.genWord.genImg.utils.BigDecimalUtils.genFormatterStr;


/**
 * Created by chenf on 2019/12/1.
 */
public class EchartsGaugeDemo {

    private final Logger logger= (Logger) LoggerFactory.getLogger(EchartsGaugeDemo.class);



    /**
     *
     * 查看生成的仪表盘的 相关操作
     * 加入了对于较大数值的 转换科学计数法的相关操作
     * 当填充的数值有点大的情况时，使用这种方式去将相应的 大数转换成为 科学计数法，便于显示
     * */
    public static String genGaugeConmen(String titile,String subtext,String formatter,Object[]gaugeDatas,Object[] Values){

        GsonOption option = new GsonOption();
        option.title()
                //.text(titile)
                .subtext(subtext)
                .x(X.center)
                .y(Y.top)
                .itemGap(20)
                .textStyle().color("rgba(30,144,255,0.8)")
                .fontFamily("微软雅黑")
                .fontSize(25)
                .fontWeight("bolder");
        //option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.tooltip().show(true).formatter(formatter);
        Legend legend=option.legend().orient(Orient.vertical).x(X.left);



        // ProxyInvokerHandlerUtil.processPiDataInvoker(legend,gaugeDatas);
        Object[] temp=new Object[Values.length];
        for (int i = 0; i <temp.length ; i++) {
            if((((Data)Values[i]).getValue()+"").toUpperCase().contains("E")){
                //说明 数据里面有相应的科学计数法
                BigDecimal b=new BigDecimal(((Data)Values[i]).getValue()+"");

                temp[i]=new Data("",b.toPlainString());
                System.out.println("temp i="+i+" v="+b.toPlainString());
            }else{
                temp[i]=new Data("",((Data)Values[i]).getValue());
                System.out.println("temp i="+i+" v="+((Data)Values[i]).getValue());
            }



        }

        for (int i = 0; i < Values.length; i++) {
//相应的formatter
            String formatter1=((Data)Values[i]).getName()+":"+new BigDecimal(((Data)Values[i]).getValue()+"").toPlainString();

            Detail dataStyle=new Detail();
            dataStyle.textStyle().fontSize(30);

            dataStyle.formatter(formatter1);

            Gauge g1=new Gauge();
            g1.startAngle(180);
            g1.endAngle(0);
            g1.radius(100+i*80);

            //根据传入的参数值 的大小 动态修改相应的仪表盘的 单位大小,需要将相应的索引值 传入，针对 每一个仪表盘单独进行修改
            String formatter2=genFormatterStr(Values,temp,i);

           // System.out.println("old value ="+((Data)temp[i]).getValue()+"");

            g1.axisLine().lineStyle().width(20);
            g1.axisLabel().formatter(formatter2);
            g1.splitLine().lineStyle().width(0);
            g1.pointer(new Pointer().width(1));
            Series series1=g1.detail(dataStyle);
            ProxyInvokerHandlerUtil.processDataInvoker(series1,new Object[]{temp[i]});
            option.series(g1);
        }






        return option.toString();



    }

    /**
     *
     * 查看生成的仪表盘的 相关操作
     * */
    public static String genGaugeConmen2(String titile,String subtext,String formatter,Object[]gaugeDatas,Object[] Values){

        GsonOption option = new GsonOption();
        option.title()
                //.text(titile)
                .subtext(subtext)
                .x(X.center)
                .y(Y.top)
                .itemGap(20)
                .textStyle().color("rgba(30,144,255,0.8)")
                .fontFamily("微软雅黑")
                .fontSize(25)
                .fontWeight("bolder");
        //option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.tooltip().show(true).formatter(formatter);
        Legend legend=option.legend().orient(Orient.vertical).x(X.left);




       // ProxyInvokerHandlerUtil.processPiDataInvoker(legend,gaugeDatas);
        Object[] temp=new Object[Values.length];
        for (int i = 0; i <temp.length ; i++) {
            if((((Data)Values[i]).getValue()+"").toUpperCase().contains("E")){
                //说明 数据里面有相应的科学计数法
                BigDecimal b=new BigDecimal(((Data)Values[i]).getValue()+"");

                temp[i]=new Data("",b.toPlainString());
                System.out.println("temp i="+i+" v="+b.toPlainString());
            }else{
                temp[i]=new Data("",((Data)Values[i]).getValue());
                System.out.println("temp i="+i+" v="+((Data)Values[i]).getValue());
            }



        }

        for (int i = 0; i < Values.length; i++) {
//相应的formatter
            String formatter1=((Data)Values[i]).getName()+":{value}";

            Detail dataStyle=new Detail();
            dataStyle.textStyle().fontSize(30);

            dataStyle.formatter(formatter1);

            Gauge g1=new Gauge();
            g1.startAngle(180);
            g1.endAngle(0);
            g1.radius(100+i*80);

            String formatter2="function (value) {" +

                    "switch (value) { " +
                    "case 0: " +
                    "return 0;" +//首位刻度
                    "case 100: " +
                    "return 200;" +//末尾刻度
                    "default: return value.toFixed(2); " +
                    "} " +
                    "}";

            g1.axisLine().lineStyle().width(20);
           // g1.axisLabel().formatter(formatter2);
            g1.splitLine().lineStyle().width(0);
            g1.pointer(new Pointer().width(1));
            Series series1=g1.detail(dataStyle);
            ProxyInvokerHandlerUtil.processDataInvoker(series1,new Object[]{temp[i]});
            option.series(g1);
        }






        return option.toString();



    }




}

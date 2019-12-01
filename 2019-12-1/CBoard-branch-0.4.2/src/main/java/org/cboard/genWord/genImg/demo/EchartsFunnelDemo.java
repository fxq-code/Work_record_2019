package org.cboard.genWord.genImg.demo;

import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.TextStyle;
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.cboard.genWord.genImg.utils.Echarts2WordUtil.isIntergerOrDouble;

/**
 * Created by chenf on 2019/11/20.
 */
public class EchartsFunnelDemo {

    private static final Logger logger=Logger.getLogger(String.valueOf(EchartsCyclicDemo.class));


    public static String genFunnelCommon(String title,String subtext,String formatter,Object[] piDatas,Object[] Valuedatas){

        GsonOption option=new GsonOption();

        option.title().text(title)
                .subtext(subtext)
                .x(X.center)
                .y(Y.top)
                //.itemGap(20)
                .textStyle().color("rgba(30,144,255,0.8)")
                .fontFamily("微软雅黑")
                .fontSize(35)
                .fontWeight("bolder");
        //option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.color("#37A2DA", "#32C5E9", "#67E0E3", "#9FE6B8","#FFDB5C");
        option.tooltip().show(true).formatter(formatter);
        Legend legend=option.legend()
                .orient(Orient.vertical).x(X.left);

        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);



        ItemStyle dataStyle = new ItemStyle();
       // dataStyle.normal().label(new Label().show(true)).labelLine().show(true);
        dataStyle.normal().opacity(0.8).borderWidth(2).setBorderColor("#fff");
                //.borderColor("#fff")
                //.formatter("{b}:{c}%").position(Position.inside);
       // dataStyle.emphasis().formatter("{b}实际: {c}%").position(Position.inside);


        String formatter1="";
        //第一种方式可以取得到 对应的值
        Object[] values=new Object[Valuedatas.length];
        for (int i = 0; i < Valuedatas.length; i++) {
            values[i]=((Data)Valuedatas[i]).getValue();
            System.out.println("values "+i+"="+values[i]);
        }

        if(values.length>1){
            String res=isIntergerOrDouble(values[0]);
            if(res.equals("FALSE")){
                throw new RuntimeException("您输入的数据项的value属性并不是数字，请检查输入！");
            }else{
                   Arrays.sort(values);
                   System.out.println("max values "+values[values.length-1]);
                formatter1="function (params,ticket,callback) {" +
                        "  var res = params.dataIndex+' '+params.name+params.value+' '+ (params.value/"+values[values.length-1]+").toFixed(2)*100;" +
                        "  return res+'%'" +
                        "          }";
            }

        }else{
            throw new RuntimeException("绘制数据项必须要有两个以上，请检查输入！");
        }




        ItemStyle labelStyle=new ItemStyle();

        labelStyle.normal()//
                //.formatter("{a} {b}: {c}  {d}%")
                .formatter(formatter1)
                .position(Position.inside).textStyle(new TextStyle().color("#777"));

        Funnel f2=new Funnel(title);
        f2.itemStyle(dataStyle);
        f2.label(labelStyle);



        ProxyInvokerHandlerUtil.processDataInvoker(f2,Valuedatas);

        option.series(f2);

        return  option.toString();


    }

    public static String genFunnelCommon2(String title,String subtext,String formatter,Object[] piDatas){

        GsonOption option=new GsonOption();

        option.title().text(title)
                .subtext(subtext)
                .x(X.center)
                .y(Y.top)
                //.itemGap(20)
                .textStyle().color("rgba(30,144,255,0.8)")
                .fontFamily("微软雅黑")
                .fontSize(35)
                .fontWeight("bolder");
        //option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.tooltip().show(true).formatter(formatter);
        Legend legend=option.legend()
                .orient(Orient.vertical).x(X.left);

        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);

        //重要的数据项
        ItemStyle dataStyle = new ItemStyle();
        dataStyle.normal().opacity(0.7).formatter("{b}预期");
        dataStyle.normal().label(new Label().show(true)).labelLine().show(true);
        dataStyle.emphasis().position(Position.inside).formatter("{b}预期: {c}%");

        ItemStyle dataStyle2 = new ItemStyle();
        dataStyle2.normal().label(new Label().show(true)).labelLine().show(false);
        dataStyle2.normal().opacity(0.5).borderWidth(2).borderColor("#fff").formatter("{c}%").position(Position.inside);
       // dataStyle2.normal().color("rgba(0,0,0,0)").label(new Label().show(false)).labelLine().show(false);
       // dataStyle2.emphasis().color("rgba(0,0,0,0)");
        dataStyle2.emphasis().formatter("{b}实际: {c}%").position(Position.inside);


        Funnel f1=new Funnel("预期");
        f1.itemStyle(dataStyle);


        Object[] objects1={
                new Data("访问", 60),
                new Data("咨询", 40),
                new Data("订单", 20),
                new Data("点击", 80),
                new Data("展现", 100)
        };

        ProxyInvokerHandlerUtil.processDataInvoker(f1,objects1);

        Funnel f2=new Funnel("实际");
        f2.itemStyle(dataStyle2);


        Object[] objects2={
                new Data("访问", 30),
                new Data("咨询", 10),
                new Data("订单", 5),
                new Data("点击", 50),
                new Data("展现", 80)
        };

        ProxyInvokerHandlerUtil.processDataInvoker(f2,objects2);

        option.series(f1,f2);

        return  option.toString();


    }


}

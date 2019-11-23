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
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;

import java.util.logging.Logger;

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
        option.tooltip().show(true).formatter(formatter);
        Legend legend=option.legend()
                .orient(Orient.vertical).x(X.left);

        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);



        ItemStyle dataStyle = new ItemStyle();
        dataStyle.normal().label(new Label().show(true)).labelLine().show(true);
        dataStyle.normal().opacity(0.8).borderWidth(2)
                //.borderColor("#fff")
                .formatter("{c}%").position(Position.inside);
        dataStyle.emphasis().formatter("{b}实际: {c}%").position(Position.inside);


        Funnel f2=new Funnel(title);
        f2.itemStyle(dataStyle);


//        Object[] objects2={
//                new Data("访问", 30),
//                new Data("咨询", 10),
//                new Data("订单", 5),
//                new Data("点击", 50),
//                new Data("展现", 80)
//        };

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

package org.cboard.genWord.genImg.demo;


import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.TextStyle;
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;

import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by chenf on 2019/11/20.
 */
public class EchartsCyclicDemo {

    private static final Logger logger=Logger.getLogger(String.valueOf(EchartsCyclicDemo.class));

    //内外环嵌套的数据结构
    public static String genCyclic2Common(String title,String subtext,String formatter,Object[] piDatas,Object[] Valuedatas,Object[] innerValueDatas){

        GsonOption option=new GsonOption();

        option.title()
                .subtext(subtext)
                .x(X.center)
                .y(Y.top)
                //.itemGap(20)
                .textStyle()
                .color("#181619")
                .fontFamily("微软雅黑")
                .fontSize(35)
                .fontWeight("bolder");
        //option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
        //option.color("#37A2DA", "#32C5E9", "#67E0E3", "#9FE6B8","#FFDB5C");
        option.tooltip().show(true).formatter(formatter);
        Legend legend=option.legend()
                .orient(Orient.vertical).x(X.left);

        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);

        //内圆中的数据项
        ItemStyle dataStyle1 = new ItemStyle();
        dataStyle1.normal().formatter("{b}:{c} \n {d}%").position(Position.center).color("#fff");

        ItemStyle dataStyle12=new ItemStyle();
        dataStyle12.normal().color("#c23531").shadowBlur(200).shadowColor("rgba(0, 0, 0, 0.5)").setShow(true);

        Pie p1 = new Pie(title);
        Series series1=p1.radius(0, 100).label(dataStyle1);
                //.itemStyle(dataStyle12);


        ProxyInvokerHandlerUtil.processDataInvoker(series1,innerValueDatas);


        //外环圆中重要的数据项
        ItemStyle dataStyle = new ItemStyle();
        //dataStyle.normal().label(new Label().show(true)).labelLine().show(true);
        dataStyle.normal()
                //.formatter(" {b}:{c} {d}% ");
                .formatter(" {b}:{d}% ");
                //.borderWidth(1).borderRadius(4)
                //.borderColor("#aaa");

        Pie p2 = new Pie(title);
        Series series2=p2.radius(100, 150).label(dataStyle);


        ProxyInvokerHandlerUtil.processDataInvoker(series2,Valuedatas);

        option.series(p1,p2);

        return  option.toString();

    }
    public static String genCyclicCommon(String title,String subtext,String formatter,Object[] piDatas,Object[] Valuedatas){

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
        dataStyle.normal().formatter("{b}:{d}%");
//        dataStyle.normal().label(new Label().show(true)).labelLine().show(true);
//        dataStyle.normal().formatter("{a} <br/>{b} : {c} ({d}%)");
//        dataStyle.normal()//
//                .formatter("{a} {b}: {c}  {d}%")
//                //.formatter(formatter1)
//                .position(Position.inside).textStyle(new TextStyle().color("#777"));


        Pie p2 = new Pie(title);
        Series series2=p2.clockWise(false).radius(100, 150).label(dataStyle);
//        Object[] objects2={
//                new Data("直达", 335),
//                new Data("邮件营销", 310),
//                new Data("联盟广告", 234),
//                new Data("视频广告", 135),
//                new Data("百度", 1048),
//                new Data("谷歌", 251),
//                new Data("必应", 147),
//                new Data("其他", 102)
//        };

        ProxyInvokerHandlerUtil.processDataInvoker(series2,Valuedatas);

        option.series(p2);

        return  option.toString();
    }

    public static String genCyclicCommon2(String title,String subtext,String formatter,Object[] piDatas){

        GsonOption option=new GsonOption();

        option.title().text(title)
                .subtext(subtext)
                .x(X.center)
                .y(Y.top)
                .itemGap(20)
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

        dataStyle.normal().label(new Label().show(true)).labelLine().show(true);

        ItemStyle placeHolderStyle = new ItemStyle();
        placeHolderStyle.normal().color("rgba(0,0,0,0)").label(new Label().show(false)).labelLine().show(false);
        placeHolderStyle.emphasis().color("rgba(0,0,0,0)");

//        Pie p1 = new Pie("访问来源");
//
//        Series series1=p1.clockWise(false).radius(125, 150).itemStyle(dataStyle);
//        Object[] objects1={new Data("68%的人表示过的不错", 68),new Data("invisible", 32).itemStyle(placeHolderStyle)};
//
//        //series1.data(new Data("68%的人表示过的不错", 68),new Data("invisible", 32).itemStyle(placeHolderStyle));
//        ProxyInvokerHandlerUtil.processDataInvoker(series1,objects1);

        Pie p2 = new Pie("访问来源");
        Series series2=p2.clockWise(false).radius(100, 150).itemStyle(dataStyle);
        Object[] objects2={
                new Data("直达", 335),
                new Data("邮件营销", 310),
                new Data("联盟广告", 234),
                new Data("视频广告", 135),
                new Data("百度", 1048),
                new Data("谷歌", 251),
                new Data("必应", 147),
                new Data("其他", 102)
                //new Data("invisible", 71).itemStyle(placeHolderStyle)

                };

        ProxyInvokerHandlerUtil.processDataInvoker(series2,objects2);

        option.series(p2);

        return  option.toString();
    }


}

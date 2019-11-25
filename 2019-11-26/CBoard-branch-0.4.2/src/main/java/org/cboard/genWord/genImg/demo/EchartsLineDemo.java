package org.cboard.genWord.genImg.demo;


import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.DataView;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.feature.Restore;
import com.github.abel533.echarts.feature.SaveAsImage;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;

import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class EchartsLineDemo {

    private static final Logger logger = Logger.getLogger(String.valueOf(EchartsLineDemo.class));

    public static String getPieOption() {
        ItemStyle dataStyle = new ItemStyle();
        dataStyle.normal().label(new Label().show(false)).labelLine().show(false);

        ItemStyle placeHolderStyle = new ItemStyle();
        placeHolderStyle.normal().color("rgba(0,0,0,0)").label(new Label().show(false)).labelLine().show(false);
        placeHolderStyle.emphasis().color("rgba(0,0,0,0)");

        GsonOption option = new GsonOption();
        option.title().text("你幸福吗？")
                .subtext("From ExcelHome")
                //.sublink("http://e.weibo.com/1341556070/AhQXtjbqh")
                .x(X.center)
                .y(Y.center)
                .itemGap(20)
                .textStyle().color("rgba(30,144,255,0.8)")
                .fontFamily("微软雅黑")
                .fontSize(36)
                .fontWeight("bolder");
        option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.legend().orient(Orient.vertical)
               // .x("(function(){return document.getElementById('main').offsetWidth / 2;})()")
                .y(56)
                .itemGap(12)
                .data("68%的人表示过的不错", "29%的人表示生活压力很大", "3%的人表示“我姓曾”");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage);

        Pie p1 = new Pie("1");
        p1.clockWise(false).radius(125, 150).itemStyle(dataStyle)
                .data(new Data("68%的人表示过的不错", 68),new Data("invisible", 32).itemStyle(placeHolderStyle));

        Pie p2 = new Pie("2");
        p2.clockWise(false).radius(100, 125).itemStyle(dataStyle)
                .data(new Data("29%的人表示生活压力很大", 29),new Data("invisible", 71).itemStyle(placeHolderStyle));

        Pie p3 = new Pie("3");
        p3.clockWise(false).radius(75, 100).itemStyle(dataStyle)
                .data(new Data("3%的人表示“我姓曾”", 3),new Data("invisible", 97).itemStyle(placeHolderStyle));

        option.series(p1, p2, p3);

        return option.toString();

    }


    public static String getLineOption() {


        GsonOption option = new GsonOption();
        option.legend("高度(km)与气温(°C)变化关系");

        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);

        option.calculable(true);
        option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}°C");

        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter("{value} °C");
        option.xAxis(valueAxis);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.axisLine().onZero(false);
        categoryAxis.axisLabel().formatter("{value} km");
        categoryAxis.boundaryGap(false);
        categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
        option.yAxis(categoryAxis);

        Line line = new Line();
        line.smooth(true).name("高度(km)与气温(°C)变化关系").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
        option.series(line);

        return option.toString();
    }

    public static String getCurveLineOption(String legend,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter,String smoothName) {
        //数据项一定要匹配
        //如果数据类型不是object[]，那么整个那一列都会为空
//        Object[] xdatas=new Object[]{"老年人", "儿童", "妇女", "高血压", "糖尿病", "精神病", "冠心病"};
//
//        Object[] xdatas1=new Object[]{10, 20, 30, 40, 50, 60, 70};
//        Object[] ydatas=new Object[]{0,6,1,7,6,4,4};


        GsonOption option = new GsonOption();
       // option.legend("高度(km)与气温(°C)变化关系");
        Toolbox toolbox = new Toolbox().show(true);    //创建工具栏
        toolbox.feature(new DataView().show(true).readOnly(false),new MagicType().show(true).type(new String[]{"line","bar"}), new Restore().show(true),new SaveAsImage().show(true));
        option.toolbox(toolbox);

        option.legend(legend);

        //option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);

//        option.toolbox().show(true).feature(Tool.mark, Tool.dataView);
//        option.calculable(true);
        option.tooltip().trigger(Trigger.axis);

        ValueAxis valueAxis = new ValueAxis();
        //valueAxis.axisLabel().formatter("{value} °C");

        valueAxis.axisLabel().formatter(yLabelFormatter);
        option.yAxis(valueAxis);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.axisLine().onZero(false);

        //categoryAxis.axisLabel().formatter("{value} km");
        categoryAxis.axisLabel().formatter(xLabelFormatter);
        categoryAxis.boundaryGap(false);
         ProxyInvokerHandlerUtil.processDataInvoker(categoryAxis,xdatas);
       // categoryAxis.data(10, 20, 30, 40, 50, 60, 70);
        option.xAxis(categoryAxis);

        Line line = new Line();
        line.smooth(true).name(smoothName);
        //line.smooth(true).name("高度(km)与气温(°C)变化关系");
       // line.data(0,6,1,7,6,4,4);
         ProxyInvokerHandlerUtil.processDataInvoker(line,ydatas);
        option.series(line);

        return  option.toString();
    }




}

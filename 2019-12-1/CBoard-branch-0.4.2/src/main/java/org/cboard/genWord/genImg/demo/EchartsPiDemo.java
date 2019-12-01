package org.cboard.genWord.genImg.demo;

import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.*;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Gauge;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.TextStyle;
import org.cboard.genWord.genImg.utils.BigDecimalUtils;
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;
import org.cboard.genWord.genImg.utils.commonUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

import static org.cboard.genWord.genImg.utils.commonUtils.genIntArr;

public class EchartsPiDemo {

    private static final Logger logger = Logger.getLogger(String.valueOf(EchartsPiDemo.class));




    public static String genPiConmen(String titile,String subtext,String formatter,Object[]piDatas,Object[] Values){

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



        Long[]tempArr=genIntArr(Values);

        Long []errArr=commonUtils.genScalaArr(tempArr);
        Long maxValueIndex=errArr[errArr.length-2];
        Long scalaRate=errArr[errArr.length-1];
        System.out.println("scalaRate ="+scalaRate+" maxValueIndex="+maxValueIndex);


        String formatter1="function (params,ticket,callback) {" +
                "var index=params.dataIndex;" +
                "var res;" +
                "if(index=="+maxValueIndex+"){" +
                " res =params.name+':'+params.values ;" +
                "}else{" +
                " res =params.name+':'+params.values/"+scalaRate*10+";" +
                "}" +
                "  return res " +
                "  }";


//
//        String formatter2="function (params,ticket,callback) {" +
//                "var index=params.dataIndex;" +
//                "var res;" +
//                "if(index=='1'){" +
//                " res =params.name+':'+"+new BigDecimal(Values[0]+" ").toPlainString()+" ;" +
//                "}else if(index=='2'){" +
//                " res =params.name+':'+"+new BigDecimal(Values[1]+" ").toPlainString()+" ;" +
//                "}else if(index=='3'){" +
//                " res =params.name+':'+"+new BigDecimal(Values[2]+" ").toPlainString()+" ;" +
//                "}" +
//                "  return res " +
//                "  }";
        ItemStyle dataStyle=new ItemStyle();
        dataStyle.normal().formatter(formatter1)
                //.position(Position.inside)
                .textStyle(new TextStyle().color("#777"));

        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);


        Pie p1 = new Pie("1");
        Series series1=p1.radius(0, 110).label(dataStyle);
        Object[] objects2=new Object[piDatas.length];
        for (int i = 0; i < piDatas.length; i++) {

            objects2[i]=new Data(piDatas[i]+"","1");
           // System.out.println("objct2 i"+i+" "+objects2[i]);
        }
        series1.data(objects2);
       // ProxyInvokerHandlerUtil.processDataInvoker(series1,objects2);
//        ArrayList arrayList=new ArrayList();
//        arrayList.add(series2);
        option.series(p1);


        return option.toString();
                //.replaceAll("value","name").replaceAll("symbol","value").replaceAll("names","value");


    }



    public static String genPiConmen2(String titile,String subtext,String formatter,Object[]piDatas,ArrayList<Series> parts){


//        ItemStyle dataStyle = new ItemStyle();
//        dataStyle.normal().label(new Label().show(false)).labelLine().show(false);
//
//        ItemStyle placeHolderStyle = new ItemStyle();
//        placeHolderStyle.normal().color("rgba(0,0,0,0)").label(new Label().show(false)).labelLine().show(false);
//        placeHolderStyle.emphasis().color("rgba(0,0,0,0)");

        GsonOption option = new GsonOption();
        option.title().text(titile)
                .subtext(subtext)

                .x(X.center)
                .y(Y.center)
                .itemGap(20)
                .textStyle().color("rgba(30,144,255,0.8)")
                .fontFamily("微软雅黑")
                .fontSize(35)
                .fontWeight("bolder");
        //option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
        option.tooltip().show(true).formatter(formatter);
        Legend legend=option.legend().orient(Orient.vertical)
                // .x("(function(){return document.getElementById('main').offsetWidth / 2;})()")
                .y(56)
                .itemGap(12);

        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);

        //        .data("68%的人表示过的不错", "29%的人表示生活压力很大", "3%的人表示“我姓曾”");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage);

//        Pie p1 = new Pie("1");
//        Series series1=p1.clockWise(false).radius(125, 150).itemStyle(dataStyle);
//        Object[] objects1={new Data("68%的人表示过的不错", 68),new Data("invisible", 32).itemStyle(placeHolderStyle)};
//
//        //series1.data(new Data("68%的人表示过的不错", 68),new Data("invisible", 32).itemStyle(placeHolderStyle));
//        ProxyInvokerHandlerUtil.processDataInvoker(series1,objects1);
//
//        Pie p2 = new Pie("2");
//        Series series2=p2.clockWise(false).radius(100, 125).itemStyle(dataStyle);
//        Object[] objects2={new Data("29%的人表示生活压力很大", 29),new Data("invisible", 71).itemStyle(placeHolderStyle)};
//        //series2.data(new Data("29%的人表示生活压力很大", 29),new Data("invisible", 71).itemStyle(placeHolderStyle));
//        ProxyInvokerHandlerUtil.processDataInvoker(series2,objects2);
//
//        Pie p3 = new Pie("3");
//        Series series3=p3.clockWise(false).radius(75, 100).itemStyle(dataStyle);
//        Object[] objects3={new Data("3%的人表示“我姓曾”", 3),new Data("invisible", 97).itemStyle(placeHolderStyle)};
//        series3.data(new Data("3%的人表示“我姓曾”", 3),new Data("invisible", 97).itemStyle(placeHolderStyle));
//        ProxyInvokerHandlerUtil.processDataInvoker(series3,objects3);
//
//
        option.series( parts);
//        System.out.println(parts.length);
//        for (int i = 0; i < parts.length; i++) {
//            System.out.println(parts[i]);
//        }
        //ProxyInvokerHandlerUtil.processOptionDataInvoker(option,parts);

        return option.toString();


    }


//    public static  ArrayList<Series> genPiInfo(Object[] pidatas,int[] values) {
//        ItemStyle dataStyle = new ItemStyle();
//        dataStyle.normal().label(new Label().show(false)).labelLine().show(false);
//
//        ItemStyle placeHolderStyle = new ItemStyle();
//        placeHolderStyle.normal().color("rgba(0,0,0,0)").label(new Label().show(false)).labelLine().show(false);
//        placeHolderStyle.emphasis().color("rgba(0,0,0,0)");
//
//        //option.series(p1, p2, p3);
//        ArrayList<Series> seriesArrayList = new ArrayList<>();
//
//                Pie p1 = new Pie("1");
//        Series series1=p1.clockWise(false).radius(125, 150).itemStyle(dataStyle);
//        Object[] objects1={new Data("68%的人表示过的不错", 68),new Data("invisible", 32).itemStyle(placeHolderStyle)};
//
//        //series1.data(new Data("68%的人表示过的不错", 68),new Data("invisible", 32).itemStyle(placeHolderStyle));
//        ProxyInvokerHandlerUtil.processDataInvoker(series1,objects1);
//
//        Pie p2 = new Pie("2");
//        Series series2=p2.clockWise(false).radius(100, 125).itemStyle(dataStyle);
//        Object[] objects2={new Data("29%的人表示生活压力很大", 29),new Data("invisible", 71).itemStyle(placeHolderStyle)};
//        //series2.data(new Data("29%的人表示生活压力很大", 29),new Data("invisible", 71).itemStyle(placeHolderStyle));
//        ProxyInvokerHandlerUtil.processDataInvoker(series2,objects2);
//
//        Pie p3 = new Pie("3");
//        Series series3=p3.clockWise(false).radius(75, 100).itemStyle(dataStyle);
//        Object[] objects3={new Data("3%的人表示“我姓曾”", 3),new Data("invisible", 97).itemStyle(placeHolderStyle)};
//        //series3.data(new Data("3%的人表示“我姓曾”", 3),new Data("invisible", 97).itemStyle(placeHolderStyle));
//        ProxyInvokerHandlerUtil.processDataInvoker(series3,objects3);
//
//
//        seriesArrayList.add(p1);
//        seriesArrayList.add(p2);
//        seriesArrayList.add(p3);
//
//        return  seriesArrayList;
//    }
}

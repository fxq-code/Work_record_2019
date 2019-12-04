package org.cboard.genWord.genImg.demo;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.feature.Mark;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Series;
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class EchartsHistogramDemo {

    private static final Logger logger = Logger.getLogger(String.valueOf(EchartsHistogramDemo.class));


    /**
     * 模拟从数据库中取出相应的数据
     * */
    public static List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        for(int i = 0;i < 20;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("NAME",i);
            map.put("TOTAL",Math.round(Math.random()*100));
            list.add(map);
        }
        return list;
    }



    /**
     *
     * 多标记的柱状图 以及面积图
     * */
    public static String genMutiHistogram2AreaCommon(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter,Object[]piDatas,Object[]ValuesData){


        GsonOption option=new GsonOption();


        Legend legend=option.legend()
                .orient(Orient.horizontal).x(X.right);
        //相应的标记值
        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);



        option.title(titile).tooltip().trigger(Trigger.axis);//设置标题
        CategoryAxis categoryAxis = new CategoryAxis();
        ProxyInvokerHandlerUtil.processDataInvoker(categoryAxis,xdatas);

        List<Series> bars=new ArrayList<>();
        //创建 面积图多个
        for (int i = 0; i < ydatas.length-1; i++) {
            Bar bar = new Bar();
            ProxyInvokerHandlerUtil.processDataInvoker(bar,(Object[]) ydatas[i]);
            bar.name(piDatas[i]+"");
            //line.areaStyle();
            bars.add(bar);
        }


        Line line = new Line();
        ProxyInvokerHandlerUtil.processDataInvoker(line,ValuesData);
        line.name(piDatas[piDatas.length-1]+"");
        line.areaStyle();
        bars.add(line);




        categoryAxis.axisLabel().formatter(xLabelFormatter);
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter(yLabelFormatter);

        option.xAxis(categoryAxis);
        option.yAxis(valueAxis);

        option.series(bars);



        return  option.toString();

    }



    /**
     *
     * 多标记的柱状图 堆叠的状况显示
     * */
    public static String genMutiHistogramStackCommon(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter,Object[]piDatas){


        GsonOption option=new GsonOption();


        Legend legend=option.legend()
                .orient(Orient.horizontal).x(X.right);
        //相应的标记值
        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);



        option.title(titile).tooltip().trigger(Trigger.axis);//设置标题
        CategoryAxis categoryAxis = new CategoryAxis();
        ProxyInvokerHandlerUtil.processDataInvoker(categoryAxis,xdatas);

        List<Series> bars=new ArrayList<>();
        //创建 面积图多个
        for (int i = 0; i < ydatas.length; i++) {
            Bar bar = new Bar();
            ProxyInvokerHandlerUtil.processDataInvoker(bar,(Object[]) ydatas[i]);
            bar.name(piDatas[i]+"");
            bar.stack(titile);
            //line.areaStyle();
            bars.add(bar);
        }



        categoryAxis.axisLabel().formatter(xLabelFormatter);
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter(yLabelFormatter);

        option.xAxis(categoryAxis);
        option.yAxis(valueAxis);

        option.series(bars);



        return  option.toString();

    }



    /**
     *
     * 多标记的柱状图
     * */
    public static String genMutiHistogramCommon(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter,Object[]piDatas){


        GsonOption option=new GsonOption();


        Legend legend=option.legend()
                .orient(Orient.horizontal).x(X.right);
        //相应的标记值
        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);



        option.title(titile).tooltip().trigger(Trigger.axis);//设置标题
        CategoryAxis categoryAxis = new CategoryAxis();
        ProxyInvokerHandlerUtil.processDataInvoker(categoryAxis,xdatas);

        List<Series> bars=new ArrayList<>();
        //创建 面积图多个
        for (int i = 0; i < ydatas.length; i++) {
            Bar bar = new Bar();
            ProxyInvokerHandlerUtil.processDataInvoker(bar,(Object[]) ydatas[i]);
            bar.name(piDatas[i]+"");
            //line.areaStyle();
            bars.add(bar);
        }



        categoryAxis.axisLabel().formatter(xLabelFormatter);
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter(yLabelFormatter);

        option.xAxis(categoryAxis);
        option.yAxis(valueAxis);

        option.series(bars);



        return  option.toString();

    }



    public static String genHistogramCommen(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter){

        GsonOption option = new GsonOption();
//            Toolbox toolbox = new Toolbox().show(true);    //创建工具栏
//            toolbox.feature(new DataView().show(true).readOnly(false),new MagicType().show(true).type(new String[]{"line","bar"}), new Restore().show(true),new SaveAsImage().show(true));
//            option.toolbox(toolbox);
        //option.color(new Color(29, 33, 154));
        option.title(titile).tooltip().trigger(Trigger.axis);//设置标题
        CategoryAxis categoryAxis = new CategoryAxis();
        //创建柱状图
        Bar bar = new Bar();

        ProxyInvokerHandlerUtil.processDataInvoker(categoryAxis,xdatas);
        ProxyInvokerHandlerUtil.processDataInvoker(bar,ydatas);
        bar.name(barName);


        categoryAxis.axisLabel().formatter(xLabelFormatter);
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter(yLabelFormatter);

        option.xAxis(categoryAxis);
        option.yAxis(valueAxis);

        option.series(bar);
        return option.toString();


    }
    public static String genHistogramCommen1(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter){
//        Map<String,String> param = new HashMap<String, String>();
//        //List<Map<String,Object>> list = dao.test(param);    //数据库查询出的数据
//        List<Map<String, Object>> list = getData();
//        if(list.size()<1){
//            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("NAME","");
//            map.put("TOTAL",0);
//            list.add(map);
//        }
        GsonOption option = new GsonOption();
//            Toolbox toolbox = new Toolbox().show(true);    //创建工具栏
//            toolbox.feature(new DataView().show(true).readOnly(false),new MagicType().show(true).type(new String[]{"line","bar"}), new Restore().show(true),new SaveAsImage().show(true));
//            option.toolbox(toolbox);
        option.color(new Color(154, 34, 30));
        option.title(titile).tooltip().trigger(Trigger.axis);//设置标题
        CategoryAxis categoryAxis = new CategoryAxis();
        //创建柱状图
        Bar bar = new Bar();
//        for(Map<String,Object> map : list){
//            categoryAxis.data(map.get("NAME"));
//            bar.data(map.get("TOTAL"));
//        }
        ProxyInvokerHandlerUtil.processDataInvoker(categoryAxis,xdatas);
        ProxyInvokerHandlerUtil.processDataInvoker(bar,ydatas);
        bar.name(barName);
//        Mark average = new Mark();//创建标记
//        average.type("average").title("平均值");
//        bar.markLine().data(average);//标记线
        categoryAxis.axisLabel().formatter(xLabelFormatter);
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter(yLabelFormatter);

        option.xAxis(categoryAxis);
        option.yAxis(valueAxis);
        //滑块   startValue 从第几个开始显示   endValue  显示到第几个
//            option.dataZoom(( new DataZoom().type(DataZoomType.slider));
//            //拖动
//            option.dataZoom(new DataZoom().type(DataZoomType.inside));
        option.series(bar);
        return option.toString();


    }


}

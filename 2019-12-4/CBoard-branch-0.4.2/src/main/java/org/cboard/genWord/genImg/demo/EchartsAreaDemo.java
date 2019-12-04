package org.cboard.genWord.genImg.demo;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Lines;
import com.github.abel533.echarts.series.Series;
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenf on 2019/12/4.
 */
public class EchartsAreaDemo {

    /**
     *
     * 单标记的面积图
     * */
    public static String genAreaCommon(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter){


        GsonOption option=new GsonOption();

        option.title(titile).tooltip().trigger(Trigger.axis);//设置标题
        CategoryAxis categoryAxis = new CategoryAxis();
        //创建 面积图
        Line line = new Line();

        ProxyInvokerHandlerUtil.processDataInvoker(categoryAxis,xdatas);
        ProxyInvokerHandlerUtil.processDataInvoker(line,ydatas);
        line.name(barName);
        line.areaStyle();


        categoryAxis.axisLabel().formatter(xLabelFormatter);
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter(yLabelFormatter);

        option.xAxis(categoryAxis);
        option.yAxis(valueAxis);

        option.series(line);



        return  option.toString();

    }

    /**
     *
     * 多标记的面积图
     * */
    public static String genMutiAreaCommon(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter,Object[]piDatas){


        GsonOption option=new GsonOption();


        Legend legend=option.legend()
                .orient(Orient.horizontal).x(X.right);
        //相应的标记值
        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);



        option.title(titile).tooltip().trigger(Trigger.axis);//设置标题
        CategoryAxis categoryAxis = new CategoryAxis();
        ProxyInvokerHandlerUtil.processDataInvoker(categoryAxis,xdatas);

        List<Series> lines=new ArrayList<>();
        //创建 面积图多个
        for (int i = 0; i < ydatas.length; i++) {
            Line line = new Line();
            ProxyInvokerHandlerUtil.processDataInvoker(line,(Object[]) ydatas[i]);
            line.name(piDatas[i]+"");
            line.areaStyle();
            lines.add(line);
        }



        categoryAxis.axisLabel().formatter(xLabelFormatter);
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().formatter(yLabelFormatter);

        option.xAxis(categoryAxis);
        option.yAxis(valueAxis);

        option.series(lines);



        return  option.toString();

    }



}

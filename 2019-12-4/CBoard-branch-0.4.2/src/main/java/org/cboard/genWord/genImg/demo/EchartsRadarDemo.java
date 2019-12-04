package org.cboard.genWord.genImg.demo;

import com.github.abel533.echarts.Data;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Radar;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.RadarSeries;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.TextStyle;
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenf on 2019/12/4.
 */
public class EchartsRadarDemo {

    public static String genRadarCommon(String title,String subtext,String formatter,Object[] piDatas,Object[] Valuedatas,Object[] innerValueDatas){

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
       // option.tooltip().show(true).formatter(formatter);
        Legend legend=option.legend()
                .orient(Orient.vertical).x(X.left);

        ProxyInvokerHandlerUtil.processPiDataInvoker(legend,piDatas);


        List<Radar.Indicator> indicator =new ArrayList<>();
        for (int i = 0; i < Valuedatas.length; i++) {
            indicator.add((Radar.Indicator) Valuedatas[i]);
        }


        TextStyle dataStyle=new TextStyle();
        dataStyle.color("#fff");
        dataStyle.backgroundColor("#999");
        dataStyle.borderRadius(3);
        dataStyle.padding(new Object[]{3,5});


        Radar radar1=new Radar();
        radar1.indicator(indicator);
        radar1.name().textStyle(dataStyle);

        RadarSeries radar=new RadarSeries();
        ProxyInvokerHandlerUtil.processDataInvoker(radar,innerValueDatas);

        option.radar(radar1);
        option.series(radar);



        return  option.toString();


    }




}

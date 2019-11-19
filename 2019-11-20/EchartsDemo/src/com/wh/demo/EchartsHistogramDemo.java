package com.wh.demo;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.Mark;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;

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
        for(int i = 0;i < 100;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("NAME",i);
            map.put("TOTAL",Math.round(Math.random()*100));
            list.add(map);
        }
        return list;
    }
    public static String genHistogramCommen(String titile){
        Map<String,String> param = new HashMap<String, String>();
        //List<Map<String,Object>> list = dao.test(param);    //数据库查询出的数据
        List<Map<String, Object>> list = getData();
        if(list.size()<1){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("NAME","");
            map.put("TOTAL",0);
            list.add(map);
        }
        GsonOption option = new GsonOption();
//            Toolbox toolbox = new Toolbox().show(true);    //创建工具栏
//            toolbox.feature(new DataView().show(true).readOnly(false),new MagicType().show(true).type(new String[]{"line","bar"}), new Restore().show(true),new SaveAsImage().show(true));
//            option.toolbox(toolbox);
        option.color("#3398DB");
        option.title(titile).tooltip().trigger(Trigger.axis);//设置标题
        CategoryAxis categoryAxis = new CategoryAxis();
        //创建柱状图
        Bar bar = new Bar();
        for(Map<String,Object> map : list){
            categoryAxis.data(map.get("NAME"));
            bar.data(map.get("TOTAL"));
        }
        bar.name("数量");
//        Mark average = new Mark();//创建标记
//        average.type("average").title("平均值");
//        bar.markLine().data(average);//标记线
        option.xAxis(categoryAxis);
        option.yAxis(new ValueAxis());
        //滑块   startValue 从第几个开始显示   endValue  显示到第几个
//            option.dataZoom(( new DataZoom().type(DataZoomType.slider));
//            //拖动
//            option.dataZoom(new DataZoom().type(DataZoomType.inside));
        option.series(bar);
        return option.toString();


    }


}

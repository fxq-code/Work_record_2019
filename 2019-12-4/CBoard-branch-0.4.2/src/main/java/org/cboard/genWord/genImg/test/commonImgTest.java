package org.cboard.genWord.genImg.test;

import com.github.abel533.echarts.Radar;
import com.github.abel533.echarts.data.Data;
import org.cboard.genWord.genImg.Common.CommonImg4Word;
import org.cboard.genWord.genImg.Common.CommonParamsVo;
import org.cboard.genWord.genImg.demo.MyIndicator;

import java.io.IOException;

import static org.cboard.genWord.genImg.Common.CommonImg4Word.genCommonImg;
import static org.cboard.genWord.genImg.EchartsEngine.GenEchartsPic.genGaugeImg;

/**
 * Created by chenf on 2019/12/3.
 */
public class commonImgTest {

    public static void main(String[] args) throws IOException {
        String legend="呵呵哒";
        Object[] xdatas=new Object[]{"老年人", "儿童", "妇女", "高血压", "糖尿病", "精神病", "冠心病"};

        Object[] xdatas1=new Object[]{10, 20, 30, 40, 50, 60, 70};
        Object[] ydatas=new Object[]{0,6,1,7,6,4,4};
        String xLabelFormatter="{value} km";
        String yLabelFormatter="{value} C";
        String barName="数量";

        String piFormatter="{a} <br/>{b} : {c} ({d}%)";

        Object[] pidatas={"68%的人表示过的不错", "29%的人表示生活压力很大", "3%的人表示“我姓曾”"};
        Object [] pidatasCyclie={"直达","营销广告","搜索引擎","邮件营销","联盟广告","视频广告","百度","谷歌","必应","其他"};
        Object [] funnelData={"展现","点击","访问","咨询","订单"};
        int[]values={68,29,3000000};
        Object[]values1={68.64,29.98,3.4556789112223456789123456E12};

        Object[] objects11={
                new Data("访问", 8196.0),
                new Data("咨询", 343074),
                // new Data("订单", 5.49455678911222345634E12),
                new Data("订单", 5.49455E12),

        };

        Object[] objects1={
                new Data("访问", 30),
                new Data("咨询", 10),
                new Data("订单", 5),
                new Data("点击", 50),
                new Data("展现", 80)
        };

        Object[] objects2={
                new Data("直达", 335),
                new Data("邮件营销", 310),
                new Data("联盟广告", 234),
                new Data("视频广告", 135),
                new Data("百度", 1048),
                new Data("谷歌", 251),
                new Data("必应", 147),
                new Data("其他", 102)
        };
        Object[]objects21={
                new Data( "直达",335),
//                new Data("营销广告",679),
//                new Data("搜索引擎",1548)
        };

        CommonParamsVo commonParamsVo=new CommonParamsVo();
        commonParamsVo.setTitile(legend);
        commonParamsVo.setSubtitile(legend);
        commonParamsVo.setFormatter(piFormatter);
        commonParamsVo.setPiDatas(funnelData);
        commonParamsVo.setValuesDatas(objects11);

        genCommonImg("gauge",commonParamsVo);
        //pi图
        commonParamsVo.setPiDatas(pidatas);
        commonParamsVo.setValuesDatas(values1);
        genCommonImg("pi",commonParamsVo);
        //生成环图
        commonParamsVo.setPiDatas(pidatasCyclie);
        commonParamsVo.setValuesDatas(objects2);
        genCommonImg("cyc",commonParamsVo);
        commonParamsVo.setInnerDatas(objects21);
        genCommonImg("cyc2",commonParamsVo);
//生成柱状图
        commonParamsVo.setxFormatter(xLabelFormatter);
        commonParamsVo.setXdatas(xdatas);
        commonParamsVo.setyFormatter(yLabelFormatter);
        commonParamsVo.setYdatas(ydatas);
        genCommonImg("his",commonParamsVo);
        //曲线图
        genCommonImg("line",commonParamsVo);
        //曲线图
        genCommonImg("area",commonParamsVo);
        //漏斗图
//        commonParamsVo.setFormatter(piFormatter);
//        commonParamsVo.setPiDatas(funnelData);
//        commonParamsVo.setValuesDatas(objects1);
//        genCommonImg("funnel",commonParamsVo);


        Object[]radarLegend={"预算分配（Allocated Budget）", "实际开销（Actual Spending）"};
        Object[]indicator={

            new MyIndicator("销售（sales)",6500),
                new MyIndicator("管理（Administration）",16000),
                new MyIndicator("信息技术（Information Techology）",30000),
                new MyIndicator("客服（Customer Support）",38000),
                new MyIndicator("研发（Development）",52000),
                new MyIndicator("市场（Marketing）",25000),

        };

        Object []radarData={
                new Data("预算分配（Allocated Budget）",new Object[]{4300, 10000, 28000, 35000, 50000, 19000}),
                new Data("实际开销（Actual Spending）",new Object[]{5000, 14000, 28000, 31000, 42000, 21000})
        };

        commonParamsVo.setPiDatas(radarLegend);
        commonParamsVo.setValuesDatas(indicator);
        commonParamsVo.setInnerDatas(radarData);

        //雷达图测试
        genCommonImg("radar",commonParamsVo);



        /**多面积图测试*/
        commonParamsVo.setPiDatas(new Object[]{"邮件营销","联盟广告","视频广告","直接访问","搜索引擎"});
        commonParamsVo.setXdatas(new Object[]{"周一","周二","周三","周四","周五","周六","周日"});
        commonParamsVo.setYdatas(new Object[]{
                new Object[]{120, 132, 101, 134, 90, 230, 210},
                new Object[]{220, 182, 191, 234, 290, 330, 310},
                new Object[]{150, 232, 201, 154, 190, 330, 410},
                new Object[]{320, 332, 301, 334, 390, 330, 320},
                new Object[]{820, 932, 901, 934, 1290, 1330, 1320},

        });
        genCommonImg("mutiarea",commonParamsVo);

        /**多曲线图测试*/
        genCommonImg("mutiline",commonParamsVo);

        /**多柱状图测试*/

        genCommonImg("mutihist",commonParamsVo);
        genCommonImg("mutihiststack",commonParamsVo);

        /**多标记折线图为主柱状图*/
        commonParamsVo.setPiDatas(new Object[]{"邮件营销","联盟广告","视频广告","直接访问","搜索引擎","baidu..."});
        commonParamsVo.setValuesDatas(new Object[]{101,99,240,336,444,559,789});
        genCommonImg("mutiline2hist",commonParamsVo);//mutihist2area


        /**多标记折线图为主的 面积图*/
        genCommonImg("mutihist2area",commonParamsVo);



       // genGaugeImg(legend,legend,piFormatter,funnelData,objects11);
        //System.out.println(genHistogramImg(legend,barName,xdatas,xLabelFormatter,ydatas,yLabelFormatter));
        // genPiImg(legend,barName,piFormatter,pidatas,values1);

        //genCyclicImg(legend,legend,piFormatter,pidatasCyclie,objects2);
        // genCyclic2Img(legend,legend,piFormatter,pidatasCyclie,objects2,objects21);
        // genFunnelImg(legend,legend,piFormatter,funnelData,objects1);
        //genCurveLineImg(legend,xdatas,xLabelFormatter,ydatas,yLabelFormatter,legend);
    }
}

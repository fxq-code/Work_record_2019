package org.cboard.genWord.genImg.Common;

import org.cboard.genWord.genImg.EchartsEngine.GenEchartsPic;
import org.cboard.genWord.genImg.demo.*;
import org.cboard.genWord.genImg.utils.JsonFormatUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import static org.cboard.genWord.genImg.EchartsEngine.GenEchartsPic.genPiImg;
import static org.cboard.genWord.genImg.EchartsEngine.GenEchartsPic.generateEChart;
import static org.cboard.genWord.genImg.EchartsEngine.GenEchartsPic.saveAsJsonFile;
import static org.cboard.genWord.genImg.demo.EchartsCyclicDemo.genCyclicCommon;

/**
 * Created by chenf on 2019/12/3.
 */
public class CommonImg4Word {

    public static String genCommonImg(String chartName,CommonParamsVo commonParamsVo) throws IOException {

        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=GenEchartsPic.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        String JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");

        if(chartName.isEmpty()||commonParamsVo==null){
            throw  new RuntimeException("绘制图片出错，请检查相应的参数的设置！");
        }
        boolean isSave=false;

        if(chartName.toLowerCase().equals("pi")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsPiDemo.genPiConmen(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getFormatter(),commonParamsVo.getPiDatas(),commonParamsVo.getValuesDatas())), infilePath, infile);
        }else if(chartName.toLowerCase().equals("funnel")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsFunnelDemo.genFunnelCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getFormatter(),commonParamsVo.getPiDatas(),commonParamsVo.getValuesDatas())),infilePath, infile);
        }else if(chartName.toLowerCase().equals("cyc")){

            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(genCyclicCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getFormatter(),commonParamsVo.getPiDatas(),commonParamsVo.getValuesDatas())), infilePath, infile);

        }else if(chartName.toLowerCase().equals("cyc2")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsCyclicDemo.genCyclic2Common(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getFormatter(),commonParamsVo.getPiDatas(),commonParamsVo.getValuesDatas(),commonParamsVo.getInnerDatas())), infilePath, infile);
        }else if(chartName.toLowerCase().equals("his")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsHistogramDemo.genHistogramCommen(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter())), infilePath, infile);
        }else if(chartName.toLowerCase().equals("line")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsLineDemo.getCurveLineOption(commonParamsVo.getTitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter(),commonParamsVo.getSubtitile())), infilePath, infile);
        }else if(chartName.toLowerCase().equals("gauge")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsGaugeDemo.genGaugeConmen(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getFormatter(),commonParamsVo.getPiDatas(),commonParamsVo.getValuesDatas())), infilePath, infile);
        }else if(chartName.toLowerCase().equals("area")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsAreaDemo.genAreaCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter())), infilePath, infile);
        }else if(chartName.toLowerCase().equals("radar")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsRadarDemo.genRadarCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getFormatter(),commonParamsVo.getPiDatas(),commonParamsVo.getValuesDatas(),commonParamsVo.getInnerDatas())), infilePath, infile);
        }else if(chartName.toLowerCase().equals("mutiarea")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsAreaDemo.genMutiAreaCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter(),commonParamsVo.getPiDatas())), infilePath, infile);

        }else if(chartName.toLowerCase().equals("mutiline")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsLineDemo.genMutiLineCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter(),commonParamsVo.getPiDatas())), infilePath, infile);

        }else if(chartName.toLowerCase().equals("mutihist")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsHistogramDemo.genMutiHistogramCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter(),commonParamsVo.getPiDatas())), infilePath, infile);

        }else if(chartName.toLowerCase().equals("mutihiststack")){
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsHistogramDemo.genMutiHistogramStackCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter(),commonParamsVo.getPiDatas())), infilePath, infile);

        }else if(chartName.toLowerCase().equals("mutiline2hist")){//以多标记折线图为主的 柱状图
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsLineDemo.genMutiLine2HistCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter(),commonParamsVo.getPiDatas(),commonParamsVo.getValuesDatas())), infilePath, infile);

        }else if(chartName.toLowerCase().equals("mutihist2area")){//以多标记折线图为主的 柱状图
            isSave= saveAsJsonFile(JsonFormatUtil.formatJson(EchartsHistogramDemo.genMutiHistogram2AreaCommon(commonParamsVo.getTitile(),commonParamsVo.getSubtitile(),commonParamsVo.getXdatas(),commonParamsVo.getxFormatter(),commonParamsVo.getYdatas(),commonParamsVo.getyFormatter(),commonParamsVo.getPiDatas(),commonParamsVo.getValuesDatas())), infilePath, infile);

        }else{
            throw new RuntimeException("该绘图功能还未添加！");
        }


        if(!isSave){
            throw new RuntimeException("绘制图片失败！");
        }else{

            return generateEChart(infilePath+"\\"+infile,outfilePath,JSpath);
        }
    }



}

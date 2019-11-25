package org.cboard.genWord.genImg.utils;

import com.github.abel533.echarts.data.Data;
import org.cboard.genWord.genImg.EchartsEngine.GenEchartsPic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import static org.cboard.genWord.genImg.EchartsEngine.GenEchartsPic.*;
import static org.cboard.genWord.genImg.demo.EchartsCyclicDemo.genCyclicCommon;
import static org.cboard.genWord.genImg.demo.EchartsFunnelDemo.genFunnelCommon;
import static org.cboard.genWord.genImg.demo.EchartsHistogramDemo.genHistogramCommen;

/**
 * Created by chenf on 2019/11/20.
 */
public class Echarts2WordUtil {


    public static String[] getImgFileNameArrayByCenter() throws IOException {
        String []pngArray=new String[15];
        /**图数据准备阶段完成，下面开始写word*/
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
        int[]values={68,29,3};


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


//        String hisImgFile=getHistogramImgFileName(legend, barName, xdatas, xLabelFormatter, ydatas, yLabelFormatter);
//        String cyclicImgFile=getCyclicImgFileName(legend,legend,piFormatter,pidatasCyclie,objects2);
//        String funnelImgFile=getFunnelImgFileName(legend,legend,piFormatter,funnelData,objects1);
        String hisImgFile=genHistogramImg(legend, barName, xdatas, xLabelFormatter, ydatas, yLabelFormatter);
        String cyclicImgFile=genCyclicImg(legend,legend,piFormatter,pidatasCyclie,objects2);
        String funnelImgFile=genFunnelImg(legend,legend,piFormatter,funnelData,objects1);

        for (int i = 0; i < pngArray.length; i++) {
            pngArray[i]="earth.png";
        }


        pngArray[0]=hisImgFile;
        pngArray[1]=cyclicImgFile;
        pngArray[2]=funnelImgFile;
        //System.out.println(hisImgFile+" "+cyclicImgFile+" "+funnelImgFile);

        return  pngArray;

    }

    public static String isIntergerOrDouble(Object num){
        String input=num+"";
        if (!input.matches("^[0-9]+([.]{0,1}[0-9]+){0,1}$")){

            System.out.println("this is not a number\n");
            return "FALSE";

        }else if (input.matches("^[0-9]+$")){

            System.out.print("this is a integer\n");

            return "TRUE@Integer";
        }else {

            System.out.print("this is a double\n");
            return "TRUE@Double";

        }

    }

    public static String[] getImgFileNameArrayByDepart() throws IOException {
        String []pngArray=new String[8];
        /**图数据准备阶段完成，下面开始写word*/
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
        int[]values={68,29,3};


        Object[] objects1={
                new Data("访问", 30.3),
                new Data("咨询", 10.4),
                new Data("订单", 5.55),
                new Data("点击", 50.66),
                new Data("展现", 80.8)
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


//        String hisImgFile=getHistogramImgFileName(legend, barName, xdatas, xLabelFormatter, ydatas, yLabelFormatter);
//        String cyclicImgFile=getCyclicImgFileName(legend,legend,piFormatter,pidatasCyclie,objects2);
//        String funnelImgFile=getFunnelImgFileName(legend,legend,piFormatter,funnelData,objects1);
        String hisImgFile=genHistogramImg(legend, barName, xdatas, xLabelFormatter, ydatas, yLabelFormatter);
      String cyclicImgFile=genCyclicImg(legend,legend,piFormatter,pidatasCyclie,objects2);
        String funnelImgFile=genFunnelImg(legend,legend,piFormatter,funnelData,objects1);

        for (int i = 0; i < pngArray.length; i++) {
            pngArray[i]="earth.png";
        }

        //pngArray[0]="D:/temp/Echart/67f8eea8-d510-495b-ad1f-8f2c056ac83d.png";
        pngArray[0]=hisImgFile;
        pngArray[1]=cyclicImgFile;
        pngArray[2]=funnelImgFile;
        //System.out.println(hisImgFile+" "+cyclicImgFile+" "+funnelImgFile);

        return  pngArray;

    }



    //生成 柱状图的相应的通用的方法
    public static String getHistogramImgFileName(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter)throws IOException{
        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=Echarts2WordUtil.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        String JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");

        boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(genHistogramCommen(titile,barName,xdatas,xLabelFormatter,ydatas,yLabelFormatter)), infilePath, infile);
        String jsonFullPath=FilePathUtil.getRealFilePath(infilePath+"\\"+infile);
        System.out.println("jsonFull1="+jsonFullPath);
        System.out.println(infilePath+"\\"+infile);
        return generateEChart(jsonFullPath,outfilePath);

    }

    //生成 环状图
    public static String getCyclicImgFileName(String title,String subtext,String formatter,Object[] piDatas,Object[] Valuedatas) throws IOException {
        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=Echarts2WordUtil.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        String JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");

        boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(genCyclicCommon(title,subtext,formatter,piDatas,Valuedatas)), infilePath, infile);
        String jsonFullPath=FilePathUtil.getRealFilePath(infilePath+"\\"+infile);
        return generateEChart(jsonFullPath,outfilePath);


    }

    //生成漏斗图的图片路径
    public static String getFunnelImgFileName(String title,String subtext,String formatter,Object[] piDatas,Object[] Valuedatas) throws IOException {

        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=Echarts2WordUtil.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        String JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");


        boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(genFunnelCommon(title,subtext,formatter,piDatas,Valuedatas)), infilePath, infile);
        String jsonFullPath=FilePathUtil.getRealFilePath(infilePath+"\\"+infile);

        return generateEChart(jsonFullPath,outfilePath);

    }


    public static String genImgPath(String pngFileName){
        String imgPath="";
        InputStream res = Echarts2WordUtil.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        try {
            properties.load(res);
            String outfilePath = properties.getProperty("outfilePath");
            imgPath=outfilePath+"\\"+pngFileName;

            //这里记得加上imgPath的相关的分隔符
            imgPath=FilePathUtil.getRealFilePath(imgPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  imgPath;

    }

}

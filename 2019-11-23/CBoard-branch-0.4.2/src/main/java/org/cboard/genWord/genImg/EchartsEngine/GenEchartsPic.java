package org.cboard.genWord.genImg.EchartsEngine;



import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import org.cboard.genWord.genImg.utils.JsonFormatUtil;
import org.cboard.genWord.genImg.utils.ProxyInvokerHandlerUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;



import static java.lang.Class.*;
import static java.util.Collections.*;
import static org.cboard.genWord.genImg.demo.EchartsCyclicDemo.genCyclicCommon;
import static org.cboard.genWord.genImg.demo.EchartsFunnelDemo.genFunnelCommon;
import static org.cboard.genWord.genImg.demo.EchartsHistogramDemo.genHistogramCommen;
import static org.cboard.genWord.genImg.demo.EchartsLineDemo.getCurveLineOption;
import static org.cboard.genWord.genImg.demo.EchartsLineDemo.getPieOption;
import static org.cboard.genWord.genImg.demo.EchartsPiDemo.genPiConmen;
import static org.cboard.genWord.genImg.demo.EchartsPiDemo.genPiInfo;

public class GenEchartsPic {

  //  private static final String JSpath = "E:\\echarts4\\echarts-convert.js";
  private static  String JSpath ="";
    private static final Logger logger=Logger.getLogger(String.valueOf(GenEchartsPic.class));
    /*
     * 主程序
     */
    public static  String generateEChart(String infile,String outfile) throws IOException {
        InputStream inputStream=GenEchartsPic.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        String PHANTOMJS_PATH=properties.getProperty("PHANTOMJS_PATH");
        String width=properties.getProperty("WIDTH");
        String height=properties.getProperty("HEIGH");

        try {
            File file = new File(outfile);     //文件路径
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            String cmd = PHANTOMJS_PATH+" " + JSpath + " -infile " + infile + " -outfile " + outfile+" -width "+width+" -height "+height;//生成命令行
            Process process = Runtime.getRuntime().exec(cmd);
            //这行代码会 阻塞当前线程 直到exec 这个命令执行完才继续调用
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
           // removeJsonFile(infile);
        }
        return outfile;
    }

    /***
     * 将相应的json字符串转换成json file落地在本地磁盘中
     */
    public static boolean saveAsJsonFile(String options, String filePath, String fileName) {
        if (filePath.trim().isEmpty() || options.trim().isEmpty()) return false;
         //logger.info("option json formatter :"+ JsonFormatUtil.formatJson(options));
        //logger.info(" "+filePath+" "+fileName);

        try {
            //options=new String(options.getBytes("ISO-8859-1"),"utf-8");
            //options=new String(options.getBytes("gbk"),"utf-8");
            File file = new File(filePath);     //文件路径
            if (!file.exists())
                file.mkdirs();
            //BufferedWriter out = new BufferedWriter(new FileWriter(filePath + "\\" + fileName));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + "\\" + fileName)
            , "utf-8"));
            out.write(options);
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后关闭文件
            logger.info("write finised....");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
            return true;

    }


    /**
     * 启动引擎，生成目标文件
     * */
    private  static String startEngine(String jsonPath,String outfile) throws IOException {
        InputStream inputStream= GenEchartsPic.class.getClassLoader().getResourceAsStream(jsonPath);
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = inputStream.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        String options=out.toString();
        System.out.println("options="+options);
        String picPath = generateEChart(jsonPath,outfile);
        return picPath;
    }

    private static boolean removeJsonFile(String jsonFilePath){
        logger.info("jsonFilePath="+jsonFilePath);
        if(jsonFilePath.trim().isEmpty()||jsonFilePath.length()<0) return  false;

        File file = new File(jsonFilePath);
        if(!file.exists()) return  true;

        file.delete();
        return  true;


    }



    public static String genPiImg(String titile,String subtext,String formatter,Object[]piDatas,ArrayList<Series> parts)throws IOException{
        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=GenEchartsPic.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");

         boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(genPiConmen(titile,subtext,formatter,piDatas,parts)), infilePath, infile);
        // boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getLineOption()), infilePath, infile);
        return generateEChart(infilePath+"\\"+infile,outfilePath);

    }

    public static String genCurveLineImg(String legend,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter,String smoothName)throws IOException{
        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=GenEchartsPic.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");

        // boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getLineOption()), infilePath, infile);
         boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getCurveLineOption(legend,xdatas,xLabelFormatter,ydatas,yLabelFormatter,smoothName)), infilePath, infile);

        return generateEChart(infilePath+"\\"+infile,outfilePath);

    }



    //生成漏斗图
    public static String genFunnelImg(String title,String subtext,String formatter,Object[] piDatas,Object[] Valuedatas) throws IOException {
        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=GenEchartsPic.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");


        boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(genFunnelCommon(title,subtext,formatter,piDatas,Valuedatas)), infilePath, infile);
        //System.out.println("json file save "+isSave);
        return generateEChart(infilePath+"\\"+infile,outfilePath);


    }


    //生成 环状图
    public static String genCyclicImg(String title,String subtext,String formatter,Object[] piDatas,Object[] Valuedatas) throws IOException {
        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=GenEchartsPic.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");

        boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(genCyclicCommon(title,subtext,formatter,piDatas,Valuedatas)), infilePath, infile);
        //System.out.println("json file save "+isSave);
        return generateEChart(infilePath+"\\"+infile,outfilePath);


    }

    public static String genHistogramImg(String titile,String barName,Object[]xdatas,String xLabelFormatter,Object[] ydatas,String yLabelFormatter)throws IOException{
        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=GenEchartsPic.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");

        //boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getPieOption()), infilePath, infile);
        // boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getLineOption()), infilePath, infile);
        // boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getCurveLineOption()), infilePath, infile);

        boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(genHistogramCommen(titile,barName,xdatas,xLabelFormatter,ydatas,yLabelFormatter)), infilePath, infile);
        //System.out.println("json file save "+isSave);
        return generateEChart(infilePath+"\\"+infile,outfilePath);
        //List xdatas = Collections.singletonList(new int[]{10, 20, 30, 40, 50, 60, 70});

//        Object[] arrrys=new Object[]{10, 20, 30, 40, 50, 60, 70};
//        try {
//            Class clazz= Class.forName("com.wh.EchartsEngine.GenEchartsPic");
//            Method method=clazz.getMethod("mutipleParams",Object[].class);
//            Object[]p={arrrys};
//            method.invoke(clazz,p);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        mutipleParams(arrrys);
    }

    public static void main(String[] args) throws IOException {
        testVisited();

    }





    private static void testVisited() throws IOException {
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


        System.out.println(genHistogramImg(legend,barName,xdatas,xLabelFormatter,ydatas,yLabelFormatter));
        //genPiImg(legend,barName,piFormatter,pidatas,genPiInfo(pidatas,values));

        genCyclicImg(legend,legend,piFormatter,pidatasCyclie,objects2);
        genFunnelImg(legend,legend,piFormatter,funnelData,objects1);
        //genCurveLineImg(legend,xdatas,xLabelFormatter,ydatas,yLabelFormatter,legend);
    }
    public static void mutipleParams(Object... args){
        System.out.println(args.length);
        for(int i=0;i<args.length;i+=1){
            System.out.print(args[i]+" ");
        }
    }
}

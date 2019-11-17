package com.wh.EchartsEngine;



import com.wh.demo.EchartsLineDemo;
import com.wh.utils.JsonFormatUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;

import static com.wh.demo.EchartsLineDemo.getCurveLineOption;
import static com.wh.demo.EchartsLineDemo.getLineOption;
import static com.wh.demo.EchartsLineDemo.getPieOption;
import static java.lang.Class.*;
import static java.util.Collections.*;

public class GenEchartsPic {

  //  private static final String JSpath = "E:\\echarts4\\echarts-convert.js";
  private static  String JSpath ="";
    private static final Logger logger=Logger.getLogger(String.valueOf(GenEchartsPic.class));
    /*
     * 主程序
     */
    public static  String generateEChart(String infile,String outfile) {

        try {
            File file = new File(outfile);     //文件路径
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            String cmd = "phantomjs " + JSpath + " -infile " + infile + " -outfile " + outfile;//生成命令行
            Process process = Runtime.getRuntime().exec(cmd);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
           // removeJsonFile(infile);
        }
        return outfile;
    }

    /***
     * 将相应的json字符串转换成json file落地在本地磁盘中
     */
    public static boolean saveAsJsonFile(String options, String filePath, String fileName) {
        if (filePath.trim().isEmpty() || options.trim().isEmpty()) return false;
        // logger.info("option json formatter :"+ JsonFormatUtil.formatJson(options));
        try {
            File file = new File(filePath);     //文件路径
            if (!file.exists()) return false;
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath + "\\" + fileName));
            out.write(options);
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后关闭文件
            logger.info("write finised....");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            return true;
        }
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
    public static void main(String[] args) throws IOException {
        String uuidStr= UUID.randomUUID().toString();
        String outfile=uuidStr + ".png";
        String infile= uuidStr + ".json";

        InputStream inputStream=GenEchartsPic.class.getClassLoader().getResourceAsStream("resource/genEcharts.properties");
        Properties properties=new Properties();
        properties.load(inputStream);
        JSpath=properties.getProperty("JSpath");
        String outfilePath=properties.getProperty("outfilePath")+outfile;
        String infilePath=properties.getProperty("infilePath");

        //boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getPieOption()), infilePath, infile);
       // boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getLineOption()), infilePath, infile);
        boolean isSave = saveAsJsonFile(JsonFormatUtil.formatJson(getCurveLineOption()), infilePath, infile);
        //System.out.println(isSave);
        System.out.println(generateEChart(infilePath+"\\"+infile,outfilePath));
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

    public static void mutipleParams(Object... args){
        System.out.println(args.length);
        for(int i=0;i<args.length;i+=1){
            System.out.print(args[i]+" ");
        }
    }
}

package com.cyl.jFreeChartTest.docxTest;

import org.docx4j.Docx4J;
import org.docx4j.TraversalUtil;
import org.docx4j.finders.RangeFinder;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;


public class Docx4jTest {
    public static void main(String[] args) throws Exception {

        // 设置的标签
        ArrayList<String> bmList = new ArrayList<String>();
        bmList.add("username");
        bmList.add("userage");
        bmList.add("usersex");

        // 文件源目录
        String srcPath = "D:\\doc4j\\test\\Demo2.docx";
        // 目标文件目录
        String destPath = "E:\\bigData\\workSystemFile\\workSystemFile\\wh_target_"+ UUID.randomUUID().toString()+".docx";
        Map<String, String> map = new HashMap<String,String>();
        map.put("username","张三");
        map.put("usersex","男");
        map.put("userage","27");

        // 打开一个存在的word
        WordprocessingMLPackage wPackage = WordprocessingMLPackage.load(new FileInputStream(srcPath));
        // 作word处理
        WordprocessingMLPackage wPackage1 = replaceContentByBookmark(wPackage, map, bmList);
        wPackage1.save(new File(destPath));
       // Docx4J.toPDF(wPackage1,new FileOutputStream(new File("E:\\bigData\\workSystemFile\\workSystemFile\\wh_target_"+ UUID.randomUUID().toString()+".pdf")));
      /*  wPackage1.save(new File(destPath));
        Docx4J.toPDF(wPackage1,new FileOutputStream(new File("C:\\work\\workSystemFile\\5.pdf")));*/

    }


    public static  WordprocessingMLPackage  replaceContentByBookmark(WordprocessingMLPackage wPackage, Map<String, String> map, ArrayList<String> bmList)  {
        try {
            // 提取正文
            MainDocumentPart main = wPackage.getMainDocumentPart();
            Document doc = main.getContents();
            Body body = doc.getBody();

            // 获取段落
            List<Object> paragraphs  = body.getContent();

            // 提取书签并获取书签的游标
            RangeFinder rt = new RangeFinder("CTBookmark", "CTMarkupRange");
            new TraversalUtil(paragraphs, rt);

            // 遍历书签
            for (CTBookmark bm : rt.getStarts()) {
                // 替换文本内容
                for (String bmName: bmList) {
                    System.out.println("bm.getName: " + bm.getName() +" ---- bmName:" + bmName);
                    if (bm.getName().equals(bmName)){
                        Tool.replaceText(bm, map.get(bm.getName()));
                    }
                }

                // 替换image
                if (bm.getName().equals("userimg")){
                    Tool.addImage(wPackage, bm, "E:\\bigData\\workSystemFile\\workSystemFile\\4.jpg");
                }

                // 替换image
                if (bm.getName().equals("userimg2")){
                    Tool.addImage(wPackage, bm, "E:\\bigData\\workSystemFile\\workSystemFile\\333.jpg");
                }
            }

        } catch (Exception e){
            // handle exception
        }

        return  wPackage;
    }



}

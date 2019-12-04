package org.cboard.genWord.WordTemplete.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cboard.genWord.CoreEngine.entity.User;
import org.cboard.genWord.WordTemplete.Doc4jWordHandler;
import org.cboard.genWord.WordTemplete.FakeData.DataSet;
import org.cboard.genWord.WordTemplete.Vo.AssetsCategoryOfStock;
import org.cboard.genWord.WordTemplete.WordTemplate;
import org.cboard.genWord.genImg.utils.SqlCommonUtil;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenf on 2019/12/1.
 */
public class Doc4jDemo01 {
    public static void genSimpleWord01() throws Docx4JException {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        wordMLPackage.getMainDocumentPart()
                .addStyledParagraphOfText("Title", "Hello Maven Central");

        wordMLPackage.getMainDocumentPart().addParagraphOfText("from docx4j!");

        // Now save it
        wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/helloMavenCentral.docx") );

    }


    public static  void changeSimpleProject(){

    }


    /**
     * 加载模板并替换数据
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static void replaceData1(Map<String, String> data) throws Exception {
        final String TEMPLATE_NAME = "D:\\doc4j\\test\\Demo1.docx";
        InputStream templateInputStream = new FileInputStream(TEMPLATE_NAME);
        //加载模板文件并创建WordprocessingMLPackage对象
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        VariablePrepare.prepare(wordMLPackage);
        documentPart.variableReplace(data);
        OutputStream os = new FileOutputStream(new File("D:\\doc4j\\test\\out1.docx"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        wordMLPackage.save(outputStream);
        outputStream.writeTo(os);
        os.close();
        outputStream.close();
        templateInputStream.close();
    }


    public static void main(String[] args) throws Exception {
        //genSimpleWord01();
        Map<String, String> data = new HashMap();
        data.put("var", "三年二班");
        data.put("titile", "图1 xxx年度报告");

//        data.put("id", "图1 xxx年度报告");
//        data.put("name", "图1 xxx年度报告");
//        data.put("password", "图1 xxx年度报告");
//
//        data.put("id", "图1 xxx年度报告1");
//        data.put("name", "图1 xxx年度报告2");
//        data.put("password", "图1 xxx年度报告3");

        String TEMPLATE_NAME = "D:\\doc4j\\test\\Demo1.docx";
        String target = "D:\\doc4j\\test\\wh_target" + System.currentTimeMillis() + ".docx";
        InputStream templateInputStream = new FileInputStream(TEMPLATE_NAME);
        //加载模板文件并创建WordprocessingMLPackage对象
        WordprocessingMLPackage template = WordprocessingMLPackage.load(templateInputStream);

        Doc4jWordHandler.replaceText(template,data);
        // -------------------------在指定位置插入图片----------------------------------
        Map<String, String> markAndImgroute = new HashMap<String, String>();
        markAndImgroute.put("图一Img图片","D:\\temp\\Echart\\ee75a295-f72c-4b3a-810c-012d6cd862c2.png");
        markAndImgroute.put("建行logo图片放置处", "E:\\bigData\\workSystemFile\\workSystemFile\\ccbsLogo.png");
//        markAndImgroute.put("大类资产配置分析图放置处", "E:\\bigData\\workSystemFile\\workSystemFile\\assetCategories15625797775374698.jpg");
//        markAndImgroute.put("沪深300以及组合分析图片放置处", "E:\\bigData\\workSystemFile\\workSystemFile\\png1-1.png");
//        markAndImgroute.put("中债以及组合分析图片放置处", "E:\\bigData\\workSystemFile\\workSystemFile\\png2-2.png");
        //addImgOnMarkPosition(template, markAndImgroute);
        Doc4jWordHandler.addImgOnTextPosition(template, markAndImgroute);


        // -------------------------表格修改----------------------------------
        // 获取doc中所有的表格
        List<Object> tables = WordTemplate.getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);
        System.out.println("此doc一共有" + tables.size() + " 个表格！"+tables.get(0).toString());
        // 根据特殊标签获取要修改的表格
        Tbl tempTable = WordTemplate.getTemplateTable(tables, "序号");


        //填充表格 的使用demo 案例
        ArrayList<HashMap> list1= SqlCommonUtil.getResultList("select * from tbl_user",null);
        System.out.println(list1);
        Doc4jWordHandler.fillTables(tempTable,2,list1);


        WordTemplate.writeDocxToStream(template, target);



    }
}

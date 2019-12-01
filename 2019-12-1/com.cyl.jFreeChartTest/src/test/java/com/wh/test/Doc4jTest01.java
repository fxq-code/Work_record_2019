package com.wh.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyl.jFreeChartTest.docxTest.Docx4jDoTableAndImg;
import com.cyl.jFreeChartTest.docxTest.WordTemplate;
import com.cyl.jFreeChartTest.docxTest.util.AssetsCategoryOfStock;
import com.cyl.jFreeChartTest.docxTest.util.DataSet;
import com.wh.core.Doc4jWordHandler;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doc4jTest01 {

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

        data.put("id", "图1 xxx年度报告");
        data.put("name", "图1 xxx年度报告");
        data.put("password", "图1 xxx年度报告");

        data.put("id", "图1 xxx年度报告1");
        data.put("name", "图1 xxx年度报告2");
        data.put("password", "图1 xxx年度报告3");

         String TEMPLATE_NAME = "D:\\doc4j\\test\\Demo1.docx";
        String target = "D:\\doc4j\\test\\wh_target" + System.currentTimeMillis() + ".docx";
        InputStream templateInputStream = new FileInputStream(TEMPLATE_NAME);
        //加载模板文件并创建WordprocessingMLPackage对象
        WordprocessingMLPackage template = WordprocessingMLPackage.load(templateInputStream);

        Doc4jWordHandler.replaceText(template,data);
        // -------------------------在指定位置插入图片----------------------------------
        Map<String, String> markAndImgroute = new HashMap<String, String>();
        markAndImgroute.put("图一Img图片","D:\\temp\\Echart\\5b81b050-91ab-434b-81c7-5936fd6f721d.png");
        markAndImgroute.put("建行logo图片放置处", "E:\\bigData\\workSystemFile\\workSystemFile\\ccbsLogo.png");
//        markAndImgroute.put("大类资产配置分析图放置处", "E:\\bigData\\workSystemFile\\workSystemFile\\assetCategories15625797775374698.jpg");
//        markAndImgroute.put("沪深300以及组合分析图片放置处", "E:\\bigData\\workSystemFile\\workSystemFile\\png1-1.png");
//        markAndImgroute.put("中债以及组合分析图片放置处", "E:\\bigData\\workSystemFile\\workSystemFile\\png2-2.png");
        //addImgOnMarkPosition(template, markAndImgroute);
       Doc4jWordHandler.addImgOnTextPosition(template, markAndImgroute);


        // -------------------------表格修改----------------------------------
        // 组装模拟数据
        DataSet dataset = new DataSet();
        List<AssetsCategoryOfStock> demoList = dataset.getDemoList();
        String totalData = dataset.getTotalData(demoList);
        System.out.println("totalData "+totalData);
        JSONObject totalDataJson = JSONObject.parseObject(totalData);
        System.out.println("totalDataJson "+totalDataJson+" "+totalData.equals(totalDataJson));
        // 获取doc中所有的表格
        List<Object> tables = WordTemplate.getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);
        System.out.println("此doc一共有" + tables.size() + " 个表格！"+tables.get(0).toString());
        // 根据特殊标签获取要修改的表格
        Tbl tempTable = WordTemplate.getTemplateTable(tables, "序号");
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(demoList));
        Doc4jWordHandler.addDocTableRows(tempTable, 3, jsonArray, true, totalDataJson);

      WordTemplate.writeDocxToStream(template, target);



    }
}

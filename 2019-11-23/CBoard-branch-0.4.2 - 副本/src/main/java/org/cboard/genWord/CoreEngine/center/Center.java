package org.cboard.genWord.CoreEngine.center;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import org.cboard.genWord.CoreEngine.entity.ColloPriceEntity;
import org.cboard.genWord.CoreEngine.entity.ReportEntity;
import org.cboard.genWord.CoreEngine.entity.User;
import org.cboard.genWord.CoreEngine.templete.WordTemplete;
import org.cboard.genWord.genImg.utils.SqlCommonUtil;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by chenf on 2019/11/18.
 */
public class Center extends WordTemplete {
    private Document document;
    private BaseFont bfChinese;
    private static  int fontSize=9;
    private  static String fileName="";
    private  static  String[] sourcePath=new String[15];

    private  static ReportEntity reportEntity1=new ReportEntity();

    public BaseFont getBfChinese() {
        return bfChinese;
    }

    public void setBfChinese(BaseFont bfChinese) {
        this.bfChinese = bfChinese;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Center(){
        this.document = new Document(PageSize.A4);

    }



    public static String genCenterReport(String[]pngArray,ReportEntity reportEntity) throws DocumentException, IOException{
        Center wt = new Center();
        InputStream res = Center.class.getClassLoader().getResourceAsStream("genEcharts.properties");
        Properties properties=new Properties();
        properties.load(res);

        fileName=properties.getProperty("CENTER_PATH")+ UUID.randomUUID().toString()+".doc";
        wt.openDocument(fileName);
        wt.insertTitle("分行业绩指数报告(总行版)", fontSize, Font.BOLD, Element.ALIGN_CENTER);

        wt.insertContext("中国建设银行"+reportEntity.getYear()+"年度业绩总收入为（"+reportEntity.getYearIncomeAll()+"），同期增长（"+reportEntity.getIncrease()+"）%，增幅("+reportEntity.getAmplification()*100+")%,各项收入来源如下:", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg("D:\\temp\\Echart\\531e7c29-d460-4dc6-918c-928e1396e90b.png", Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("年度各分行托管费收入为:", fontSize, Font.NORMAL, Element.ALIGN_LEFT);

        ArrayList<User> list1= SqlCommonUtil.getResultList("select * from tbl_user",null);

        wt.insertCommonTable(new String[]{"测试代码","测试名称","行为代码"},6,new Color(122, 199, 255),list1);

        wt.insertContext("年度各分行银行费收入为:", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertCommonTable(new String[]{"测试代码","测试名称","行为代码"},6,new Color(122, 199, 255),list1);


        wt.insertContext("年度各分行资金沉淀收入为:", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertCommonTable(new String[]{"测试代码","测试名称","行为代码"},6,new Color(122, 199, 255),list1);

        wt.insertContext("年度各分行资产组合财务净值规模为:", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertCommonTable(new String[]{"测试代码","测试名称","行为代码"},6,new Color(122, 199, 255),list1);



        wt.insertContext("月度明细如下：\n", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[1], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<全行托管费用收入月度情况分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[2], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<全行银行费用收入月度情况分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[3], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<全行资金沉淀收入月度情况分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[4], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<全行客户数月度情况分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[5], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<全行产品数量月度情况分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[6], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);


        wt.insertContext("<全行资产组合财务净值规模月度情况分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[7], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<全行资源投入月度情况分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);

        wt.insertContext("各分行年度分行业绩指数排名如下:", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertCommonTable(new String[]{"测试代码","测试名称","行为代码"},6,new Color(122, 199, 255),list1);


        wt.insertContext("排名前五的分行为（"+reportEntity.getRinking5Top()+"），排名后五的分行为（"+reportEntity.getRinking5Last()+"）。", fontSize, Font.NORMAL, Element.ALIGN_LEFT);


        wt.insertContext("分行业绩分析如下：\n", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[8], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<分行托管费收入贡献分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[9], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<分行银行费收入贡献分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[10], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<分行资金沉淀收入贡献分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[11], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<分行客户数贡献分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[12], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<分行产品数贡献分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg(pngArray[13], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<分行资产组合财务净值贡献分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);

        wt.insertImg(pngArray[14], Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);

        wt.insertContext("<分行资源投入贡献分析>", fontSize, Font.NORMAL, Element.ALIGN_LEFT);



        wt.insertContext("总结&建议：\n", fontSize, Font.NORMAL, Element.ALIGN_LEFT);

        wt.insertContext("分行业绩指标计算规则为：100*托管费用收入排名百分比+100*银行费用收入排名百分比+100*资金沉淀收益排名百分比+100*客户数量排名百分比+100*产品数量排名百分比+100*资产规模排名百分比+100*建行规模占比排名百分比+100*分行资源投入排名百分比\n", fontSize, Font.NORMAL, Element.ALIGN_LEFT);


        wt.closeDocument();
        return  fileName;


    }


    public static void main(String[] args) throws DocumentException, IOException {
        for (int i = 0; i < 15; i++) {
            sourcePath[i]="earth.png";
        }
        System.out.println(genCenterReport(sourcePath,reportEntity1));

    }

}

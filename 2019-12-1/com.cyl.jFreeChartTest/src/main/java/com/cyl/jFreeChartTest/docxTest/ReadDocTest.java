package com.cyl.jFreeChartTest.docxTest;

public class ReadDocTest {
    public static void main(String [] args){
        ReadDoc rd = new ReadDoc();
        try {
            rd.testReadByDoc("C:\\work\\工作文件\\风险绩效需求文件\\单组合分析报告__2017-08-01～2017-08-31.doc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

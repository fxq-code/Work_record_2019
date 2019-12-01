package org.cboard.genWord.CoreEngine.templete;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

import  java.lang.StackTraceElement.*;
import org.cboard.genWord.CoreEngine.entity.ColloPriceEntity;
import org.cboard.genWord.genImg.utils.BigDecimalUtils;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;



/**
 * Created by chenf on 2019/11/18.
 */
public class WordTemplete {



    private Document document;
    private BaseFont bfChinese;
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

    public WordTemplete(){
        this.document = new Document(PageSize.A4);

    }
    /**
     * @param filePath 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
     * @throws DocumentException
     * @throws IOException
     */
    public void openDocument(String filePath) throws DocumentException,
            IOException {
//       建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
        RtfWriter2.getInstance(this.document, new FileOutputStream(filePath));
        this.document.open();
//       设置中文字体
        //this.bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        this.bfChinese=BaseFont.createFont();
    }

    /**
     * @param titleStr 标题
     * @param fontsize 字体大小
     * @param fontStyle 字体样式
     * @param elementAlign 对齐方式
     * @throws DocumentException
     */
    public void insertTitle(String titleStr,int fontsize,int fontStyle,int elementAlign) throws DocumentException{
        Font titleFont = new Font(this.bfChinese, fontsize, fontStyle);
        Paragraph title = new Paragraph(titleStr);
        // 设置标题格式对齐方式
        title.setAlignment(elementAlign);
        title.setFont(titleFont);

        this.document.add(title);
    }

    /**
     * @param contextStr 内容
     * @param fontsize 字体大小
     * @param fontStyle 字体样式
     * @param elementAlign 对齐方式
     * @throws DocumentException
     */
    public void insertContext(String contextStr,int fontsize,int fontStyle,int elementAlign) throws DocumentException{
        // 正文字体风格
        Font contextFont = new Font(bfChinese, fontsize, fontStyle);
        Paragraph context = new Paragraph(contextStr);
        //设置行距
        //context.setLeading(10f);
        // 正文格式左对齐
        context.setAlignment(elementAlign);
        context.setFont(contextFont);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(0);
        // 设置第一行空的列数
        context.setFirstLineIndent(0);
        document.add(context);
    }
    /*
     * 测试清单
     * */
    public  void insertRiskTable() throws DocumentException{
        Table aTable = new Table(6,3);
        int width[] = { 10, 40, 17, 13, 10, 10 };
        aTable.setWidths(width);// 设置每列所占比例
        aTable.setWidth(100); // 占页面宽度 90%
        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setBorderWidth(0); // 边框宽度
        aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色
        aTable.setPadding(2);// 衬距，看效果就知道什么意思了
        aTable.setSpacing(3);// 即单元格之间的间距
        aTable.setBorder(2);// 边框

        Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
        Cell cell = new Cell(new Phrase("\n测试代码\n", fontChinese));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(new Color(0, 0, 0));
        cell.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell);

        Cell cell1 = new Cell(new Phrase("测试名称", fontChinese));
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBorderColor(new Color(0, 0, 0));
        cell1.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell1);

        Cell cell2 = new Cell(new Phrase("测试发生可能性", fontChinese));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBorderColor(new Color(0, 0, 0));
        cell2.setBackgroundColor(new Color(255, 255, 0));
        aTable.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("测试损失度", fontChinese));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorderColor(new Color(0, 0, 0));
        cell3.setBackgroundColor(new Color(255, 255, 0));
        aTable.addCell(cell3);

        Cell cell4 = new Cell(new Phrase("测试水平", fontChinese));
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setBorderColor(new Color(0, 0, 0));
        cell4.setBackgroundColor(new Color(255, 255, 0));
        aTable.addCell(cell4);

        Cell cell5 = new Cell(new Phrase("测试等级", fontChinese));
        cell5.setVerticalAlignment(Element.ALIGN_CENTER);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setBorderColor(new Color(0, 0, 0));
        cell5.setBackgroundColor(new Color(255, 255, 0));
        aTable.addCell(cell5);

        for(int i=0;i<12;i++){
            aTable.addCell(new Cell(i+""));
        }
        document.add(aTable);
        document.add(new Paragraph("\n"));
    }
    /*
     * 现状评估
     * */
    public void insertRiskEvaluationTable() throws DocumentException{
        Table aTable = new Table(12,4);
        int width1[] = { 5, 20, 5, 20, 5, 5, 5, 5, 5, 5, 5, 5};
        aTable.setWidths(width1);// 设置每列所占比例
        aTable.setWidth(100); // 占页面宽度 90%
        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setBorderWidth(0); // 边框宽度
        aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色

        Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
        Cell cell = new Cell(new Phrase("\n测试代码\n", fontChinese));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRowspan(2);
        cell.setBorderColor(new Color(0, 0, 0));
        cell.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell);

        Cell cell2 = new Cell(new Phrase("测试名称", fontChinese));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setRowspan(2);
        cell2.setBorderColor(new Color(0, 0, 0));
        cell2.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("行为代码", fontChinese));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setRowspan(2);
        cell3.setBorderColor(new Color(0, 0, 0));
        cell3.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell3);

        Cell cell4 = new Cell(new Phrase("引发测试的行为", fontChinese));
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setRowspan(2);
        cell4.setBorderColor(new Color(0, 0, 0));
        cell4.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell4);

        Cell cell5 = new Cell(new Phrase("控制现状", fontChinese));
        cell5.setVerticalAlignment(Element.ALIGN_CENTER);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setColspan(8);
        cell5.setBorderColor(new Color(0, 0, 0));
        cell5.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell5);

        Cell cell6 = new Cell(new Phrase("部门内审查", fontChinese));
        cell6.setVerticalAlignment(Element.ALIGN_CENTER);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell6.setBorderColor(new Color(0, 0, 0));
        cell6.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell6);

        Cell cell7 = new Cell(new Phrase("测试意识", fontChinese));
        cell7.setVerticalAlignment(Element.ALIGN_CENTER);
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setBorderColor(new Color(0, 0, 0));
        cell7.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell7);

        Cell cell8 = new Cell(new Phrase("过程监控", fontChinese));
        cell8.setVerticalAlignment(Element.ALIGN_CENTER);
        cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell8.setBorderColor(new Color(0, 0, 0));
        cell8.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell8);

        Cell cell9 = new Cell(new Phrase("奖惩机制", fontChinese));
        cell9.setVerticalAlignment(Element.ALIGN_CENTER);
        cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell9.setBorderColor(new Color(0, 0, 0));
        cell9.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell9);

        Cell cell10 = new Cell(new Phrase("明确责权", fontChinese));
        cell10.setVerticalAlignment(Element.ALIGN_CENTER);
        cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell10.setBorderColor(new Color(0, 0, 0));
        cell10.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell10);

        Cell cell11 = new Cell(new Phrase("执行者能力要求", fontChinese));
        cell11.setVerticalAlignment(Element.ALIGN_CENTER);
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setBorderColor(new Color(0, 0, 0));
        cell11.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell11);

        Cell cell12 = new Cell(new Phrase("专业审查", fontChinese));
        cell12.setVerticalAlignment(Element.ALIGN_CENTER);
        cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell12.setBorderColor(new Color(0, 0, 0));
        cell12.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell12);

        Cell cell13 = new Cell(new Phrase("资源配置", fontChinese));
        cell13.setVerticalAlignment(Element.ALIGN_CENTER);
        cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell13.setBorderColor(new Color(0, 0, 0));
        cell13.setBackgroundColor(new Color(204, 255, 255));
        aTable.addCell(cell13);

        for(int i=0;i<24;i++){
            aTable.addCell(new Cell(i+""));
        }

        document.add(aTable);
        document.add(new Paragraph("\n"));
    }

    /**
     * 拿到一个实体类的对象，将这个实体类中所有属性的值取出来，以一个字符串的形式去返回
     * */
    public  String getStringValue(Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        StringBuilder value=new StringBuilder();
        //不需要的自己去掉即可
        if (object != null ) {//if (object!=null )  ----begin
            // 拿到该类
            Class<?> clz = object.getClass();
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();

            value.append(fields.length+"#");
            for (Field field : fields) {// --for() begin
                //System.out.println(field.getGenericType());//打印该类的所有属性类型

                // 如果类型是String
                if (field.getGenericType().toString().equals(
                        "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    // 拿到该属性的gettet方法
                    /**
                     * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
                     * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
                     * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
                     */
                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));

                    String val = (String) m.invoke(object);// 调用getter方法获取属性值
                    if (val != null) {
                       // System.out.println("String type:" + val);
                        value.append(val+"@");
                    }

                }

                // 如果类型是Integer
                if (field.getGenericType().toString().equals(
                        "class java.lang.Integer")) {
                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Integer val = (Integer) m.invoke(object);
                    if (val != null) {
                        System.out.println("Integer type:" + val);
                        value.append(val+"@");
                    }

                }

                // 如果类型是Double
                if (field.getGenericType().toString().equals(
                        "class java.lang.Double")) {
                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Double val = (Double) m.invoke(object);
                    if (val != null) {
                        System.out.println("Double type:" + val);
                        value.append(val+"@");
                    }

                }

                // 如果类型是Boolean 是封装类
                if (field.getGenericType().toString().equals(
                        "class java.lang.Boolean")) {
                    Method m = (Method) object.getClass().getMethod(
                            field.getName());
                    Boolean val = (Boolean) m.invoke(object);
                    if (val != null) {
                        System.out.println("Boolean type:" + val);
                        value.append(val+"@");
                    }

                }

                // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
                // 反射找不到getter的具体名
                if (field.getGenericType().toString().equals("boolean")) {
                    Method m = (Method) object.getClass().getMethod(
                            field.getName());
                    Boolean val = (Boolean) m.invoke(object);
                    if (val != null) {
                        System.out.println("boolean type:" + val);
                        value.append(val+"@");
                    }

                }
                // 如果类型是Date
                if (field.getGenericType().toString().equals(
                        "class java.util.Date")) {
                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Date val = (Date) m.invoke(object);
                    if (val != null) {
                        System.out.println("Date type:" + val);
                        value.append(val+"@");
                    }

                }
                // 如果类型是Short
                if (field.getGenericType().toString().equals(
                        "class java.lang.Short")) {
                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(object);
                    if (val != null) {
                        System.out.println("Short type:" + val);
                        value.append(val+"@");
                    }

                }


            }//for() --end


        }//if (object!=null )  ----end
        return  value.append("^").toString();
    }

    private static String getMethodName(String fildeName){
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

//    public void insertDepartTable(String[] titils,String[] rowTitls,int lineNum,ArrayList arrValue)throws DocumentException{
//        Table aTable = new Table(titils.length,lineNum);
//
//        aTable.setOffset(0);
//        aTable.setWidth(100); // 占页面宽度 90%
//        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
//        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
//        aTable.setAutoFillEmptyCells(true); // 自动填满
//        aTable.setBorderWidth(0); // 边框宽度
//        aTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色
//
//        /**
//         * 循环添加表头
//         * */
//        for (int i = 0; i < titils.length; i++) {
//            Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
//            Cell cell = new Cell(new Phrase(titils[i].split("-")[0], fontChinese));
//            cell.setVerticalAlignment(Element.ALIGN_CENTER);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorderColor(new Color(0, 0, 0));
//            cell.setBackgroundColor(new Color(122, 199, 255));
//            aTable.addCell(cell);
//        }
//
//        if(arrValue.size()>1){
//            try {
//
//                for(int i=0;i<arrValue.size();i++){
//                    //转换成为key
//                    HashMap map= (HashMap) arrValue.get(i);
//                    //System.out.println(map);
//                    for (int j = 0; j < titils.length; j++) {
//                        if(j==0){
//
//
//                        }else{
//                            Object object=map.get(titils[j].split("-")[1]);
//                            if(object instanceof  String){
//                                aTable.addCell(new Cell(object+""));
//                            }else if(object instanceof BigDecimal){
//                                DecimalFormat df=new DecimalFormat("#,###,###,###,###,###,###.00");
//                                aTable.addCell(new Cell(df.format(object)));
//                            }else{
//
//                                aTable.addCell(new Cell(BigDecimalUtils.convertPlainString(object)));
//                            }
//                        }
//
//
//                    }
//
//                }
//
//                document.add(aTable);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        }else{
//            System.out.println("值列表arrValues 中没有数据，生成表数据失败！");
//        }
//
//
//
//    }

    public  void insertCommonTable(String []arr,int lineNum, Color color, ArrayList arrValue) throws DocumentException{

        Table aTable = new Table(arr.length,lineNum);

        aTable.setOffset(0);
//        int width[] = { 5, 13, 5, 9, 9, 13, 9, 9, 9, 9, 9 };
//        aTable.setWidths(width);// 设置每列所占比例
        aTable.setWidth(100); // 占页面宽度 90%
        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setBorderWidth(0); // 边框宽度
        aTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色

        /**
         * 循环添加表头
         * */
        for (int i = 0; i < arr.length; i++) {
            Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
            Cell cell = new Cell(new Phrase(arr[i].split("-")[0], fontChinese));
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(new Color(0, 0, 0));
            cell.setBackgroundColor(color);
            aTable.addCell(cell);
        }


        if(arrValue.size()>1){
            try {

                for(int i=0;i<arrValue.size();i++){
                    //转换成为key
                    HashMap map= (HashMap) arrValue.get(i);
                    //System.out.println(map);
                    for (int j = 0; j < arr.length; j++) {
                        Object object=map.get(arr[j].split("-")[1]);
                        if(object instanceof  String){
                            aTable.addCell(new Cell(object+""));
                        }else if(object instanceof BigDecimal){
                            DecimalFormat df=new DecimalFormat("#,###,###,###,###,###,###.00");
                            aTable.addCell(new Cell(df.format(object)));
                        }else{

                            aTable.addCell(new Cell(BigDecimalUtils.convertPlainString(object)));
                        }

                    }
//                    //int filedNum=map.keySet().size();
//                    Object[] values=map.values().toArray();
//                    //System.out.println("valuses="+values.length);
//                    for (int j = 0; j <values.length ; j++) {
//                       aTable.addCell(new Cell(values[j]+""));
//                    }
                }

                document.add(aTable);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }else{
            System.out.println("值列表arrValues 中没有数据，生成表数据失败！");
        }



    }

    public  void insertCommonTableByEntity(String []arr,int lineNum, Color color, ArrayList arrValue) throws DocumentException{

        Table aTable = new Table(arr.length,lineNum);
//        int width[] = { 5, 13, 5, 9, 9, 13, 9, 9, 9, 9, 9 };
//        aTable.setWidths(width);// 设置每列所占比例
        aTable.setWidth(100); // 占页面宽度 90%
        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setBorderWidth(0); // 边框宽度
        aTable.setBorderColor(new Color(0, 0, 0)); // 边框颜色

        /**
         * 循环添加表头
         * */
        for (int i = 0; i < arr.length; i++) {
            Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
            Cell cell = new Cell(new Phrase(arr[i], fontChinese));
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(new Color(0, 0, 0));
            cell.setBackgroundColor(color);
            aTable.addCell(cell);
        }

        //System.out.println(arrValue.toArray().length);
        //Objects[] objects = (Objects[]) arrValue.toArray();
        if(arrValue.size()>1){
            try {
                int filedNum=Integer.parseInt(getStringValue((Object)arrValue.get(0)).split("#")[0]);
//                String []values1=getStringValue((Object)arrValue.get(0)).split("#")[1].split("@");
//                System.out.println(filedNum+" "+values1);

                for(int i=0;i<arrValue.size();i++){
                    //aTable.addCell(new Cell(arrValue.get(i)+""));
                    String []values=getStringValue((Object)arrValue.get(i)).split("#")[1].split("@");
                    //System.out.println("valuses="+values.length);
                    for (int j = 0; j <values.length-1 ; j++) {
                        aTable.addCell(new Cell(values[j]+""));
                    }
                }

                document.add(aTable);

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }


        }else{
            System.out.println("值列表arrValues 中没有数据，生成表数据失败！");
        }




    }

    /*
     * 测试控制清单
     * */
    public  void insertRiskControlTable() throws DocumentException{
        Table aTable = new Table(11,3);
        int width[] = { 5, 13, 5, 9, 9, 13, 9, 9, 9, 9, 9 };
        aTable.setWidths(width);// 设置每列所占比例
        aTable.setWidth(100); // 占页面宽度 90%
        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setBorderWidth(0); // 边框宽度
        aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色

        Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
        Cell cell = new Cell(new Phrase("\n测试代码\n", fontChinese));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(new Color(0, 0, 0));
        cell.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell);

        Cell cell1 = new Cell(new Phrase("测试名称", fontChinese));
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBorderColor(new Color(0, 0, 0));
        cell1.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell1);

        Cell cell2 = new Cell(new Phrase("行为代码", fontChinese));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBorderColor(new Color(0, 0, 0));
        cell2.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("引发测试的行为", fontChinese));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setBorderColor(new Color(0, 0, 0));
        cell3.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell3);

        Cell cell4 = new Cell(new Phrase("测试控制态度", fontChinese));
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setBorderColor(new Color(0, 0, 0));
        cell4.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell4);

        Cell cell5 = new Cell(new Phrase("控制措施", fontChinese));
        cell5.setVerticalAlignment(Element.ALIGN_CENTER);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setBorderColor(new Color(0, 0, 0));
        cell5.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell5);

        Cell cell6 = new Cell(new Phrase("措施类型", fontChinese));
        cell6.setVerticalAlignment(Element.ALIGN_CENTER);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell6.setBorderColor(new Color(0, 0, 0));
        cell6.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell6);

        Cell cell7 = new Cell(new Phrase("完成标志", fontChinese));
        cell7.setVerticalAlignment(Element.ALIGN_CENTER);
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setBorderColor(new Color(0, 0, 0));
        cell7.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell7);

        Cell cell8 = new Cell(new Phrase("控制措施完成时间", fontChinese));
        cell8.setVerticalAlignment(Element.ALIGN_CENTER);
        cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell8.setBorderColor(new Color(0, 0, 0));
        cell8.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell8);

        Cell cell9 = new Cell(new Phrase("控制措施牵头部门", fontChinese));
        cell9.setVerticalAlignment(Element.ALIGN_CENTER);
        cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell9.setBorderColor(new Color(0, 0, 0));
        cell9.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell9);

        Cell cell10 = new Cell(new Phrase("控制措施配合部门", fontChinese));
        cell10.setVerticalAlignment(Element.ALIGN_CENTER);
        cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell10.setBorderColor(new Color(0, 0, 0));
        cell10.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell10);

        for(int i=0;i<22;i++){
            aTable.addCell(new Cell(i+""));
        }

        document.add(aTable);
        document.add(new Paragraph("\n"));

    }

    /**
     * @param imgUrl 图片路径
     * @param imageAlign 显示位置
     * @param height 显示高度
     * @param weight 显示宽度
     * @param percent 显示比例
     * @param heightPercent 显示高度比例
     * @param weightPercent 显示宽度比例
     * @param rotation 显示图片旋转角度
     * @throws MalformedURLException
     * @throws IOException
     * @throws DocumentException
     */
    public void insertImg(String imgUrl,int imageAlign,int height,int weight,int percent,int heightPercent,int weightPercent,int rotation) throws MalformedURLException, IOException, DocumentException{
//       添加图片
        Image img = Image.getInstance(imgUrl);
        if(img==null)
            return;
        img.setAbsolutePosition(0, 0);
        img.setAlignment(imageAlign);
        img.scaleAbsolute(height, weight);
        img.scalePercent(percent);
        img.scalePercent(heightPercent, weightPercent);
        img.setRotation(rotation);


        document.add(img);
    }

    public void closeDocument() throws DocumentException{
        this.document.close();
    }

    public static void main(String[] args) throws DocumentException, IOException {
        WordTemplete wt = new WordTemplete();
        wt.openDocument("d:\\dome1.doc");
        wt.insertTitle("一、测试基本情况", 12, Font.BOLD, Element.ALIGN_CENTER);

        wt.insertContext("共识别出XXX个测试，XXX项测试行为，其中，违规类测试XX项，占测试总量的XX%，违约类测试XX项，占测试总量的XX%，侵权类测试XX项，占测试总量的XX%，怠于类测试XX项，占测试总量的XX%，不当类测试XX项，占测试总量的XX%。", 12, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertContext("根据测试测评结果，各等级测试数量及所占百分比分别为：一级测试共XX项，占测试总量的XX%；二级测试共XX项，占测试总量的XX%；三级测试共XX项，占测试总量的XX%；四级测试共XX项，占测试总量的XX%；五级测试共XX项，占测试总量的XX%。\n\n", 12, Font.NORMAL, Element.ALIGN_LEFT);

        wt.insertContext("测试定向分析结果如下：", 12, Font.NORMAL, Element.ALIGN_LEFT);

        wt.insertContext("① 部门角度测试分析", 12, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg("earth.png", Image.ALIGN_CENTER, 12, 35, 50, 50, 50, 30);
        wt.insertContext("② 主体角度测试分析", 12, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg("earth.png", Image.ALIGN_CENTER, 12, 35, 50, 60, 60, 30);
        wt.insertContext("③ 部门主体交叉角度测试分析", 12, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg("earth.png", Image.ALIGN_CENTER, 50, 75, 100, 100, 100, 30);
        wt.insertContext("④ 业务活动角度测试分析", 12, Font.NORMAL, Element.ALIGN_LEFT);
        wt.insertImg("earth.png", Image.ALIGN_CENTER, 12, 35, 50, 80, 80, 30);

        wt.insertTitle("二、重大测试清单", 12, Font.BOLD, Element.ALIGN_CENTER);
        wt.insertRiskTable();
        wt.insertTitle("三、测试控制现状评估结果", 12, Font.BOLD, Element.ALIGN_CENTER);
        wt.insertRiskEvaluationTable();
        wt.insertTitle("四、测试控制计划", 12, Font.BOLD, Element.ALIGN_CENTER);
        wt.insertRiskControlTable();
        wt.closeDocument();
    }

}

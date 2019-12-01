package org.cboard.genWord.WordTemplete;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.finders.RangeFinder;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by chenf on 2019/12/1.
 */
public class Doc4jWordHandler {


    public  static boolean validateReplaceMark(String docxPobjTxt){
        if(null == docxPobjTxt || 1> docxPobjTxt.length()){
            return false;
        }
        String pattern = "^[(（\\()]{1}[a-zA-Z]{1,}[_0-9a-zA-z]{0,}[(）\\))]{1}$";
        Pattern compile = Pattern.compile(pattern);
        return compile.matcher(docxPobjTxt).matches();
    }

    public static String transEnglishSymbol(String oringnal){
        if(null == oringnal || 0 == oringnal.length()){
            return null;
        }
        if(oringnal.indexOf("（") > -1 ){
            oringnal = oringnal.replace("（", "(");
        }
        if(oringnal.indexOf("）") > -1 ){
            oringnal = oringnal.replace("）", ")");
        }
        return oringnal;
    }


    public static void replaceText(WordprocessingMLPackage template, Map<String, String> markTxtValMap){
        // 1. get the paragraph
        List<Object> paragraphs = WordTemplate.getAllElementFromObject(template.getMainDocumentPart(), P.class);
        String mapKey = "";
        for (Object p : paragraphs) {
            //System.out.println("通过P方式读取 :"+p.toString());
            P ogrignal = (P) p;
            String orignalStr = p.toString().trim();
            //筛选处要替换的地方格式:\\(a-zA-z[0-9]+\\)
            if((orignalStr.indexOf("(") != -1 || orignalStr.indexOf("（") != -1 ) &&
                    (orignalStr.indexOf(")") != -1 || orignalStr.indexOf("）") != -1 )){
                StringBuffer replaceTxt = null;
                //word文字的格式直接满足
                if(validateReplaceMark(orignalStr)){
                    orignalStr = transEnglishSymbol(orignalStr);
                    mapKey = orignalStr.substring(orignalStr.indexOf("(")+ 1 , orignalStr.indexOf(")"));
                    if(!markTxtValMap.containsKey(mapKey)){
                        continue;
                    }
                    replaceTxt = new StringBuffer(markTxtValMap.get(mapKey));
                    //直接替换
                    //P copyInfo = (P)XmlUtils.deepCopy(ogrignal);
                    List<Object> texts = WordTemplate.getAllElementFromObject(ogrignal, Text.class);
                    for (int i = 0; i < texts.size(); i++) {
                        Text text = (Text) texts.get(i);
                        if(i == 0 ){
                            text.setValue(replaceTxt.toString());
                        }else{
                            text.setValue("");
                        }
                    }

                }else{ //若还带有其他文字穿插  类似这种"WWW（name）22Er"
                    //截取出来
                    orignalStr = transEnglishSymbol(orignalStr);
                    String replaceKey = orignalStr.substring(orignalStr.indexOf("("), orignalStr.indexOf(")") + 1);
                    mapKey = orignalStr.substring(orignalStr.indexOf("(")+ 1 , orignalStr.indexOf(")"));
                    if(!markTxtValMap.containsKey(mapKey)){
                        continue;
                    }
                    String newReplaceVal = orignalStr.replace(replaceKey, markTxtValMap.get(mapKey));
                    //直接替换
                    //P copyInfo = (P)XmlUtils.deepCopy(ogrignal);
                    List<Object> texts = WordTemplate.getAllElementFromObject(ogrignal, Text.class);

                    for (int i = 0; i < texts.size(); i++) {
                        Text text = (Text) texts.get(i);
                        if(i == 0 ){
                            text.setValue(newReplaceVal);
                        }else{
                            text.setValue("");
                        }
                    }
                }
            }
        }
    }





    /**
     *
     * @param tempTable
     *            要替换修改的table
     * @param templateRowNum
     *            以第几行作为模板行，默认为 第二行，默认值为2
     * @param jsonArray
     *            要填充的数据
     */
    public static void fillTables(Tbl tempTable, int templateRowNum, List<HashMap> jsonArray) {
        // 获取表格的子元素
        List<Object> rows = WordTemplate.getAllElementFromObject(tempTable, Tr.class);
        int originRowsCount = rows.size();
        // 模板行的下标位置 默认第二行，对应数值下标1
        int tempLateIndex = 1;
        if (templateRowNum > 2) {
            tempLateIndex = templateRowNum - 1;
        }
        Tr templateRow = (Tr) rows.get(tempLateIndex);

        for (HashMap map : jsonArray) {
           // JSONObject jsonObj = (JSONObject) jObj;
            Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
            List<?> textElements = WordTemplate.getAllElementFromObject(workingRow, Text.class);
            for (Object textElment : textElements) {
                Text text = (Text) textElment;
                text.setValue(map.get(text.getValue())+"");
            }
            tempTable.getContent().add(workingRow);// 添加拼接的行数
        }
        tempTable.getContent().remove(templateRow);// 删掉模板行

        // 判断是否要处理最后一行（一般为合计的项）
//        if (isDealEndRow) {
//            Tr templateEndRow = (Tr) rows.get(originRowsCount - 1);
//            Tr workingEndRow = (Tr) XmlUtils.deepCopy(templateEndRow);
//            List<?> endTextElements = WordTemplate.getAllElementFromObject(workingEndRow, Text.class);
//            for (Object textElment : endTextElements) {
//                Text text = (Text) textElment;
//                String trColsVal = endRowData.getString(text.getValue());
//                if (null != trColsVal && !"".equals(trColsVal)) {
//                    text.setValue(trColsVal);
//                }
//            }
//            tempTable.getContent().add(workingEndRow);// 添加拼接的行数
//            tempTable.getContent().remove(templateEndRow);// 删掉模板行
//        }

    }



    /**
     *
     * @param tempTable
     *            要替换修改的table
     * @param templateRowNum
     *            以第几行作为模板行，默认为 第二行，默认值为2
     * @param jsonArray
     *            要填充的数据
     * @param isDealEndRow
     *            是否需要处理最后一行：有时需要处理最后一行（一般为汇总）
     * @param endRowData
     *            填充最后一行的数据
     */
    public static void addDocTableRows(Tbl tempTable, int templateRowNum, JSONArray jsonArray, Boolean isDealEndRow,
                                       JSONObject endRowData) {
        // 获取表格的子元素
        List<Object> rows = WordTemplate.getAllElementFromObject(tempTable, Tr.class);
        int originRowsCount = rows.size();
        // 模板行的下标位置 默认第二行，对应数值下标1
        int tempLateIndex = 1;
        if (templateRowNum > 2) {
            tempLateIndex = templateRowNum - 1;
        }
        Tr templateRow = (Tr) rows.get(tempLateIndex);

        for (Object jObj : jsonArray) {
            JSONObject jsonObj = (JSONObject) jObj;
            Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
            List<?> textElements = WordTemplate.getAllElementFromObject(workingRow, Text.class);
            for (Object textElment : textElements) {
                Text text = (Text) textElment;
                text.setValue(jsonObj.getString(text.getValue()));
            }
            tempTable.getContent().add(workingRow);// 添加拼接的行数
        }
        tempTable.getContent().remove(templateRow);// 删掉模板行

        // 判断是否要处理最后一行（一般为合计的项）
        if (isDealEndRow) {
            Tr templateEndRow = (Tr) rows.get(originRowsCount - 1);
            Tr workingEndRow = (Tr) XmlUtils.deepCopy(templateEndRow);
            List<?> endTextElements = WordTemplate.getAllElementFromObject(workingEndRow, Text.class);
            for (Object textElment : endTextElements) {
                Text text = (Text) textElment;
                String trColsVal = endRowData.getString(text.getValue());
                if (null != trColsVal && !"".equals(trColsVal)) {
                    text.setValue(trColsVal);
                }
            }
            tempTable.getContent().add(workingEndRow);// 添加拼接的行数
            tempTable.getContent().remove(templateEndRow);// 删掉模板行
        }

    }

    /**
     * 在书签处插入图片
     *
     * @param template
     *            WordprocessingMLPackage对象
     * @param markAndImgroute
     *            书签名称-->图片路径
     * @throws Exception
     */
    public static void addImgOnMarkPosition(WordprocessingMLPackage template, Map<String, String> markAndImgroute)
            throws Exception {
        MainDocumentPart mainDocumentPart = template.getMainDocumentPart();
        // Get the live contents of this part (the JAXB object model).
        Document jaxbElement = mainDocumentPart.getJaxbElement();
        // Gets the value of the body property.
        Body body = jaxbElement.getBody();
        // 获取段落
        List<Object> paragraphs = body.getContent();
        // 提取书签并获取书签的游标,固定写法，可参考源码
        RangeFinder rt = new RangeFinder("CTBookmark", "CTMarkupRange");
        // 此方法用于将paragraphs中的书签遍历到rt对象中
        new TraversalUtil(paragraphs, rt);
        // 遍历书签
        for (CTBookmark bm : rt.getStarts()) {
            //System.out.println("bm.getName: " + bm.getName());
            String bookMarkName = bm.getName().toString().trim();
            if (!markAndImgroute.containsKey(bookMarkName)) {
                continue;
            }
            InputStream is = new FileInputStream(new File(markAndImgroute.get(bookMarkName)));
            byte[] byteArray = IOUtils.toByteArray(is);
            //Create an image part from the provided byte array
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(template, byteArray);
            //这个方法看源码还是不太理解 ,为保证图片实际大小，最后一个参数要大于等于图片的真实宽度
            Inline newInline = imagePart.createImageInline(null, null, 0,1, false, 10000);
            //以下为插入图片的实际操作
            P p = (P) bm.getParent();
            ObjectFactory objectFactory = new ObjectFactory();
            R run = objectFactory.createR();
            Drawing drawing = objectFactory.createDrawing();
            drawing.getAnchorOrInline().add(newInline);
            run.getContent().add(drawing);
            p.getContent().add(run);
        }
    }
    /**
     * 在指定的文字说明处插入图片
     *
     * @param template
     *            WordprocessingMLPackage对象
     * @param markAndImgroute
     *            文字名称-->图片路径
     * @throws Exception
     */
    public static void addImgOnTextPosition(WordprocessingMLPackage template, Map<String, String> markAndImgroute) throws Exception{
        List<Object> paragraphs = WordTemplate.getAllElementFromObject(template.getMainDocumentPart(), P.class);
        for (int i = 0; i < paragraphs.size(); i++) {
            P orignal = (P) paragraphs.get(i);
            String key = orignal.toString().trim();
            //若此处的word文字标识满足Map中的key值
            if (markAndImgroute.containsKey(key)) {//只支持替换处为单独的一行，若有多行请以表格的形式，插入处的标识占整个row
                //将原本的word中的文字标识清空
                List<Object> textElements = WordTemplate.getAllElementFromObject(orignal, Text.class);
                for (int j = 0; j < textElements.size(); j++) {
                    Text text = (Text) textElements.get(j);
                    text.setValue("");
                }
                //替换上图片
                String imgPath = markAndImgroute.get(key);
                InputStream is = new FileInputStream(new File(imgPath));
                byte[] byteArray = IOUtils.toByteArray(is);
                //Create an image part from the provided byte array
                BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(template, byteArray);
                //这个方法看源码还是不太理解 ,为保证图片实际大小，最后一个参数要大于等于图片的真实宽度
                Inline newInline = imagePart.createImageInline(null, null, 0,1, false,0);
                //以下为插入图片的实际操作
                //	P p = (P) getAllElementFromObject(textElement, P.class);
                ObjectFactory objectFactory = new ObjectFactory();
                R run = objectFactory.createR();
                Drawing drawing = objectFactory.createDrawing();
                drawing.getAnchorOrInline().add(newInline);
                run.getContent().add(drawing);
                orignal.getContent().add(run);

            }else{
                continue;
            }
        }
    }

}

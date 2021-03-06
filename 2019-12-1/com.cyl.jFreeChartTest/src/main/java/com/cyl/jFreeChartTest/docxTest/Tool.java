package com.cyl.jFreeChartTest.docxTest;

import org.apache.commons.io.IOUtils;
import org.docx4j.Docx4J;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.dml.Graphic;
import org.docx4j.dml.GraphicData;
import org.docx4j.dml.picture.Pic;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.*;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 模板word内容替换工具类
 */
public class Tool {

    /**
     * 在标签处插入替换内容
     *
     * @param bm
     * @param object
     * @throws Exception
     */
    public static void replaceText(CTBookmark bm, Object object) throws Exception {
        if (object == null) {
            return;
        }
        if (bm.getName() == null){
            return;
        }
        String value = object.toString();
        try {
            List<Object> theList = null;
            ParaRPr rpr = null;
            if (bm.getParent() instanceof P) {
                PPr pprTemp = ((P) (bm.getParent())).getPPr();
                if (pprTemp == null) {
                    rpr = null;
                } else {
                    rpr = ((P) (bm.getParent())).getPPr().getRPr();
                }
                theList = ((ContentAccessor) (bm.getParent())).getContent();
            } else {
                return;
            }
            int rangeStart = -1;
            int rangeEnd = -1;
            int i = 0;
            for (Object ox : theList) {
                Object listEntry = XmlUtils.unwrap(ox);
                if (listEntry.equals(bm)) {
                    if (((CTBookmark) listEntry).getName() != null) {
                        rangeStart = i + 1;
                    }
                } else if (listEntry instanceof CTMarkupRange) {
                    if (((CTMarkupRange) listEntry).getId().equals(bm.getId())) {
                        rangeEnd = i - 1;
                        break;
                    }
                }
                i++;
            }
            int x = i - 1;
            for (int j = x; j >= rangeStart; j--) {
                theList.remove(j);
            }

            ObjectFactory factory = Context.getWmlObjectFactory();
            org.docx4j.wml.R run = factory.createR();
            org.docx4j.wml.Text t = factory.createText();
            t.setValue(value);
            run.getContent().add(t);
            theList.add(rangeStart, run);
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }
    }

    /**
     * 替换image
     * @param wPackage
     * @param bm
     * @param file
     */
    public static void addImage(WordprocessingMLPackage wPackage, CTBookmark bm, String file){

        // 新增image
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = (P) (bm.getParent());
        R run = factory.createR();
        byte[] bytes ;
        Long newCx = 0L;
        Long newCy = 0L;
        String newRelId = null;
        try {
            bytes = IOUtils.toByteArray(new FileInputStream(file));
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wPackage,bytes);
            Inline newInline = imagePart.createImageInline(null, null, 0, 1, false,1000);

            // 获取CX 、CY
            newCx = newInline.getExtent().getCx();
            newCy = newInline.getExtent().getCy();

            // 获取relId
            Graphic newg = newInline.getGraphic();
            GraphicData graphicdata = newg.getGraphicData();
            Object o = graphicdata.getAny().get(0);
            Pic pic = (Pic)XmlUtils.unwrap(o);
            newRelId = pic.getBlipFill().getBlip().getEmbed();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取模板图片的CX 、CY 、relId
        Object parent = (P) bm.getParent();
        List<Object> rList = getAllElementFromObject(parent, R.class);
        for (Object robj: rList){
            if (robj instanceof R){
                R r = (R) robj;
                List<Object> drawList = getAllElementFromObject(r, Drawing.class);
                for (Object dobj: drawList){
                    if (dobj instanceof Drawing){
                        Drawing d = (Drawing) dobj;
                        Inline inline = (Inline)d.getAnchorOrInline().get(0);
                        // 获取CX 、CY
                        Long cx = inline.getExtent().getCx();
                        Long cy = inline.getExtent().getCy();

                        // 获取relId
                        Graphic g = inline.getGraphic();
                        GraphicData graphicdata = g.getGraphicData();
                        Object o = graphicdata.getAny().get(0);
                        Pic pic = (Pic)XmlUtils.unwrap(o);
                        String relId = pic.getBlipFill().getBlip().getEmbed();

                        // 替换relId
                        pic.getBlipFill().getBlip().setEmbed(newRelId);
                        // 处理CX、CY
                        Map<String,Long> map = dealCxy(cx, cy, newCx, newCy);

                        // 更改cx、cy
                        inline.getExtent().setCx(map.get("setCx"));
                        inline.getExtent().setCy(map.get("setCy"));

                    }
                }
            }
        }
    }

    /**
     * 处理图片适应大小
     * @param cx
     * @param cy
     * @param newCx
     * @param newCy
     */
    public static Map<String, Long> dealCxy(Long cx, Long cy, Long newCx, Long newCy){
        Map<String, Long> map = new HashMap<String, Long>();
        Long setCx;
        Long setCy;

        if (newCx > cx){
            if (newCy <= cy){
                setCx = cx;
                setCy = newCy/(newCx/cx);
            } else {
                if ((newCx/cx) > (newCy/cy)){
                    setCx = cx;
                    setCy = newCy/(newCx/cx);
                } else {
                    setCy = cy;
                    setCx = newCx/(newCy/cy);
                }
            }
        } else {   // newCx < cx
            if (newCy > cy) {
                setCx = cx;
                setCy = newCy * (cx/newCx);
            } else {
                if ((cx/newCx) > (cy/newCy)) {
                    setCx = cx;
                    setCy = newCy *(cx/newCx);
                } else {
                    setCy = cy;
                    setCx = newCy * (cy/newCy);
                }
            }
        }
        map.put("setCx",setCx);
        map.put("setCy",setCy);
        return map;
    }


    /**
     * 得到指定类型的元素
     * @param obj
     * @param toSearch
     * @return
     */
    public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement)
            obj = ((JAXBElement<?>) obj).getValue();
        if (obj.getClass().equals(toSearch))
            result.add(obj);
        else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }
        }
        return result;
    }

    /**
     * word 转换 pdf （实际是没有用到的）
     * @param docxPath
     * @param pdfPath
     * @throws Exception
     */
    public static void convertDocxToPDF(String docxPath, String pdfPath) throws Exception {
        OutputStream os = null;
        try {
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(new File(docxPath));
            Mapper fontMapper = new IdentityPlusMapper();
            fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
            fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
            fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
            mlPackage.setFontMapper(fontMapper);

            os = new java.io.FileOutputStream(pdfPath);

            FOSettings foSettings = Docx4J.createFOSettings();
            foSettings.setWmlPackage(mlPackage);
            Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 下载PDF文件（实际是没有用到的）
     * @param wordMLPackage
     * @param os
     * @throws Exception
     */
    public  void pdfFile(WordprocessingMLPackage wordMLPackage,OutputStream os) throws Exception {

        String regex = null;
        Mapper fontMapper = new IdentityPlusMapper();
        wordMLPackage.setFontMapper(fontMapper);
        PhysicalFont font = PhysicalFonts.get("Arial Unicode MS");
        String directory = "D:\\upload\\pdf";
        String fileName = "OUT_ConvertInXHTMLURL.pdf";
        File f = new File(directory,fileName);
        if(f.exists()) {
            // 文件已经存在，输出文件的相关信息
            System.out.println(f.getAbsolutePath());
            System.out.println(f.getName());
            System.out.println(f.length());
        } else {
            //  先创建文件所在的目录
            f.getParentFile().mkdirs();
        }
        File file = new File(directory+"/"+fileName);
        OutputStream os34 = new java.io.FileOutputStream(file);
        Docx4J.toPDF(wordMLPackage, os34);
        os.flush();
        os.close();
        wordMLPackage = null;
    }


}


package com.cyl.jFreeChartTest.docxTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.Docx4J;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.P;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;

import com.cyl.jFreeChartTest.docxTest.util.AssetsCategoryOfStock;
import com.cyl.jFreeChartTest.docxTest.util.DataSet;

public class WordTemplate {

	// 加载模板
	public static WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
		return template;
	}

	// 获取占位符
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

	// 替换占位符
	public static void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder) {
		List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);

		for (Object text : texts) {
			Text textElement = (Text) text;
			System.out.println(textElement.getValue());
			if (textElement.getValue().equals(placeholder)) {
				textElement.setValue(name);
			}
		}
	}

	// 替换占位符2，根据实际需求改写，这种方式更加符合逻辑
	public static void replacePlaceholder2(WordprocessingMLPackage template, String name, String placeholder) {
		// 获取doc的主要内容
		List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
		List<Object> mainDocP = getAllElementFromObject(template.getMainDocumentPart(), P.class);
		for (Object object : mainDocP) {
			P orignal = (P) object;
			String key = orignal.toString().trim();
			System.out.println("通过P类型来接收的结果：" + key);

		}
	}

	// 保存文档
	public static void writeDocxToStream(WordprocessingMLPackage template, String target) throws Exception {
		File f = new File(target);
		template.save(f);
		OutputStream os = new FileOutputStream(f);
		template.save(f);
		os.close();
		System.out.println("保存修改后的docx ：" + target);
	}

	/**
	 * docx文档转换为PDF
	 *
	 * @param mlPackage
	 *            docx文档
	 * @param os
	 *            PDF文档存储路径
	 * @throws Exception
	 *             可能为Docx4JException, FileNotFoundException, IOException等
	 */
	public static void convertDocxToPDF(WordprocessingMLPackage mlPackage, OutputStream os) throws Exception {
		try {
			Mapper fontMapper = new IdentityPlusMapper();
			fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
			fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
			fontMapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
			fontMapper.put("黑体", PhysicalFonts.get("SimHei"));
			fontMapper.put("楷体", PhysicalFonts.get("KaiTi"));
			fontMapper.put("新宋体", PhysicalFonts.get("NSimSun"));
			fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
			fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
			fontMapper.put("宋体扩展", PhysicalFonts.get("simsun-extB"));
			fontMapper.put("仿宋", PhysicalFonts.get("FangSong"));
			fontMapper.put("仿宋_GB2312", PhysicalFonts.get("FangSong_GB2312"));
			fontMapper.put("幼圆", PhysicalFonts.get("YouYuan"));
			fontMapper.put("华文宋体", PhysicalFonts.get("STSong"));
			fontMapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));

			mlPackage.setFontMapper(fontMapper);

			FOSettings foSettings = Docx4J.createFOSettings();
			foSettings.setWmlPackage(mlPackage);
			Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);// FLAG_EXPORT_PREFER_XSL

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

	
	public void replaceParagraph(String placeholder, String textToAdd, WordprocessingMLPackage template,
			ContentAccessor addTo) {
		// 1. get the paragraph
		List<Object> paragraphs = getAllElementFromObject(template.getMainDocumentPart(), P.class);

		P toReplace = null;
		for (Object p : paragraphs) {
			List<Object> texts = getAllElementFromObject(p, Text.class);
			for (Object t : texts) {
				Text content = (Text) t;
				if (content.getValue().equals(placeholder)) {
					toReplace = (P) p;
					break;
				}
			}
		}

		// we now have the paragraph that contains our placeholder: toReplace
		// 2. split into seperate lines
		String as[] = StringUtils.splitPreserveAllTokens(textToAdd, '\n');

		for (int i = 0; i < as.length; i++) {
			String ptext = as[i];

			// 3. copy the found paragraph to keep styling correct
			P copy = (P) XmlUtils.deepCopy(toReplace);

			// replace the text elements from the copy
			List<?> texts = getAllElementFromObject(copy, Text.class);
			if (texts.size() > 0) {
				Text textToReplace = (Text) texts.get(0);
				textToReplace.setValue(ptext);
			}

			// add the paragraph to the document
			addTo.getContent().add(copy);
		}

		// 4. remove the original one
		((ContentAccessor) toReplace.getParent()).getContent().remove(toReplace);

	}
	/**
	 * 
	 * @param placeholders
	 * @param textToAdd
	 * @param template
	 * @throws Docx4JException
	 * @throws JAXBException
	 */
	@SuppressWarnings("unused")
	public static void replaceTable(String[] placeholders, List<Map<String, String>> textToAdd,
			WordprocessingMLPackage template) throws Docx4JException, JAXBException {

		List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);
		// 1. find the table
		Tbl tempTable = getTemplateTable(tables, placeholders[0]);
		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);

		// first row is header, second row is content
		if (rows.size() == 2) {
			// this is our template row
			Tr templateRow = (Tr) rows.get(1);

			for (Map<String, String> replacements : textToAdd) {
				// 2 and 3 are done in this method
				addRowToTable(tempTable, templateRow, replacements);
			}

			// 4. remove the template row
			tempTable.getContent().remove(templateRow);
			tempTable.getContent().remove(templateRow);
		}
	}

	/**
	 * 获取docx中的table表格
	 * 
	 * @param tables docx中所有的表格元素
	 * @param templateKey 要寻找的表格中的关键性元素
	 * @return	匹配的table元素
	 * @throws Docx4JException
	 * @throws JAXBException
	 */
	public static Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
		for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
			Object tbl = iterator.next();
			List<?> textElements = getAllElementFromObject(tbl, Text.class);
			for (Object text : textElements) {
				Text textElement = (Text) text;
				System.out.println(textElement.getValue()+" "+textElement.getParent()+" "+textElement.getSpace()+" "+textElement.getClass().getName());
				if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
					return (Tbl) tbl;
			}
		}
		return null;
	}

	public static void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
		Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
		List<?> textElements = getAllElementFromObject(workingRow, Text.class);
		for (Object object : textElements) {
			Text text = (Text) object;
			String replacementValue = (String) replacements.get(text.getValue());
			if (replacementValue != null)
				text.setValue(replacementValue);
		}

		reviewtable.getContent().add(workingRow);
	}

	public static void main(String[] args) throws Exception {
		DataSet dataset = new DataSet();
		List<AssetsCategoryOfStock> demoList = dataset.getDemoList();
		String templatefile = "c:\\work\\workSystemFile\\demo4.docx";
		String target = "c:\\work\\workSystemFile\\target" + System.currentTimeMillis() + ".docx";
		WordprocessingMLPackage template = getTemplate(templatefile);
		List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);
		System.out.println("此doc一共有" + tables.size() + " 个表格！");
		//根据特殊标签获取要修改的表格
		Tbl tempTable = getTemplateTable(tables, "行业类别");
		//获取表格的子元素
		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);
		// first row is header, second row is content ，有合并单元格的情况，可能是2行，也可能是3行
		if (rows.size() == 2 || rows.size() == 3) {
			// this is our template row 最后一行作为要add的行的格式，以此为模板
			Tr templateRow = rows.size() == 2 ? (Tr) rows.get(1) :(Tr) rows.get(2);
			for (AssetsCategoryOfStock acos : demoList) {//有多少数据就增加多少行
				Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
				List<?> textElements = getAllElementFromObject(workingRow, Text.class);
				String[] elementVal = acos.elementVal();
				int index = 0 ;
				for (Object object : textElements) {
					Text text = (Text) object;
					if (elementVal[index] != null){//传输的数据长度要与行中的列数相对应
						text.setValue(elementVal[index]);
					}
					index ++;
				}
				tempTable.getContent().add(workingRow);//添加拼接的行数
			}
			tempTable.getContent().remove(templateRow);//删掉模板行
		}
		
		writeDocxToStream(template, target);
	}
}

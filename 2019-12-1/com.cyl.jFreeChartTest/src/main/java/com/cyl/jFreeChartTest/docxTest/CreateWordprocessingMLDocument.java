package com.cyl.jFreeChartTest.docxTest;


import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;


public class CreateWordprocessingMLDocument {
	public static void main(String[] args) throws Exception {	
		
		boolean save = true; 		
		WordprocessingMLPackage wordMLPackage =  
				  WordprocessingMLPackage.load(new java.io.File("E:\\bigData\\workSystemFile\\workSystemFile\\demo2.docx"));
		
		MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
		// Example 1: add text in Title style
		mdp.addStyledParagraphOfText("heading1", "Heading1");
		mdp.addStyledParagraphOfText("heading2", "Heading2");
		mdp.addStyledParagraphOfText("heading3", "Heading3");
		
	   	// Pretty print the main document part
		System.out.println(
				XmlUtils.marshaltoString(mdp.getJaxbElement(), true, true) );
		
		// Optionally save it
		if (save) {
			String filename = "E:\\bigData\\workSystemFile\\workSystemFile\\wh_out"+System.currentTimeMillis()+".docx";
			wordMLPackage.save(new java.io.File(filename) );
			System.out.println("Saved " + filename);
		}
						
	}


}

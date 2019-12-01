



package com.cyl.jFreeChartTest.jFreeChartSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class BarChartDemo2 extends ApplicationFrame
{

    public BarChartDemo2(String s)
    {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }
    /**
     * 分组数据，每组详细的数据
     * @return
     */
    private static CategoryDataset createDataset()
    {
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(-12D, "期末大类配置", "流动性资产");
        defaultcategorydataset.addValue(20D, "期间配置变动", "流动性资产");
        
        defaultcategorydataset.addValue(99D, "期末大类配置", "固定收益类");
        defaultcategorydataset.addValue(-2D, "期间配置变动", "固定收益类");
        
        defaultcategorydataset.addValue(5D, "期末大类配置", "权益类资产");
        defaultcategorydataset.addValue(-4D, "期间配置变动", "权益类资产");
        
        defaultcategorydataset.addValue(-2D, "期末大类配置", "其他");
        defaultcategorydataset.addValue(5D, "期间配置变动", "其他");
        
        return defaultcategorydataset;
    }

    private static JFreeChart createChart(CategoryDataset categorydataset)
    {
    	JFreeChart jfreechart = getCnChartObj(categorydataset);
    	jfreechart.setBackgroundPaint(Color.white);
        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
        //设置图表的背景色
        categoryplot.setBackgroundPaint(ChartColor.white);
        //設置图表边框颜色，与图表背景色一致便可隐藏图表边框
        //categoryplot.setOutlinePaint(Color.white);
        categoryplot.setRangeGridlinePaint(Color.white);
        categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        //numberaxis.setRange(-20D, 120D);
        numberaxis.setLowerBound(-20);
        numberaxis.setUpperBound(120);
        numberaxis.setAutoRange(false);
//        numberaxis.setAutoRangeMinimumSize(20);
//        numberaxis.setFixedDimension(20);
//        numberaxis.setAutoRange(true);
//        numberaxis.setLabelAngle(20);
        numberaxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());//暂时没有源码，不知道干嘛的，去掉也没影响
        BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
        barrenderer.setDrawBarOutline(true);
        return jfreechart;
    }

    public static JPanel createDemoPanel()
    {
        JFreeChart jfreechart = createChart(createDataset());
        BufferedImage createBufferedImage = jfreechart.createBufferedImage(100, 50);
        imgToDisk(createBufferedImage, jfreechart);
        
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[])
    {
       /* BarChartDemo2 barchartdemo2 = new BarChartDemo2("大类资产配置");
        barchartdemo2.pack();
        RefineryUtilities.centerFrameOnScreen(barchartdemo2);
        barchartdemo2.setVisible(true);*/
    	JFreeChart jfreechart = createChart(createDataset());
        BufferedImage createBufferedImage = jfreechart.createBufferedImage(600, 400);
        imgToDisk(createBufferedImage, jfreechart);
    }
    
    /**
     * 获取一个支持中文的jFreeChart对象
     * @param categorydataset CategoryDataset数据集对象
     * @return
     */
    public static JFreeChart getCnChartObj(CategoryDataset categorydataset){
    	StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
    	standardChartTheme.setExtraLargeFont(new Font("黑体",Font.BOLD,12));
    	standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,12));
    	standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,12));
    	ChartFactory.setChartTheme(standardChartTheme);
    	JFreeChart jfreeChart = ChartFactory.createBarChart("", "", "", categorydataset, PlotOrientation.HORIZONTAL, true, true, false);
    	return  jfreeChart;
    }
    
    
    public static void imgToDisk(BufferedImage createBufferedImage,JFreeChart jFreeChart) {
    	FileOutputStream fos = null;
    	String dateCur = Long.toString(System.currentTimeMillis());
    	int random =  (int) (Math.random()*5000);
    	String jpgName = dateCur + Integer.toString(random)+".jpg";
    	String path = "c:\\work\\workSystemFile\\assetCategories" + jpgName;
    	File file = null;
    	try {
        	file = new File(path);
			fos = new FileOutputStream(file);
			ChartUtilities.writeChartAsJPEG(fos, jFreeChart, 700, 300);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				System.out.println("图片生成完毕,图片路径:"+ path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
}

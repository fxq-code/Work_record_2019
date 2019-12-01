package com.cyl.jFreeChartTest.jFreeChartSource;

import java.awt.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
/**
 * 有两个标的
 * @author caoyulun
 *
 */
public class PriceVolumeDemo1 extends ApplicationFrame
{

    public PriceVolumeDemo1(String s) throws ParseException
    {
        super(s);
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartpanel);
    }

    private static JFreeChart createChart() throws ParseException
    {
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("黑体",Font.BOLD,14));
        standardChartTheme.setRegularFont(new Font("黑体",Font.BOLD,14));
        standardChartTheme.setLargeFont(new Font("黑体",Font.BOLD,14));
        ChartFactory.setChartTheme(standardChartTheme);
        
        XYDataset xydataset = createVolumeDataset();
        String s = "组合市值权重&沪深指数";
        JFreeChart jfreechart = ChartFactory.createXYAreaChart(s, "沪深300指数", "指数点位", xydataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        //设置y轴
        NumberAxis axis = (NumberAxis)xyplot.getRangeAxis();
        axis.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(1300);
        axis.setTickUnit(unit);
        axis.setAutoRange(false);
        axis.setRange(1500d, 8000d);
        //设置横坐标
        DateAxis dateaxis = new DateAxis("");
        //dateaxis.setLowerMargin(0.0D);
        //dateaxis.setUpperMargin(0.0D);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateaxis.setRange(simpleDateFormat.parse("2016-06-01"), simpleDateFormat.parse("2019-07-01"));
        dateaxis.setDateFormatOverride(simpleDateFormat);
        dateaxis.setAutoRange(false);
        dateaxis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH,2));
        xyplot.setDomainAxis(dateaxis);
        dateaxis.setVerticalTickLabels(true);

        //第二个标的信息
        NumberAxis numberaxis1 = new NumberAxis("市值权重(%)");
        //numberaxis1.setUpperMargin(1.0D);
        xyplot.setRangeAxis(1, numberaxis1);
        xyplot.setDataset(1, createPriceDataset());
        xyplot.mapDatasetToRangeAxis(1, 1);
        numberaxis1.setTickUnit(new NumberTickUnit(2d));
        numberaxis1.setAutoRange(false);
        numberaxis1.setRange(0d, 12d);
        numberaxis1.setVerticalTickLabels(true);
        numberaxis1.setLabelFont(new Font("黑体",Font.BOLD,14));
        numberaxis1.setTickLabelFont(new Font("黑体",Font.BOLD,14));
        jfreechart.getLegend().setItemFont(new Font("黑体",Font.BOLD,14));
        jfreechart.getTitle().setFont(new Font("黑体",Font.BOLD,14));
        DeviationRenderer xybarrenderer = new DeviationRenderer(true, false);
        xyplot.setRenderer(1, xybarrenderer);
        return jfreechart;
    }

    private static XYDataset createPriceDataset()
    {
        TimeSeries timeseries = new TimeSeries("社保101组合", org.jfree.data.time.Day.class);
        timeseries.add(new Day(27, 6, 2016), 6.5D);
        timeseries.add(new Day(3, 9, 2016), 6.57D);
        timeseries.add(new Day(4, 12, 2016), 7.5D);
        timeseries.add(new Day(7, 3, 2017), 7.2D);
        timeseries.add(new Day(8, 6, 2017), 5.0D);
        timeseries.add(new Day(9, 9, 2017), 6.5D);
        timeseries.add(new Day(10, 12, 2017), 7.3D);
        timeseries.add(new Day(11, 3, 2018), 8.0D);
        timeseries.add(new Day(14, 6, 2018), 8.5D);
        timeseries.add(new Day(15, 9, 2018), 9.12D);
        timeseries.add(new Day(16, 12, 2018), 8.0D);
        timeseries.add(new Day(17, 3, 2019), 10.3D);
        timeseries.add(new Day(18, 6, 2019), 11.0D);
        return new TimeSeriesCollection(timeseries);
    }

    private static IntervalXYDataset createVolumeDataset()
    {
        //
        TimeSeries timeseries = new TimeSeries("沪深300指数", org.jfree.data.time.Day.class);
        timeseries.add(new Day(27, 6, 2016), 4102D);
        timeseries.add(new Day(3, 9, 2016), 4558D);
        timeseries.add(new Day(4, 12, 2016), 3672D);
        timeseries.add(new Day(7, 3, 2017), 2366D);
        timeseries.add(new Day(8, 6, 2017), 3233D);
        timeseries.add(new Day(9, 9, 2017), 1678D);
        timeseries.add(new Day(10, 12, 2017), 1897D);
        timeseries.add(new Day(11, 3, 2018), 4489D);
        timeseries.add(new Day(14, 6, 2018), 2309D);
        timeseries.add(new Day(15, 9, 2018), 2786D);
        timeseries.add(new Day(16, 12, 2018), 3099D);
        timeseries.add(new Day(17, 3, 2019), 1789D);
        timeseries.add(new Day(18, 6, 2019), 1900D);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeseries);
        return timeseriescollection;
    }

    public static JPanel createDemoPanel() throws ParseException
    {
        JFreeChart jfreechart = createChart();
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[])
    {
        PriceVolumeDemo1 pricevolumedemo1;
		try {
			pricevolumedemo1 = new PriceVolumeDemo1("Price Volume Chart Demo");
			pricevolumedemo1.pack();
	        RefineryUtilities.centerFrameOnScreen(pricevolumedemo1);
	        pricevolumedemo1.setVisible(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
}

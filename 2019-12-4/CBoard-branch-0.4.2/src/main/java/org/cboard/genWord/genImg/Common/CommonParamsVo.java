package org.cboard.genWord.genImg.Common;

import java.util.Arrays;

/**
 * Created by chenf on 2019/12/3.
 */
public class CommonParamsVo {
    private String titile;
    private String subtitile;

    private String xFormatter;

    private Object[]xdatas;

    private String yFormatter;
    private Object[]ydatas;
//接下来都是 绘制pi的相关的图需要的数据

    private String Formatter;

    private Object[]piDatas;

    private Object[]ValuesDatas;

    private Object[] innerDatas;//内嵌环形图中需要的数据


    @Override
    public String toString() {
        return "CommonParamsVo{" +
                "titile='" + titile + '\'' +
                ", subtitile='" + subtitile + '\'' +
                ", xFormatter='" + xFormatter + '\'' +
                ", xdatas=" + Arrays.toString(xdatas) +
                ", yFormatter='" + yFormatter + '\'' +
                ", ydatas=" + Arrays.toString(ydatas) +
                ", Formatter='" + Formatter + '\'' +
                ", piDatas=" + Arrays.toString(piDatas) +
                ", ValuesDatas=" + Arrays.toString(ValuesDatas) +
                ", innerDatas=" + Arrays.toString(innerDatas) +
                '}';
    }

    public Object[] getValuesDatas() {
        return ValuesDatas;
    }

    public void setValuesDatas(Object[] valuesDatas) {
        ValuesDatas = valuesDatas;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getSubtitile() {
        return subtitile;
    }

    public void setSubtitile(String subtitile) {
        this.subtitile = subtitile;
    }

    public String getxFormatter() {
        return xFormatter;
    }

    public void setxFormatter(String xFormatter) {
        this.xFormatter = xFormatter;
    }

    public Object[] getXdatas() {
        return xdatas;
    }

    public void setXdatas(Object[] xdatas) {
        this.xdatas = xdatas;
    }

    public String getyFormatter() {
        return yFormatter;
    }

    public void setyFormatter(String yFormatter) {
        this.yFormatter = yFormatter;
    }

    public Object[] getYdatas() {
        return ydatas;
    }

    public void setYdatas(Object[] ydatas) {
        this.ydatas = ydatas;
    }

    public String getFormatter() {
        return Formatter;
    }

    public void setFormatter(String formatter) {
        Formatter = formatter;
    }

    public Object[] getPiDatas() {
        return piDatas;
    }

    public void setPiDatas(Object[] piDatas) {
        this.piDatas = piDatas;
    }

    public Object[] getInnerDatas() {
        return innerDatas;
    }

    public void setInnerDatas(Object[] innerDatas) {
        this.innerDatas = innerDatas;
    }
}

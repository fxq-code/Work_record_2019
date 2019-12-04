package org.cboard.genWord.genImg.demo;

import com.github.abel533.echarts.Radar;

/**
 * Created by chenf on 2019/12/4.
 */
public class MyIndicator extends Radar.Indicator {

    public MyIndicator(String name, Object max, Object min, String color) {
        super.setName(name);
        super.setMax(max);
        super.setMin(min);
        super.setColor(color);

    }

    public MyIndicator(String name, Object max) {
        super.setName(name);
        super.setMax(max);
    }
}

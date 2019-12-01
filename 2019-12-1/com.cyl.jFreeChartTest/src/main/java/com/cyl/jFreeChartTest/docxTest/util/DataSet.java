package com.cyl.jFreeChartTest.docxTest.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 组装假数据
 * @author caoyulun
 *
 */
public class DataSet {

	public List<AssetsCategoryOfStock> getDemoList(){
		
		List<AssetsCategoryOfStock> demoList = new ArrayList<AssetsCategoryOfStock>();
		for (int i = 0; i < 20; i++) {
			AssetsCategoryOfStock acos = new AssetsCategoryOfStock(i+1, "测试行业"+ (i+1), new BigDecimal("3544973.87").add(new BigDecimal(i)),
					new BigDecimal("2.34").add(new BigDecimal(i)), new BigDecimal("10.34").add(new BigDecimal(i)),
					new BigDecimal("17.34").add(new BigDecimal(i)), new BigDecimal("-2.34").add(new BigDecimal(i)), 
					new BigDecimal("-2.34").add(new BigDecimal(i)));
			demoList.add(acos);
		}
		return demoList;
	}
	
	public String getTotalData( List<AssetsCategoryOfStock> getDemoList){
		BigDecimal n_assets_end_total = new BigDecimal("0") ;
		BigDecimal n_rate_port_end_total = new BigDecimal("0")  ;
		BigDecimal n_rate_benchmark_total = new BigDecimal("0")  ;
		BigDecimal n_rate_excess_total = new BigDecimal("0")  ;
		BigDecimal n_rate_port_begin_total = new BigDecimal("0")  ;
		BigDecimal n_rate_period_chagen_total = new BigDecimal("0")  ;
		
		
		for (AssetsCategoryOfStock acos : getDemoList) {
			n_assets_end_total = n_assets_end_total.add(acos.getN_assets_end());
			n_rate_port_end_total = n_rate_port_end_total.add(acos.getN_rate_port_end());
			n_rate_benchmark_total = n_rate_benchmark_total.add(acos.getN_rate_benchmark());
			n_rate_excess_total = n_rate_excess_total.add(acos.getN_rate_excess());
			n_rate_port_begin_total = n_rate_port_begin_total.add(acos.getN_rate_port_begin());
			n_rate_period_chagen_total = n_rate_period_chagen_total.add(acos.getN_rate_period_chagen());
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n_assets_end_total", n_assets_end_total);
		jsonObj.put("n_rate_port_end_total", n_rate_port_end_total);
		jsonObj.put("n_rate_benchmark_total", n_rate_benchmark_total);
		jsonObj.put("n_rate_excess_total", n_rate_excess_total);
		jsonObj.put("n_rate_port_begin_total", n_rate_port_begin_total);
		jsonObj.put("n_rate_period_chagen_total", n_rate_period_chagen_total);
		
		return jsonObj.toString();
	}
	
}

package org.cboard.genWord.WordTemplete.Vo;

import java.math.BigDecimal;

/**
 * Created by chenf on 2019/12/1.
 */
public class AssetsCategoryOfStock {
    private Integer n_order_num;
    private String c_industry;
    private BigDecimal n_assets_end;
    private BigDecimal n_rate_port_end;
    private BigDecimal n_rate_benchmark;
    private BigDecimal n_rate_excess;
    private BigDecimal n_rate_port_begin;
    private BigDecimal n_rate_period_chagen;

    public String[] elementVal(){
        String [] eleValArray = new String[]{
                this.n_order_num.toString(),
                this.c_industry.toString(),
                this.n_assets_end.toString(),
                this.n_rate_port_end.toString(),
                this.n_rate_benchmark.toString()  ,
                this.n_rate_excess.toString()  ,
                this.n_rate_port_begin.toString()  ,
                this.n_rate_period_chagen.toString()

        };
        return eleValArray;
    }


    public AssetsCategoryOfStock() {
        super();
    }
    public AssetsCategoryOfStock(Integer n_order_num, String c_industry, BigDecimal n_assets_end,
                                 BigDecimal n_rate_port_end, BigDecimal n_rate_benchmark, BigDecimal n_rate_excess,
                                 BigDecimal n_rate_port_begin, BigDecimal n_rate_period_chagen) {
        super();
        this.n_order_num = n_order_num;
        this.c_industry = c_industry;
        this.n_assets_end = n_assets_end;
        this.n_rate_port_end = n_rate_port_end;
        this.n_rate_benchmark = n_rate_benchmark;
        this.n_rate_excess = n_rate_excess;
        this.n_rate_port_begin = n_rate_port_begin;
        this.n_rate_period_chagen = n_rate_period_chagen;
    }
    public Integer getN_order_num() {
        return n_order_num;
    }
    public void setN_order_num(Integer n_order_num) {
        this.n_order_num = n_order_num;
    }
    public String getC_industry() {
        return c_industry;
    }
    public void setC_industry(String c_industry) {
        this.c_industry = c_industry;
    }
    public BigDecimal getN_assets_end() {
        return n_assets_end;
    }
    public void setN_assets_end(BigDecimal n_assets_end) {
        this.n_assets_end = n_assets_end;
    }
    public BigDecimal getN_rate_port_end() {
        return n_rate_port_end;
    }
    public void setN_rate_port_end(BigDecimal n_rate_port_end) {
        this.n_rate_port_end = n_rate_port_end;
    }
    public BigDecimal getN_rate_benchmark() {
        return n_rate_benchmark;
    }
    public void setN_rate_benchmark(BigDecimal n_rate_benchmark) {
        this.n_rate_benchmark = n_rate_benchmark;
    }
    public BigDecimal getN_rate_excess() {
        return n_rate_excess;
    }
    public void setN_rate_excess(BigDecimal n_rate_excess) {
        this.n_rate_excess = n_rate_excess;
    }
    public BigDecimal getN_rate_port_begin() {
        return n_rate_port_begin;
    }
    public void setN_rate_port_begin(BigDecimal n_rate_port_begin) {
        this.n_rate_port_begin = n_rate_port_begin;
    }
    public BigDecimal getN_rate_period_chagen() {
        return n_rate_period_chagen;
    }
    public void setN_rate_period_chagen(BigDecimal n_rate_period_chagen) {
        this.n_rate_period_chagen = n_rate_period_chagen;
    }


}

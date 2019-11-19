package com.wh.entity;

import java.util.List;

/**
 * Created by chenf on 2019/11/18.
 */
public class ReportEntity {
    //分行报表需要的数据项
    private String year;
    private double yearIncomeAll; //总收入
    private double increase;  //同期增长
    private String perforLevel; //业绩指数
    private  double rivalAllRate;  //行内对手
    private String branchBank;     //xxx分行
    private  double incomePart;   //分行总收入
    private double  incomeRate;   //分行总收入占总体收入百分比
    private double  rivalPartRate; //分行打败百分比对手
    private double  yearColloPrice;  //年度托管费用
    private double  rivalColloRate;  //年度托管费用打败对手百分比
    private double  yearBankPrice;  //年度银行费
    private double  rivalBankRate;  //年度银行费打败行内对手
    private double  yearCapCediPrice; //年度沉淀总收入
    private double  rivalCapCediRate;  //年度沉淀收入打败行内对手
    private int      customerNum;      //客户数
    private double   rivalCustomerRate;  //客户数打败行内对手
    private double   netAssetSize;     //资产净值规模
    private double  rivalNetAssetSizeRate; //资产净值规模打败对手
    private int    colloProduceNum;      //托管产品数
    private double  rivalColloProductRate;  //托管产品数打败行内对手
    private double  branchResourceInput;  //分行资源投入
    private double rivalbranchResInputRate;  //分行资源投入打败行内对手
    private String province;                //省会

    public String getBranchBank() {
        return branchBank;
    }

    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    //总行需要的数据(分行业绩指数排名)
    private List<String> rinking5Top;  //排名前5

    private List<String> rinking5Last;  //排名后5


    public ReportEntity() {
    }



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getYearIncomeAll() {
        return yearIncomeAll;
    }

    public void setYearIncomeAll(double yearIncomeAll) {
        this.yearIncomeAll = yearIncomeAll;
    }

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }

    public String getPerforLevel() {
        return perforLevel;
    }

    public void setPerforLevel(String perforLevel) {
        this.perforLevel = perforLevel;
    }

    public double getRivalAllRate() {
        return rivalAllRate;
    }

    public void setRivalAllRate(double rivalAllRate) {
        this.rivalAllRate = rivalAllRate;
    }

    public double getIncomePart() {
        return incomePart;
    }

    public void setIncomePart(double incomePart) {
        this.incomePart = incomePart;
    }

    public double getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(double incomeRate) {
        this.incomeRate = incomeRate;
    }

    public double getRivalPartRate() {
        return rivalPartRate;
    }

    public void setRivalPartRate(double rivalPartRate) {
        this.rivalPartRate = rivalPartRate;
    }

    public double getYearColloPrice() {
        return yearColloPrice;
    }

    public void setYearColloPrice(double yearColloPrice) {
        this.yearColloPrice = yearColloPrice;
    }

    public double getRivalColloRate() {
        return rivalColloRate;
    }

    public void setRivalColloRate(double rivalColloRate) {
        this.rivalColloRate = rivalColloRate;
    }

    public double getYearBankPrice() {
        return yearBankPrice;
    }

    public void setYearBankPrice(double yearBankPrice) {
        this.yearBankPrice = yearBankPrice;
    }

    public double getRivalBankRate() {
        return rivalBankRate;
    }

    public void setRivalBankRate(double rivalBankRate) {
        this.rivalBankRate = rivalBankRate;
    }

    public double getYearCapCediPrice() {
        return yearCapCediPrice;
    }

    public void setYearCapCediPrice(double yearCapCediPrice) {
        this.yearCapCediPrice = yearCapCediPrice;
    }

    public double getRivalCapCediRate() {
        return rivalCapCediRate;
    }

    public void setRivalCapCediRate(double rivalCapCediRate) {
        this.rivalCapCediRate = rivalCapCediRate;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public double getRivalCustomerRate() {
        return rivalCustomerRate;
    }

    public void setRivalCustomerRate(double rivalCustomerRate) {
        this.rivalCustomerRate = rivalCustomerRate;
    }

    public double getNetAssetSize() {
        return netAssetSize;
    }

    public void setNetAssetSize(double netAssetSize) {
        this.netAssetSize = netAssetSize;
    }

    public double getRivalNetAssetSizeRate() {
        return rivalNetAssetSizeRate;
    }

    public void setRivalNetAssetSizeRate(double rivalNetAssetSizeRate) {
        this.rivalNetAssetSizeRate = rivalNetAssetSizeRate;
    }

    public int getColloProduceNum() {
        return colloProduceNum;
    }

    public void setColloProduceNum(int colloProduceNum) {
        this.colloProduceNum = colloProduceNum;
    }

    public double getRivalColloProductRate() {
        return rivalColloProductRate;
    }

    public void setRivalColloProductRate(double rivalColloProductRate) {
        this.rivalColloProductRate = rivalColloProductRate;
    }

    public double getBranchResourceInput() {
        return branchResourceInput;
    }

    public void setBranchResourceInput(double branchResourceInput) {
        this.branchResourceInput = branchResourceInput;
    }

    public double getRivalbranchResInputRate() {
        return rivalbranchResInputRate;
    }

    public void setRivalbranchResInputRate(double rivalbranchResInputRate) {
        this.rivalbranchResInputRate = rivalbranchResInputRate;
    }

    public List<String> getRinking5Top() {
        return rinking5Top;
    }

    public void setRinking5Top(List<String> rinking5Top) {
        this.rinking5Top = rinking5Top;
    }

    public List<String> getRinking5Last() {
        return rinking5Last;
    }

    public void setRinking5Last(List<String> rinking5Last) {
        this.rinking5Last = rinking5Last;
    }


    public ReportEntity(String year, double yearIncomeAll, double increase, String perforLevel, double rivalAllRate, String branchBank, double incomePart, double incomeRate, double rivalPartRate, double yearColloPrice, double rivalColloRate, double yearBankPrice, double rivalBankRate, double yearCapCediPrice, double rivalCapCediRate, int customerNum, double rivalCustomerRate, double netAssetSize, double rivalNetAssetSizeRate, int colloProduceNum, double rivalColloProductRate, double branchResourceInput, double rivalbranchResInputRate, String province, List<String> rinking5Top, List<String> rinking5Last) {
        this.year = year;
        this.yearIncomeAll = yearIncomeAll;
        this.increase = increase;
        this.perforLevel = perforLevel;
        this.rivalAllRate = rivalAllRate;
        this.branchBank = branchBank;
        this.incomePart = incomePart;
        this.incomeRate = incomeRate;
        this.rivalPartRate = rivalPartRate;
        this.yearColloPrice = yearColloPrice;
        this.rivalColloRate = rivalColloRate;
        this.yearBankPrice = yearBankPrice;
        this.rivalBankRate = rivalBankRate;
        this.yearCapCediPrice = yearCapCediPrice;
        this.rivalCapCediRate = rivalCapCediRate;
        this.customerNum = customerNum;
        this.rivalCustomerRate = rivalCustomerRate;
        this.netAssetSize = netAssetSize;
        this.rivalNetAssetSizeRate = rivalNetAssetSizeRate;
        this.colloProduceNum = colloProduceNum;
        this.rivalColloProductRate = rivalColloProductRate;
        this.branchResourceInput = branchResourceInput;
        this.rivalbranchResInputRate = rivalbranchResInputRate;
        this.province = province;
        this.rinking5Top = rinking5Top;
        this.rinking5Last = rinking5Last;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "year='" + year + '\'' +
                ", yearIncomeAll=" + yearIncomeAll +
                ", increase=" + increase +
                ", perforLevel='" + perforLevel + '\'' +
                ", rivalAllRate=" + rivalAllRate +
                ", branchBank='" + branchBank + '\'' +
                ", incomePart=" + incomePart +
                ", incomeRate=" + incomeRate +
                ", rivalPartRate=" + rivalPartRate +
                ", yearColloPrice=" + yearColloPrice +
                ", rivalColloRate=" + rivalColloRate +
                ", yearBankPrice=" + yearBankPrice +
                ", rivalBankRate=" + rivalBankRate +
                ", yearCapCediPrice=" + yearCapCediPrice +
                ", rivalCapCediRate=" + rivalCapCediRate +
                ", customerNum=" + customerNum +
                ", rivalCustomerRate=" + rivalCustomerRate +
                ", netAssetSize=" + netAssetSize +
                ", rivalNetAssetSizeRate=" + rivalNetAssetSizeRate +
                ", colloProduceNum=" + colloProduceNum +
                ", rivalColloProductRate=" + rivalColloProductRate +
                ", branchResourceInput=" + branchResourceInput +
                ", rivalbranchResInputRate=" + rivalbranchResInputRate +
                ", province='" + province + '\'' +
                ", rinking5Top=" + rinking5Top +
                ", rinking5Last=" + rinking5Last +
                '}';
    }
}

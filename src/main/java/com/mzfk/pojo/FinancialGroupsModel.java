package com.mzfk.pojo;


import java.math.BigInteger;

/**
 * @author sz
 * @Title: FinancialGroupsModel
 * @ProjectName 8Madmin_config
 * @Description: 财团model
 * @date 2018/11/516:02
 */
public class FinancialGroupsModel {


    private String id;
    private String name;
    private String currencyCode;

    private BigInteger maxTotalBalance;
    private BigInteger maxDailyDeposit;
    private BigInteger maxDailyWithdraw;
    private BigInteger minWithdrawAmount;
    private BigInteger maxWithdrawAmount;

    private Integer isDefault;


    public BigInteger getMaxTotalBalance() {
        return maxTotalBalance;
    }

    public void setMaxTotalBalance(String maxTotalBalance) {
        BigInteger maxTotalBalances = new BigInteger(maxTotalBalance);
        this.maxTotalBalance = maxTotalBalances;
    }

    public BigInteger getMaxDailyDeposit() {
        return maxDailyDeposit;
    }

    public void setMaxDailyDeposit(String maxDailyDeposit) {
        BigInteger maxDailyDeposits = new BigInteger(maxDailyDeposit);
        this.maxDailyDeposit = maxDailyDeposits;
    }

    public BigInteger getMaxDailyWithdraw() {
        return maxDailyWithdraw;
    }

    public void setMaxDailyWithdraw(String maxDailyWithdraw) {
        BigInteger maxDailyWithdraws = new BigInteger(maxDailyWithdraw);
        this.maxDailyWithdraw = maxDailyWithdraws;
    }

    public BigInteger getMinWithdrawAmount() {
        return minWithdrawAmount;
    }

    public void setMinWithdrawAmount(String minWithdrawAmount) {
        BigInteger minWithdrawAmounts = new BigInteger(minWithdrawAmount);
        this.minWithdrawAmount = minWithdrawAmounts;
    }

    public BigInteger getMaxWithdrawAmount() {
        return maxWithdrawAmount;
    }

    public void setMaxWithdrawAmount(String maxWithdrawAmount) {
        BigInteger maxWithdrawAmounts = new BigInteger(maxWithdrawAmount);
        this.maxWithdrawAmount = maxWithdrawAmounts;
    }




    @Override
    public String toString() {
        return "FinancialGroupsModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", maxTotalBalance='" + maxTotalBalance + '\'' +
                ", maxDailyDeposit='" + maxDailyDeposit + '\'' +
                ", maxDailyWithdraw='" + maxDailyWithdraw + '\'' +
                ", minWithdrawAmount='" + minWithdrawAmount + '\'' +
                ", maxWithdrawAmount='" + maxWithdrawAmount + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}

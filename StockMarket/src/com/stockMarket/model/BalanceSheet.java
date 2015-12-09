package com.stockMarket.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BalanceSheet implements java.io.Serializable{

	private static final long serialVersionUID = 20151128L;
	
	private String stockCode;

	private String yearMonth;
	
	private BigDecimal fixedAssets;
	
	private BigDecimal investments;
	
	private BigDecimal otherAssets;
	
	private BigDecimal cashOnHand;
	
	private BigDecimal receivables;
	
	private BigDecimal investory;
	
	private BigDecimal otherCurrentAssets;
	
	private BigDecimal totalAssets;
	
	private BigDecimal longTermDebt;
	
	private BigDecimal otherLongTermLiabilities;
	
	private BigDecimal payables;
	
	private BigDecimal taxation;
	
	private BigDecimal shortTermDebt;
	
	private BigDecimal otherCurrentLiabilities;
	
	private BigDecimal totalLiabilities;
	
	private BigDecimal totalEquity;
	
	private BigDecimal totalDebt;
	
	private BigDecimal netAssets;
	
	private BigDecimal intangibleAssets;
	
	private String currency;
	
	private String unit;
	
	private Timestamp lastUpdatedTime;
	
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public BigDecimal getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(BigDecimal fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public BigDecimal getInvestments() {
		return investments;
	}

	public void setInvestments(BigDecimal investments) {
		this.investments = investments;
	}

	public BigDecimal getOtherAssets() {
		return otherAssets;
	}

	public void setOtherAssets(BigDecimal otherAssets) {
		this.otherAssets = otherAssets;
	}

	public BigDecimal getCashOnHand() {
		return cashOnHand;
	}

	public void setCashOnHand(BigDecimal cashOnHand) {
		this.cashOnHand = cashOnHand;
	}

	public BigDecimal getReceivables() {
		return receivables;
	}

	public void setReceivables(BigDecimal receivables) {
		this.receivables = receivables;
	}

	public BigDecimal getInvestory() {
		return investory;
	}

	public void setInvestory(BigDecimal investory) {
		this.investory = investory;
	}

	public BigDecimal getOtherCurrentAssets() {
		return otherCurrentAssets;
	}

	public void setOtherCurrentAssets(BigDecimal otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}

	public BigDecimal getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}

	public BigDecimal getLongTermDebt() {
		return longTermDebt;
	}

	public void setLongTermDebt(BigDecimal longTermDebt) {
		this.longTermDebt = longTermDebt;
	}

	public BigDecimal getOtherLongTermLiabilities() {
		return otherLongTermLiabilities;
	}

	public void setOtherLongTermLiabilities(BigDecimal otherLongTermLiabilities) {
		this.otherLongTermLiabilities = otherLongTermLiabilities;
	}

	public BigDecimal getPayables() {
		return payables;
	}

	public void setPayables(BigDecimal payables) {
		this.payables = payables;
	}

	public BigDecimal getTaxation() {
		return taxation;
	}

	public void setTaxation(BigDecimal taxation) {
		this.taxation = taxation;
	}

	public BigDecimal getShortTermDebt() {
		return shortTermDebt;
	}

	public void setShortTermDebt(BigDecimal shortTermDebt) {
		this.shortTermDebt = shortTermDebt;
	}

	public BigDecimal getOtherCurrentLiabilities() {
		return otherCurrentLiabilities;
	}

	public void setOtherCurrentLiabilities(BigDecimal otherCurrentLiabilities) {
		this.otherCurrentLiabilities = otherCurrentLiabilities;
	}

	public BigDecimal getTotalLiabilities() {
		return totalLiabilities;
	}

	public void setTotalLiabilities(BigDecimal totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public BigDecimal getTotalEquity() {
		return totalEquity;
	}

	public void setTotalEquity(BigDecimal totalEquity) {
		this.totalEquity = totalEquity;
	}

	public BigDecimal getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt(BigDecimal totalDebt) {
		this.totalDebt = totalDebt;
	}

	public BigDecimal getNetAssets() {
		return netAssets;
	}

	public void setNetAssets(BigDecimal netAssets) {
		this.netAssets = netAssets;
	}

	public BigDecimal getIntangibleAssets() {
		return intangibleAssets;
	}

	public void setIntangibleAssets(BigDecimal intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}


}

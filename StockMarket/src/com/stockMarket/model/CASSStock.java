package com.stockMarket.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CASSStock implements java.io.Serializable{

	private static final long serialVersionUID = 20151128L;
	
	private String stockCode;

	private String stockName;
	
	private BigDecimal marketValue;
	
	private BigDecimal cass;
	
	private BigDecimal nonCass;
	
	private BigDecimal top5Cass;
	
	private BigDecimal driness;
	
	private Timestamp lastUpdatedTime;
	
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	public BigDecimal getCass() {
		return cass;
	}

	public void setCass(BigDecimal cass) {
		this.cass = cass;
	}

	public BigDecimal getNonCass() {
		return nonCass;
	}

	public void setNonCass(BigDecimal nonCass) {
		this.nonCass = nonCass;
	}

	public BigDecimal getTop5Cass() {
		return top5Cass;
	}

	public void setTop5Cass(BigDecimal top5Cass) {
		this.top5Cass = top5Cass;
	}

	public BigDecimal getDriness() {
		return driness;
	}

	public void setDriness(BigDecimal driness) {
		this.driness = driness;
	}

	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}


	
}

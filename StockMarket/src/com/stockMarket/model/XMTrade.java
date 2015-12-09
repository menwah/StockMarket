package com.stockMarket.model;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.*;


public class XMTrade implements java.io.Serializable{
	
	private static final long serialVersionUID = 20151128L;

	private int id;
	
	private String stockCode;
	
	private String stockName;
	
	private BigDecimal marketValue;
	
	private Time executionTime;
	
	private int quantity;
	
	private String tradeType;
	
	private BigDecimal price;
	
	private BigDecimal amount;
	
	private Date executionDate;
	
	private Timestamp lastUpdatedTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Time getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Time executionTime) {
		this.executionTime = executionTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}


	
}

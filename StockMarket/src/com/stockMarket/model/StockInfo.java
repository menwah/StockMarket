package com.stockMarket.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class StockInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = 20151128L;

	private String stockCode;
	
	private String stockName;
	
	private Date listingDate;
	
	private String currency;
	
	private int lotSize;
	
	private BigDecimal nav;
	
	private BigDecimal pe;
	
	private BigDecimal dividend;
	
	private BigDecimal marketValue;
	
	private BigDecimal high52Week;
	
	private BigDecimal low52Week;
	
	private BigDecimal closingPrice;
	
	private BigDecimal rsi;
	
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

	public Date getListingDate() {
		return listingDate;
	}

	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getLotSize() {
		return lotSize;
	}

	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}

	public BigDecimal getNav() {
		return nav;
	}

	public void setNav(BigDecimal nav) {
		this.nav = nav;
	}

	public BigDecimal getPe() {
		return pe;
	}

	public void setPe(BigDecimal pe) {
		this.pe = pe;
	}

	public BigDecimal getDividend() {
		return dividend;
	}

	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}

	public BigDecimal getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	public BigDecimal getHigh52Week() {
		return high52Week;
	}

	public void setHigh52Week(BigDecimal high52Week) {
		this.high52Week = high52Week;
	}

	public BigDecimal getLow52Week() {
		return low52Week;
	}

	public void setLow52Week(BigDecimal low52Week) {
		this.low52Week = low52Week;
	}

	public BigDecimal getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(BigDecimal closingPrice) {
		this.closingPrice = closingPrice;
	}

	public BigDecimal getRsi() {
		return rsi;
	}

	public void setRsi(BigDecimal rsi) {
		this.rsi = rsi;
	}
	
	@Override
	public String toString() {
		return "StockInfo [stockCode=" + stockCode + ", stockName=" + stockName
				+ ", listingDate=" + listingDate + ", currency=" + currency
				+ ", lotSize=" + lotSize + ", nav=" + nav + ", pe=" + pe
				+ ", dividend=" + dividend + ", marketValue=" + marketValue
				+ ", high52Week=" + high52Week + ", low52Week=" + low52Week
				+ ", closingPrice=" + closingPrice + ", rsi=" + rsi
				+ ", lastUpdatedTime=" + lastUpdatedTime + "]";
	}
	
}

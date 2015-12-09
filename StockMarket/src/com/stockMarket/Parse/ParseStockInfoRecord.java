package com.stockMarket.Parse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.stockMarket.model.StockInfo;

public class ParseStockInfoRecord {

	public StockInfo parseFromHTML(String html) {
		
		StockInfo stock = new StockInfo();
		
		// Format the time
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/DD");
		Date date = null;
		
		Document doc = Jsoup.parse(html);

		//Retrieve Basic Info
		Element stockInfo = doc.getElementsByClass("grid_11").first();
		
		Iterator<Element> stockInfoIterator = stockInfo.select("table").iterator();

		Iterator<Element> stockBasicInfoIterator = stockInfoIterator.next().select("td").iterator();

		while (stockBasicInfoIterator.hasNext()) {
			
			String stockBasicHeader = stockBasicInfoIterator.next().text();
			
			if(stockBasicHeader.compareTo("Name")==0)
				stock.setStockName(stockBasicInfoIterator.next().text());
			
			if(stockBasicHeader.compareTo("Listing Date")==0)
			{
				String dateValue = stockBasicInfoIterator.next().text();
				
				if(dateValue.compareTo("-")!=0)
				{
					try {
						date = formatter.parse(dateValue);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					stock.setListingDate(date);
				}
			}
			
			if(stockBasicHeader.compareTo("Trade Currency")==0)
				stock.setCurrency(stockBasicInfoIterator.next().text());
			
			if(stockBasicHeader.compareTo("Board Lot")==0)
				stock.setLotSize(Integer.parseInt(stockBasicInfoIterator.next().text().replaceAll(",", "")));
			
			if(stockBasicHeader.contains("NAV(")&&(!stockBasicHeader.contains("PNAV")))
				stock.setNav(validateFormat(stockBasicInfoIterator.next().text().replaceAll(",", "")));
			
			if(stockBasicHeader.compareTo("PE")==0)
				stock.setPe(validateFormat(stockBasicInfoIterator.next().text().replaceAll(",", "")));
			
			if(stockBasicHeader.compareTo("Yield")==0)
				stock.setDividend(validateFormat(stockBasicInfoIterator.next().text().replaceAll(",", "")));
			
		}

		Iterator<Element> stockPriceVolumeIterator = stockInfoIterator.next().select("td").iterator();

		while (stockPriceVolumeIterator.hasNext()) {
			
			String stockPriceVolumeHeader = stockPriceVolumeIterator.next().text();
			
			if(stockPriceVolumeHeader.compareTo("Market Capital")==0)
				stock.setMarketValue(validateFormat(stockPriceVolumeIterator.next().text().replaceAll(",", "")));
			
			if(stockPriceVolumeHeader.compareTo("52-Week High")==0)
				stock.setHigh52Week(validateFormat(stockPriceVolumeIterator.next().text().replaceAll(",", "")));
			
			if(stockPriceVolumeHeader.compareTo("52-Week Low")==0)
				stock.setLow52Week(validateFormat(stockPriceVolumeIterator.next().text().replaceAll(",", "")));
			
			if(stockPriceVolumeHeader.compareTo("Close Price")==0)
				stock.setClosingPrice(validateFormat(stockPriceVolumeIterator.next().text().replaceAll(",", "")));
			
			
		}
		
		Iterator<Element> stockTechincalIterator = stockInfoIterator.next().select("td").iterator();

		while (stockTechincalIterator.hasNext()) {
			
			if(stockTechincalIterator.next().text().compareTo("14-Day RSI")==0)
				stock.setRsi(new BigDecimal(stockTechincalIterator.next().text().replaceAll(",", "")));
		}
		
		//Last update time
		stock.setLastUpdatedTime(new Timestamp((new Date()).getTime()));
		
		return stock;
	}
	
	public BigDecimal validateFormat(String value){
		
		BigDecimal tmp;
		
		if(value.compareTo("-")==0){
			tmp = null;
		}
		else if(value.contains("K")){
			tmp = new BigDecimal(value.substring(0,value.indexOf("K")));
			tmp = tmp.multiply(new BigDecimal("1000"));
		}
		else if(value.contains("M")){
			tmp = new BigDecimal(value.substring(0,value.indexOf("M")));
			tmp = tmp.multiply(new BigDecimal("1000000"));
		}
		else if(value.contains("B")){
			tmp = new BigDecimal(value.substring(0,value.indexOf("B")));
			tmp = tmp.multiply(new BigDecimal("1000000000"));
		}
		else{
			tmp = new BigDecimal(value);
		}

		return tmp;
		
	}
	
}

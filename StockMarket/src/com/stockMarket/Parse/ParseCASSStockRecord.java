package com.stockMarket.Parse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.stockMarket.model.CASSStock;

public class ParseCASSStockRecord {

	/**
	 * @param args
	 */
	public ArrayList<CASSStock> parseFromHTML(String html) {

		String stockCode = null;
		String stockName = null;
		String marketValue = null;
		String cass = null;
		String nonCass = null;
		String top5Cass = null;
		
		Document doc = Jsoup.parse(html);

		ArrayList<CASSStock> stocks = new ArrayList<CASSStock>();

		Element table = doc.select("table").first();

		Iterator<Element> iterator = table.select("td").iterator();

		while (iterator.hasNext()) {
			stockCode = iterator.next().text();
			stockName = iterator.next().text();
			marketValue = iterator.next().text();
			cass = iterator.next().text();
			nonCass = iterator.next().text();
			iterator.next().text();
			top5Cass = iterator.next().text();
			iterator.next().text();
			iterator.next().text();
			iterator.next().text();

			CASSStock stock = convertCASSStock(stockCode, stockName, marketValue,
					cass, nonCass, top5Cass);
			stocks.add(stock);
			
		}

		return stocks;
	}
	
	private CASSStock convertCASSStock(String stockCode, String stockName,
			String marketValue, String cass, String nonCass,
			String top5Cass) {

		// Add new stock
		CASSStock stock = new CASSStock();

		stock.setStockCode(stockCode);
		stock.setStockName(stockName);
		
		try{
		stock.setMarketValue(new BigDecimal(marketValue.replaceAll(",", "")));
		}catch(Exception e)
		{
			marketValue = "999,999,999.99";
			stock.setMarketValue(new BigDecimal(marketValue.replaceAll(",", "")));
		}
		try{
			
			BigDecimal driness = new BigDecimal(0);
			
			stock.setCass(new BigDecimal(0));
			stock.setNonCass(new BigDecimal(0));
			stock.setTop5Cass(new BigDecimal(0));
			stock.setDriness(driness);
			
			if(cass.compareTo("N/A")!=0)
			{
				stock.setCass(new BigDecimal(cass));
				stock.setNonCass(new BigDecimal(nonCass));
				stock.setTop5Cass(new BigDecimal(top5Cass));
				
				driness = driness.add(stock.getNonCass());
				driness = driness.add(stock.getTop5Cass());
				stock.setDriness(driness);
			}

			stock.setLastUpdatedTime(new Timestamp((new Date()).getTime()));
		}catch(Exception e)
		{
			System.out.println("Exception case on : "+stock.getStockCode());
		}
		
		return stock;
	}


}

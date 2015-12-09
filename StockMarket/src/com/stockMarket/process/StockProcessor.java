package com.stockMarket.process;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.stockMarket.Parse.ParseStockInfoRecord;
import com.stockMarket.dao.CASSStockDao;
import com.stockMarket.dao.StockInfoDao;
import com.stockMarket.model.CASSStock;
import com.stockMarket.model.StockInfo;

public class StockProcessor extends BasicProcessor {

	public StockInfo parse(String html) {
		ParseStockInfoRecord parser = new ParseStockInfoRecord();

		StockInfo stock = parser.parseFromHTML(html);

		System.out.println("Stock info : " + stock.toString());

		return stock;

	}

	public void process() throws Exception {

		String aastockDetailQuotalink = "http://www.aastocks.com/en/stocks/analysis/company-fundamental/basic-information?symbol=";

		StockInfo stock;

		StockInfoDao dao = new StockInfoDao();

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		CASSStockProcessor cassStockProcessor = new CASSStockProcessor();

		List<CASSStock> cStocks = cassStockProcessor.getAllCASSStockProcess();

		for (int i = 0; i < cStocks.size(); i++) {

			stock = null;
			stock = dao.getStockInfoByStockCodeAndToday(cStocks.get(i)
					.getStockCode());

			if (stock == null) {
				
				String page = this.getPageContent(aastockDetailQuotalink
						+ cStocks.get(i).getStockCode(), false);
				
				try {
					stock = this.parse(page);
					stock.setStockCode(cStocks.get(i).getStockCode());
					
					System.out.println("Stock Code : "
							+ cStocks.get(i).getStockCode());
					stock.toString();
					dao.addOrUpdateStockInfo(stock);
					
				} catch (Exception e) {
					System.out.println("Fail to paring : "
							+ cStocks.get(i).getStockCode());
					e.printStackTrace();
				}
				
				System.out.println("--- Sleep in random time ---");
				Thread.sleep((int) (Math.random() * 1000));
				// System.out.println("Time : "+(int)(Math.random()*10000));
				System.out.println("--- Start again ---");
				// break;
			}
		}
	}

}

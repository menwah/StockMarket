package com.stockMarket.process;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

import com.stockMarket.Parse.ParseBalanceSheetRecord;
import com.stockMarket.dao.BalanceSheetDao;
import com.stockMarket.model.BalanceSheet;
import com.stockMarket.model.CASSStock;

public class BalanceSheetProcessor extends BasicProcessor {

	public List<BalanceSheet> parse(String html, String stockCode) {
		ParseBalanceSheetRecord parser = new ParseBalanceSheetRecord();

		List<BalanceSheet> records = parser.parseFromHTML(html, stockCode);

		return records;

	}

	public void process() throws Exception {

		importData(1);
		
		importData(2);
		
	}
	
	public void importData(int annual) throws Exception{
		
		String aastockBalanceSheetlink = "http://www.aastocks.com/en/stocks/analysis/company-fundamental/balance-sheet?symbol=";

		BalanceSheetDao dao = new BalanceSheetDao();

		List<BalanceSheet> sheets = new ArrayList<BalanceSheet>();

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		CASSStockProcessor cassStockProcessor = new CASSStockProcessor();

		List<CASSStock> cStocks = cassStockProcessor.getAllCASSStockProcess();

		for (int i = 0; i < cStocks.size(); i++) {

			String period = "";
			if(annual == 2){
				period = "&period=2";
			}
			
			String page = this.getPageContent(aastockBalanceSheetlink
					+ cStocks.get(i).getStockCode()+period, false);
			
			try {
				sheets = this.parse(page, cStocks.get(i).getStockCode());
				//System.out.println("Stock Code : "
				//		+ cStocks.get(i).getStockCode());

				for (int j = 0; j < sheets.size(); j++)
				{  
					dao.addOrUpdateBalanceSheet((BalanceSheet) sheets.get(j));
				}

			} catch (Exception e) {
				System.out.println("Fail to paring : "
						+ cStocks.get(i).getStockCode());
				e.printStackTrace();
			}

			//System.out.println("--- Sleep in random time ---");
			Thread.sleep((int) (Math.random() * 1000));
			//System.out.println("--- Start again ---");
		}
	}

}

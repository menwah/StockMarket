package com.stockMarket;

import com.stockMarket.process.BalanceSheetProcessor;
import com.stockMarket.process.CASSStockProcessor;
import com.stockMarket.process.LatestBalanceSheetProcessor;
import com.stockMarket.process.StockProcessor;
import com.stockMarket.process.XMTradeProcessor;

public class TestDrive {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		XMTradeProcessor xmtProcess = new XMTradeProcessor();
		xmtProcess.batchProcess();
		
		CASSStockProcessor cassProcess = new CASSStockProcessor();
		cassProcess.process();
		
		StockProcessor stockProcess = new StockProcessor();
		stockProcess.process();
		
		BalanceSheetProcessor bsProcess = new BalanceSheetProcessor();
		bsProcess.process();
		
		LatestBalanceSheetProcessor lbsProcess = new LatestBalanceSheetProcessor();
		lbsProcess.importData();
		
	}

}

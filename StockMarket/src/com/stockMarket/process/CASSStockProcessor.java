package com.stockMarket.process;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.stockMarket.Parse.ParseCASSStockRecord;
import com.stockMarket.dao.CASSStockDao;
import com.stockMarket.model.CASSStock;

public class CASSStockProcessor extends BasicProcessor {
	
	public void parse(String html){
		ParseCASSStockRecord parser = new ParseCASSStockRecord();

		ArrayList<CASSStock> trades = parser.parseFromHTML(html);
		
		CASSStockDao dao = new CASSStockDao();
		
		Iterator<CASSStock> iterator = trades.iterator();
		while (iterator.hasNext()) {
			dao.addOrUpdateCASSStock(iterator.next());
		}
	}
	
	public void process() throws Exception{
		String loginInterfaceUrl = "http://www.stockradar.hk/page.php?p=login";
		String loginUrl = "http://www.stockradar.hk/member/_login.php";
		String cassStock = "http://www.stockradar.hk/page.php?p=ccass_update_vip";
		
		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		String page = this.getPageContent(loginInterfaceUrl, true);
		System.out.println("Page : " + page);

		this.sendPost(loginUrl, "username=tmkk83&password=95430901");

		String result = this.getPageContent(cassStock,false);
		this.parse(result);
	}
	
	public List<CASSStock> getAllCASSStockProcess(){
		
		CASSStockDao dao = new CASSStockDao();
		List<CASSStock> stocks = dao.getAllCASSStocks();
		return stocks;
	}
}

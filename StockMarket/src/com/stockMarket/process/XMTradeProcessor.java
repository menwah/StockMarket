package com.stockMarket.process;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.stockMarket.Parse.ParseXMTradeRecord;
import com.stockMarket.dao.XMTradeDao;
import com.stockMarket.model.XMTrade;

public class XMTradeProcessor extends BasicProcessor {

	public void batchProcess() throws Exception {
		String loginInterfaceUrl = "http://www.stockradar.hk/page.php?p=login";
		String loginUrl = "http://www.stockradar.hk/member/_login.php";
		String stockXMTradeDefaultUrl = "http://www.stockradar.hk/page.php?p=t_XM_vip&s=%26wdate%3D";

		Date minDate = new Date();
		Date maxDate = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
		String minDateInString = "2015_12_04";
		String maxDateInString = "2015_12_05";

		try {

			minDate = formatter.parse(minDateInString);
			System.out.println(minDate);
			System.out.println(formatter.format(minDate));

			maxDate = formatter.parse(maxDateInString);
			System.out.println(maxDate);
			System.out.println(formatter.format(maxDate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		String page = this.getPageContent(loginInterfaceUrl, true);
		System.out.println("Page : " + page);

		this.sendPost(loginUrl, "username=tmkk83&password=95430901");
		while (minDate.before(maxDate)) {
			System.out.println("Link : " + stockXMTradeDefaultUrl
					+ formatter.format(minDate));
			String result = this.getPageContent(stockXMTradeDefaultUrl
					+ formatter.format(minDate), false);

			List<XMTrade> trades = this.parse(result, minDate);

			this.importData(trades, minDate);
			
			minDate = DateUtils.addDays(minDate, 1);
		}
	}

	public List<XMTrade> parse(String html, Date date) {
		ParseXMTradeRecord parser = new ParseXMTradeRecord();

		List<XMTrade> trades = parser.parseFromHTML(html, date);
		
		return trades;

	}

	public void importData(List<XMTrade> trades, Date executionDate) {

		XMTradeDao dao = new XMTradeDao();

		List<XMTrade> tmpTrades = dao.getXMTradesByExecutionDate(executionDate);
		
		//Delete duplication data
		if(tmpTrades.size()>0)
			dao.deleteXMTradeByExecutionDate(executionDate);
		
		Iterator<XMTrade> iterator = trades.iterator();
		while (iterator.hasNext()) {
			dao.addXMTrade(iterator.next());
		}

	}

	public void process() throws Exception {
		String loginInterfaceUrl = "http://www.stockradar.hk/page.php?p=login";
		String loginUrl = "http://www.stockradar.hk/member/_login.php";
		String stockXMTrade = "http://www.stockradar.hk/page.php?p=t_XM_vip&s=%26wdate%3D2015_11_12";

		Date date = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "07/06/2013";

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		String page = this.getPageContent(loginInterfaceUrl, true);
		System.out.println("Page : " + page);

		this.sendPost(loginUrl, "username=tmkk83&password=95430901");

		String result = this.getPageContent(stockXMTrade, false);
		System.out.println(result);
	}

}

package com.stockMarket.Parse;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.stockMarket.model.XMTrade;

public class ParseXMTradeRecord {

	/**
	 * @param args
	 */
	public List<XMTrade> parseFromHTML(String html, Date executionDate) {

		String stockCode = null;
		String stockName = null;
		String marketValue = null;
		String executionTime = null;
		String quantity = null;
		String tradeType = null;
		String price = null;
		String amount = null;

		Document doc = Jsoup.parse(html);

		List<XMTrade> trades = new ArrayList<XMTrade>();

		Element table = doc.select("table").first();

		Iterator<Element> iterator = table.select("td").iterator();

		while (iterator.hasNext()) {

			try {
				
			stockCode = iterator.next().text();
			stockName = iterator.next().text();
			marketValue = iterator.next().text();
			executionTime = iterator.next().text();
			quantity = iterator.next().text();
			tradeType = iterator.next().text();
			price = iterator.next().text();
			amount = iterator.next().text();

				XMTrade trade = convertXMTrade(stockCode, stockName,
						marketValue, executionTime, quantity, tradeType, price,
						amount, executionDate);
				if(trade!=null)
					trades.add(trade);
			} catch (Exception e) {
				System.out.println("Fail to convert in XMTrade : " + stockCode
						+ " ");
				e.printStackTrace();
			}

		}

		return trades;
	}

	private XMTrade convertXMTrade(String stockCode, String stockName,
			String marketValue, String executionTime, String quantity,
			String tradeType, String price, String amount, Date executionDate) {

		// Format the time
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = null;

		// Add new trade
		XMTrade trade = new XMTrade();

		try {
			date = formatter.parse(executionTime);

			trade.setAmount(new BigDecimal(amount.replaceAll(",", "")));

			trade.setExecutionDate(executionDate);
			trade.setExecutionTime(new Time(date.getTime()));
			trade.setLastUpdatedTime(new Timestamp((new Date()).getTime()));
			try {
				trade.setMarketValue(new BigDecimal(marketValue.replaceAll(",",
						"")));
			} catch (Exception e) {
				marketValue = "999,999,999.99";
				trade.setMarketValue(new BigDecimal(marketValue.replaceAll(",",
						"")));
			}
			trade.setPrice(new BigDecimal(price.replaceAll(",", "")));
			trade.setQuantity(Integer.parseInt(quantity.replace(",", "")));
			trade.setStockCode(stockCode);
			trade.setStockName(stockName);
			trade.setTradeType(tradeType);

		} catch (ParseException e) {
			e.printStackTrace();
			trade = null;
		}

		return trade;
	}

}

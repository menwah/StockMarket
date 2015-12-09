package com.stockMarket.Parse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.stockMarket.model.BalanceSheet;

public class ParseBalanceSheetRecord {

	public List<BalanceSheet> parseFromHTML(String html, String stockCode) {

		List<BalanceSheet> records = new ArrayList<BalanceSheet>();

		BalanceSheet record = new BalanceSheet();

		Document doc = Jsoup.parse(html);

		// Retrieve Balance Sheet Info
		Element balanceSheetInfo = doc.getElementsByClass("grid_11").first();

		Iterator<Element> balanceSheetInfoIterator = balanceSheetInfo.select(
				"table").iterator();

		Iterator<Element> balanceSheetOverviewInfoIterator = balanceSheetInfoIterator
				.next().select("td").iterator();

		while (balanceSheetOverviewInfoIterator.hasNext()) {

			String balanceSheetinfo = balanceSheetOverviewInfoIterator.next()
					.text();

			if (balanceSheetinfo.compareTo("Closing Date") == 0) {
				while (true) {
					String value = balanceSheetOverviewInfoIterator.next()
							.text().replace("/", "");

					if ((value.compareTo("Trend") == 0)||(value.compareTo("") == 0))
						break;

					BalanceSheet tmpRecord = new BalanceSheet();
					tmpRecord.setStockCode(stockCode);
					tmpRecord.setYearMonth(value);

					records.add(tmpRecord);
				}
			}

			if (balanceSheetinfo.compareTo("Fixed Assets") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setFixedAssets(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Investments") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setInvestments(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Other Assets") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setOtherAssets(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Cash On Hand") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setCashOnHand(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Receivables") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setReceivables(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Inventory") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setInvestory(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Other Current Assets") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setOtherCurrentAssets(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Total Assets") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setTotalAssets(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Long Term Debt") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setLongTermDebt(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Other Long Term Liabilities") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setOtherLongTermLiabilities(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Payables") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setPayables(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Taxation") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setTaxation(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Short Term Debt") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setShortTermDebt(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Other Current Liabilities") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setOtherCurrentLiabilities(validateFormat(value));
					records.set(i, record);
				}
			}
			
			if (balanceSheetinfo.compareTo("Total Liabilities") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setTotalLiabilities(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Total Equity") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setTotalEquity(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Currency") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					record = records.get(i);
					record.setCurrency(value);
					records.set(i, record);
				}
			}
			
			if (balanceSheetinfo.compareTo("Unit") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetOverviewInfoIterator.next()
							.text();
					
					if(value.compareTo("Thousand")==0)
						value = "T";
					else if(value.compareTo("Million")==0)
						value = "M";
					
					
					record = records.get(i);
					record.setUnit(value);
					records.set(i, record);
				}
			}

		}

		// Retrieve Balance Sheet Total Info
		Element balanceSheetTotalInfo = doc.getElementById("cp_pPLTable2");

		Iterator<Element> balanceSheetTotalTableInfoIterator = balanceSheetTotalInfo
				.select("table").iterator();

		Iterator<Element> balanceSheetTotalOverviewTableInfoIterator = balanceSheetTotalTableInfoIterator
				.next().select("td").iterator();

		while (balanceSheetTotalOverviewTableInfoIterator.hasNext()) {

			String balanceSheetinfo = balanceSheetTotalOverviewTableInfoIterator.next()
					.text();

			if (balanceSheetinfo.compareTo("Total Debt") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetTotalOverviewTableInfoIterator.next()
							.text();
					record = records.get(i);
					record.setTotalDebt(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Net Assets") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetTotalOverviewTableInfoIterator.next()
							.text();
					record = records.get(i);
					record.setNetAssets(validateFormat(value));
					records.set(i, record);
				}
			}

			if (balanceSheetinfo.compareTo("Intangible Assets") == 0) {

				for (int i = 0; i < records.size(); i++) {

					String value = balanceSheetTotalOverviewTableInfoIterator.next()
							.text();
					record = records.get(i);
					record.setIntangibleAssets(validateFormat(value));
					records.set(i, record);
				}
			}
		}

		for (int i = 0; i < records.size(); i++) {

			record = records.get(i);
			record.setLastUpdatedTime(new Timestamp((new Date()).getTime()));
			records.set(i, record);
		}

		return records;
	}

	public BigDecimal validateFormat(String value) {

		BigDecimal tmp;

		value = value.replace(",", "");

		if (value.compareTo("-") == 0) {
			tmp = new BigDecimal("0");
		} else if (value.contains("K")) {
			tmp = new BigDecimal(value.substring(0, value.indexOf("K")));
			tmp = tmp.multiply(new BigDecimal("1000"));
		} else if (value.contains("M")) {
			tmp = new BigDecimal(value.substring(0, value.indexOf("M")));
			tmp = tmp.multiply(new BigDecimal("1000000"));
		} else if (value.contains("B")) {
			tmp = new BigDecimal(value.substring(0, value.indexOf("B")));
			tmp = tmp.multiply(new BigDecimal("1000000000"));
		} else {
			tmp = new BigDecimal(value);
		}

		return tmp;

	}

}

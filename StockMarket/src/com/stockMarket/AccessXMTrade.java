package com.stockMarket;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.stockMarket.dao.XMTradeDao;
import com.stockMarket.model.XMTrade;

public class AccessXMTrade {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        
		XMTradeDao dao = new XMTradeDao();

		//Format the decimal information
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		
		//Format the time
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = null;
		 
		try {
			date = formatter.parse("13:12:11");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Add new trade
        XMTrade trade = new XMTrade();
        try {
			trade.setAmount((BigDecimal) decimalFormat.parse("1000"));

        trade.setExecutionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-13"));
        trade.setExecutionTime(new Time(date.getTime()));
        trade.setLastUpdatedTime(new Timestamp((new Date()).getTime()));
        trade.setMarketValue((BigDecimal) decimalFormat.parse("1000"));
        trade.setPrice((BigDecimal) decimalFormat.parse("10"));
        trade.setQuantity(1000);
        trade.setStockCode("0112");
        trade.setStockName("Dummy");
        trade.setTradeType("X");
       
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        dao.addXMTrade(trade);
        System.out.println("Added Successfully");
//
//        // Update trade
        trade.setStockCode("2222");
        trade.setId(1);
        dao.updateXMTrade(trade);
        System.out.println("Updated Successfully");
        
        // Delete trade
        //dao.deleteXMTrade(2);

        // Get all trades
        for (XMTrade iter : dao.getAllXMTrades()) {
            System.out.println(iter);
        }

        // Get trade by id
        System.out.println(dao.getXMTradeById(1));
	}

}

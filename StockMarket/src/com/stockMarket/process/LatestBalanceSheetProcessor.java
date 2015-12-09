package com.stockMarket.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.stockMarket.dao.BalanceSheetDao;
import com.stockMarket.dao.LatestBalanceSheetDao;
import com.stockMarket.model.BalanceSheet;
import com.stockMarket.model.LatestBalanceSheet;

public class LatestBalanceSheetProcessor {

	public void importData(){
		
		//Clear previous data
		LatestBalanceSheetDao lbsDao = new LatestBalanceSheetDao();
		lbsDao.deleteAllLatestBalanceSheet();
		
		//Retrieve latest data
		BalanceSheetDao bsDao = new BalanceSheetDao();
		List<BalanceSheet> sheets = bsDao.getLatestBalanceSheet();
		
		//Loop and parse it
		for(int i=0; i<sheets.size(); i++){
			BalanceSheet bSheet = (BalanceSheet)sheets.get(i);
			
			LatestBalanceSheet lbSheet = this.transform(bSheet);
			
			lbsDao.addLatestBalanceSheet(lbSheet);
		}
		
	}
	
	private LatestBalanceSheet transform(BalanceSheet sheet){
		
		LatestBalanceSheet lbSheet = new LatestBalanceSheet();
		
		BigDecimal multipler = null;
		
		if(sheet.getUnit().compareTo("T")==0)
			multipler = new BigDecimal("1000");
		else
			multipler = new BigDecimal("1000000");
		
		lbSheet.setStockCode(sheet.getStockCode());
		
		lbSheet.setCashOnHand(formatBigDecimal(sheet.getCashOnHand(),multipler));
		
		lbSheet.setCurrency(sheet.getCurrency());
		
		lbSheet.setFixedAssets(formatBigDecimal(sheet.getFixedAssets(),multipler));
		
		lbSheet.setIntangibleAssets(formatBigDecimal(sheet.getIntangibleAssets(),multipler));
		
		lbSheet.setInvestments(formatBigDecimal(sheet.getInvestments(),multipler));
		
		lbSheet.setInvestory(formatBigDecimal(sheet.getInvestory(),multipler));
		
		lbSheet.setLongTermDebt(formatBigDecimal(sheet.getLongTermDebt(),multipler));
		
		lbSheet.setNetAssets(formatBigDecimal(sheet.getNetAssets(),multipler));
		
		lbSheet.setOtherAssets(formatBigDecimal(sheet.getOtherAssets(),multipler));
		
		lbSheet.setOtherCurrentAssets(formatBigDecimal(sheet.getOtherCurrentAssets(),multipler));
		
		lbSheet.setOtherCurrentLiabilities(formatBigDecimal(sheet.getOtherCurrentLiabilities(),multipler));
		
		lbSheet.setOtherLongTermLiabilities(formatBigDecimal(sheet.getOtherLongTermLiabilities(),multipler));
		
		lbSheet.setPayables(formatBigDecimal(sheet.getPayables(),multipler));
		
		lbSheet.setReceivables(formatBigDecimal(sheet.getReceivables(),multipler));
		
		lbSheet.setShortTermDebt(formatBigDecimal(sheet.getShortTermDebt(),multipler));
		
		lbSheet.setTaxation(formatBigDecimal(sheet.getTaxation(),multipler));
		
		lbSheet.setTotalAssets(formatBigDecimal(sheet.getTotalAssets(),multipler));
		
		lbSheet.setTotalDebt(formatBigDecimal(sheet.getTotalDebt(),multipler));
		
		lbSheet.setTotalEquity(formatBigDecimal(sheet.getTotalEquity(),multipler));
		
		lbSheet.setTotalLiabilities(formatBigDecimal(sheet.getTotalLiabilities(),multipler));
		
		lbSheet.setUnit(sheet.getUnit());
		
		lbSheet.setYearMonth(sheet.getYearMonth());
		
		lbSheet.setLastUpdatedTime(new Timestamp((new Date()).getTime()));
		
		return lbSheet; 
	}
	
	private BigDecimal formatBigDecimal(BigDecimal value, BigDecimal multipler){
		
		if(value == null)
			return null;
		else
			return value.multiply(multipler);

	}
	

}

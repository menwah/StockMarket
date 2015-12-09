package com.stockMarket.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stockMarket.model.BalanceSheet;
import com.stockMarket.model.StockInfo;
import com.stockMarket.util.HibernateUtil;

public class BalanceSheetDao {

	public void addBalanceSheet(BalanceSheet sheet) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(sheet);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void deleteBalanceSheet(String stockCode) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            BalanceSheet sheet = (BalanceSheet) session.load(BalanceSheet.class, stockCode);
            session.delete(sheet);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void updateBalanceSheet(BalanceSheet sheet) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(sheet);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public List<BalanceSheet> getAllBalanceSheets() {
        List<BalanceSheet> users = new ArrayList<BalanceSheet>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from BalanceSheet").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }

    public List<BalanceSheet> getBalanceSheetByStockCode(String stockCode) {
        Transaction trns = null;
        List<BalanceSheet> records = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from BalanceSheet where stockCode = :stockCode";
            Query query = session.createQuery(queryString);
            query.setString("stockCode", stockCode);
            records = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return records;
    }
    
    public List<BalanceSheet> getLatestBalanceSheet() {
        Transaction trns = null;
        List<BalanceSheet> records = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = " SELECT b1.* FROM balance_sheet AS b1 LEFT JOIN balance_sheet AS b2 " +
            					 " ON (b1.stock_code = b2.stock_code AND b1.balance_sheet_year_month < b2.balance_sheet_year_month) " +
            					 " WHERE b2.balance_sheet_year_month IS NULL";
            SQLQuery query = session.createSQLQuery(queryString);
            query.addEntity(BalanceSheet.class);
            records = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return records;
    }
    
    public BalanceSheet getBalanceSheetByStockCodeAndYearMonth(String stockCode, String yearMonth) {
    	BalanceSheet sheet = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from BalanceSheet where stockCode = :stockCode and yearMonth = :yearMonth";
            Query query = session.createQuery(queryString);
            query.setString("stockCode", stockCode);
            query.setString("yearMonth", yearMonth);
            
            sheet = (BalanceSheet) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return sheet;
    }
    
    public void addOrUpdateBalanceSheet(BalanceSheet sheet) {
    	
    	if(getBalanceSheetByStockCodeAndYearMonth(sheet.getStockCode(),sheet.getYearMonth())==null)
    		addBalanceSheet(sheet);
    	else
    		updateBalanceSheet(sheet);
  
    }
    

}

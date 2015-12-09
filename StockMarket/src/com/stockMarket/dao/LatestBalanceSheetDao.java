package com.stockMarket.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stockMarket.model.BalanceSheet;
import com.stockMarket.model.LatestBalanceSheet;
import com.stockMarket.model.StockInfo;
import com.stockMarket.util.HibernateUtil;

public class LatestBalanceSheetDao {

	public void addLatestBalanceSheet(LatestBalanceSheet sheet) {
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

    public void deleteLatestBalanceSheetByStockCode(String stockCode) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            LatestBalanceSheet sheet = (LatestBalanceSheet) session.load(LatestBalanceSheet.class, stockCode);
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

    public void deleteAllLatestBalanceSheet() {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("Delete from LatestBalanceSheet");
            query.executeUpdate();
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
    
    public void updateLatestBalanceSheet(LatestBalanceSheet sheet) {
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

    public List<LatestBalanceSheet> getAllLatestBalanceSheet() {
        List<LatestBalanceSheet> users = new ArrayList<LatestBalanceSheet>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from LatestBalanceSheet").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }

    public List<LatestBalanceSheet> getLatestBalanceSheetByStockCode(String stockCode) {
        Transaction trns = null;
        List<LatestBalanceSheet> records = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from LatestBalanceSheet where stockCode = :stockCode";
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
    
    public LatestBalanceSheet getLatestBalanceSheetByStockCodeAndYearMonth(String stockCode, String yearMonth) {
    	LatestBalanceSheet sheet = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from LatestBalanceSheet where stockCode = :stockCode and yearMonth = :yearMonth";
            Query query = session.createQuery(queryString);
            query.setString("stockCode", stockCode);
            query.setString("yearMonth", yearMonth);
            
            sheet = (LatestBalanceSheet) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return sheet;
    }
    
    public void addOrUpdateLatestBalanceSheet(LatestBalanceSheet sheet) {
    	
    	if(getLatestBalanceSheetByStockCodeAndYearMonth(sheet.getStockCode(),sheet.getYearMonth())==null)
    		addLatestBalanceSheet(sheet);
    	else
    		updateLatestBalanceSheet(sheet);
  
    }
    

}

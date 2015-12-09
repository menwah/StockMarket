package com.stockMarket.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stockMarket.model.StockInfo;
import com.stockMarket.util.HibernateUtil;

public class StockInfoDao {

	public void addStockInfo(StockInfo stock) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(stock);
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

    public void deleteStockInfo(String stockCode) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            StockInfo stock = (StockInfo) session.load(StockInfo.class, stockCode);
            session.delete(stock);
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
    
    public void deleteAllStockInfo() {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("Delete from StockInfo");
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

    public void updateStockInfo(StockInfo stock) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(stock);
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

    public List<StockInfo> getAllStockInfos() {
        List<StockInfo> users = new ArrayList<StockInfo>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from StockInfo").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }

    public StockInfo getStockInfoByStockCode(String stockCode) {
    	StockInfo stock = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from StockInfo where stockCode = :stockCode";
            Query query = session.createQuery(queryString);
            query.setString("stockCode", stockCode);
            stock = (StockInfo) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return stock;
    }
    
    public StockInfo getStockInfoByStockCodeAndToday(String stockCode) {
    	StockInfo stock = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from StockInfo where stockCode = :stockCode and lastUpdatedTime >= :updatedDate";
            Query query = session.createQuery(queryString);
            query.setString("stockCode", stockCode);
            
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            
            query.setDate("updatedDate", calendar.getTime());
            stock = (StockInfo) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return stock;
    }
    
	public void addOrUpdateStockInfo(StockInfo stock) {
        
		StockInfo tmpStock = getStockInfoByStockCode(stock.getStockCode());
		
		if(tmpStock == null){
			addStockInfo(stock);
		}
		else{
			updateStockInfo(stock);
		}
    }

}

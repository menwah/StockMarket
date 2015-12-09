package com.stockMarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stockMarket.model.CASSStock;
import com.stockMarket.util.HibernateUtil;

public class CASSStockDao {

	public void addCASSStock(CASSStock stock) {
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

    public void deleteCASSStock(String stockCode) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            CASSStock stock = (CASSStock) session.load(CASSStock.class, stockCode);
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

    public void deleteAllCASSStock() {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("Delete from CASSStock");
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
    
    public void updateCASSStock(CASSStock stock) {
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

    public List<CASSStock> getAllCASSStocks() {
        List<CASSStock> stocks = new ArrayList<CASSStock>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            stocks = session.createQuery("from CASSStock").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return stocks;
    }

    public CASSStock getCASSStockByStockCode(String stockCode) {
    	CASSStock stock = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from CASSStock where stockCode = :stockCode";
            Query query = session.createQuery(queryString);
            query.setString("stockCode", stockCode);
            stock = (CASSStock) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return stock;
    }
    
	public void addOrUpdateCASSStock(CASSStock stock) {
        
		CASSStock tmpStock = getCASSStockByStockCode(stock.getStockCode());
		
		if(tmpStock == null){
			addCASSStock(stock);
		}
		else{
			updateCASSStock(stock);
		}
    }

}

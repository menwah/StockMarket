package com.stockMarket.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stockMarket.model.XMTrade;
import com.stockMarket.util.HibernateUtil;

public class XMTradeDao {

    public void addXMTrade(XMTrade trade) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(trade);
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

    public void deleteXMTrade(int id) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            XMTrade trade = (XMTrade) session.load(XMTrade.class, new Integer(id));
            session.delete(trade);
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

    public void updateXMTrade(XMTrade trade) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(trade);
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

    public List<XMTrade> getAllXMTrades() {
        List<XMTrade> trades = new ArrayList<XMTrade>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            trades = session.createQuery("from XMTrade").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return trades;
    }
    
    public List<XMTrade> getXMTradesByExecutionDate(Date executionDate) {
        List<XMTrade> trades = new ArrayList<XMTrade>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("from XMTrade where executionDate = :executionDate");
            query.setParameter("executionDate", executionDate);
            trades = query.list();
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return trades;
    }

    public void deleteXMTradeByExecutionDate(Date executionDate) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	trns = session.beginTransaction();
            String queryString = "delete XMTrade where executionDate = :executionDate";
            Query query = session.createQuery(queryString);
            query.setDate("executionDate", executionDate);
            query.executeUpdate();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public XMTrade getXMTradeById(int id) {
    	XMTrade trade = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from XMTrade where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            trade = (XMTrade) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return trade;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernateonetomany.dao;

import hibernateonetomany.SessionFactoryUtil;
import hibernateonetomany.models.Address;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Tharanga
 */
public class AddressDAOImpl implements AddressDAO{
    
    private SessionFactory sessionFactory;
    
    public AddressDAOImpl(){
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer insertAddress(Address address) {
        Integer id = null;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            id = (Integer)session.save(address);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
   
        return id;
        
    }

    @Override
    public void updateAddress(Address address) {
        
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            session.update(address);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }

    }

    @Override
    public void removeAddress(Address address) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            session.delete(address);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public List<Address> listAddress() {
        List<Address> addresses = null;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            addresses = session.createQuery("FROM Address").list();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
        return addresses;
    }

    @Override
    public Address findAddress(Integer id) {
        Address address = null;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            address = (Address) session.get(Address.class, id);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
        return address;
    }
    
    
    
}

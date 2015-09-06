/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernateonetoone.dao;

import hibernateonetoone.SessionFactoryUtil;
import hibernateonetoone.models.Address;
import hibernateonetoone.models.Employee;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Tharanga
 */
public class EmployeeDAOImpl implements EmployeeDAO{

    private SessionFactory sessionFactory;
    
    public EmployeeDAOImpl(){
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Integer insertEmployee(Employee employee) {
        Integer id = null;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            id = (Integer)session.save(employee);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
   
        return id;
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void removeEmployee(Employee employee) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            session.delete(employee);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public List<Employee> listEmployee() {
        List<Employee> employees = null;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            employees = session.createQuery("FROM Employee").list();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
        return employees;
    }

    @Override
    public Employee findEmployee(Integer id) {
        Employee employee = null;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            employee = (Employee) session.get(Employee.class, id);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive())tx.rollback();
        }finally{
            session.close();
        }
        return employee;
    }
    
}

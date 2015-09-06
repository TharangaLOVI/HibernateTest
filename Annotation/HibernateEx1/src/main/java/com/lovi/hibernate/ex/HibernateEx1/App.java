package com.lovi.hibernate.ex.HibernateEx1;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lovi.hibernate.ex.models.Address;
import com.lovi.hibernate.ex.models.Employee;
import com.lovi.hibernate.ex.models.Office;
import com.lovi.hibernate.ex.models.Person;
import com.lovi.hibernate.ex.models.Stock;
import com.lovi.hibernate.ex.models.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//save multiple objects
        /*Stock stock1 = new Stock();
        stock1.setStockCode("S_CODE_1");
        stock1.setStockName("S_NAME_1");
        
        Stock stock2 = new Stock();
        stock2.setStockCode("S_CODE_2");
        stock2.setStockName("S_NAME_2");
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(stock1);
        session.save(stock2);
        session.getTransaction().commit();*/
    	//save multiple objects end
    	
    	//Embed object
    	/*User user1 = new User();
    	
    	Address address = new Address();
    	address.setCity("city 1");
    	address.setState("state 1");
    	
    	user1.setAddress(address);
    	
    	Office office1 = new Office();
    	office1.setOfficeId(1);
    	office1.setOfficeName("Office 1");
    	user1.getListOfOffice().add(office1);
    	
    	Office office2 = new Office();
    	office2.setOfficeId(2);
    	office2.setOfficeName("Office 2");
    	user1.getListOfOffice().add(office2);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(user1);
    	session.getTransaction().commit();*/
    	//Embed object end
    	
    	//Embed collection
    	/*
    	Office office1 = new Office();
    	office1.setOfficeName("OF_1");
    	
    	Office office2 = new Office();
    	office2.setOfficeName("OF_2");
    	
    	Employee employee = new Employee();
    	employee.setEmpId(1);
    	employee.setEmpName("emp_1");
    	employee.getOffices().add(office1);
    	employee.getOffices().add(office2);
    	//---------------------------------------
    	Office office3 = new Office();
    	office3.setOfficeName("OF_3");
    	
    	Office office4 = new Office();
    	office4.setOfficeName("OF_4");
    	
    	Employee employee2 = new Employee();
    	employee2.setEmpId(1);
    	employee2.setEmpName("emp_1");
    	employee2.getOffices().add(office3);
    	employee2.getOffices().add(office4);
    	
    	//------------------------------------------
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(employee);
    	session.save(employee2);
    	session.getTransaction().commit();
    	*/
    	//Embed collection End
    	
    	//Lazy Fetch
    	//store
    	Office office1 = new Office();
    	office1.setOfficeName("OF_1");
    	
    	Office office2 = new Office();
    	office2.setOfficeName("OF_2");
    	
    	Person person = new Person();
    	person.setEmpId(1);
    	person.setEmpName("person_1");
    	person.getOffices().add(office1);
    	person.getOffices().add(office2);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(person);
    	session.getTransaction().commit();
    	session.close();
    	
    	//-----------------------------------------------
    	session = sessionFactory.openSession();
    	/**
    	 * this is not load office list,
    	 * if member list is huge it take more to load,
    	 * get only first level members
    	 * so hibernate using Lazy Fetch method
    	 */
    	Person personRe = (Person) session.get(Person.class, 1);
    	
    	/**
    	 * Hibernate excute and get list of offices when only it need
    	 */
    	Collection<Office> offices = personRe.getOffices();
    	
    	for(Office office : offices){
    		System.out.println(office.getOfficeName());
    	}
    	
    	session.close();
    	
    }
}

package com.lovi.hibernate.ex.HibernateEx2;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lovi.hibernate.ex.models.Office;
import com.lovi.hibernate.ex.models.Person;
import com.lovi.hibernate.ex.models.Vehicle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Lazy Fetch
    	//store
    	Vehicle vehicle = new Vehicle();
    	vehicle.setVehicleName("v_1");
    	
    	Person person = new Person();
    	person.setPersonName("p 1");
    	person.setVehicle(vehicle);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(person);
    	session.save(vehicle);
    	session.getTransaction().commit();
    	session.close();
    }
}

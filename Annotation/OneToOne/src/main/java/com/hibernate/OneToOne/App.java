package com.hibernate.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.OneToOne.models.User;
import com.hibernate.OneToOne.models.Vehicle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	User user = new User();
    	user.setUserName("User1");
    	
    	Vehicle vehicle = new Vehicle();
    	vehicle.setVehicleName("Car");
    	
    	user.setVehicle(vehicle);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(user);
    	session.save(vehicle);
    	session.getTransaction().commit();
    	session.close();
    }
}

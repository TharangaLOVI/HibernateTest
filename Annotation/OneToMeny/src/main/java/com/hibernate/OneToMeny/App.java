package com.hibernate.OneToMeny;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.OneToMeny.models.User;
import com.hibernate.OneToMeny.models.Vehicle;


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
    	
    	Vehicle vehicle1 = new Vehicle();
    	vehicle1.setVehicleName("Car");
    	
    	Vehicle vehicle2 = new Vehicle();
    	vehicle2.setVehicleName("Jeep");
    	
    	user.getVehicles().add(vehicle1);
    	user.getVehicles().add(vehicle2);
    	vehicle1.setUser(user);
    	vehicle2.setUser(user);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	//session.save(user);
    	//session.save(vehicle1);
    	//session.save(vehicle2);
    	
    	//ensure persist user
    	session.persist(user);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	System.out.print("OK");
    }
}

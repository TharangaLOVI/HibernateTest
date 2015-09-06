package com.hibernate.ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.ManyToMany.models.User;
import com.hibernate.ManyToMany.models.Vehicle;

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
    	vehicle1.getUsers().add(user);
    	vehicle2.getUsers().add(user);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	session.save(user);
    	session.save(vehicle1);
    	session.save(vehicle2);
    	
    	session.getTransaction().commit();
    	session.close();
    }
}

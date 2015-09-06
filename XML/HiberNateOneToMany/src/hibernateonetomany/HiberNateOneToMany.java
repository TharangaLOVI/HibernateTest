/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernateonetomany;

import hibernateonetomany.dao.AddressDAO;
import hibernateonetomany.dao.AddressDAOImpl;
import hibernateonetomany.dao.EmployeeDAO;
import hibernateonetomany.dao.EmployeeDAOImpl;
import hibernateonetomany.models.Address;
import hibernateonetomany.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Tharanga
 */
public class HiberNateOneToMany {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //example1();
        //example2();
        example3();
        //example4();
    }
    
    private static void example1(){
        Employee employee = new Employee();
        employee.setFirstName("f2");
        employee.setLastName("l3");
        employee.setSalary(50);
        
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        employeeDAO.insertEmployee(employee);
        
//        Address address1 = new Address();
//        address1.setCity("City1");
//        address1.setState("State1");
//        address1.setStreet("Street1");
//        address1.setZipcode("Zip1");
//        
//        Address address2 = new Address();
//        address2.setCity("City2");
//        address2.setState("State2");
//        address2.setStreet("Street2");
//        address2.setZipcode("Zip2");
//        
//        Address address3 = new Address();
//        address3.setCity("City3");
//        address3.setState("State3");
//        address3.setStreet("Street3");
//        address3.setZipcode("Zip3");
//        
//        employee.getAddresses().add(address1);
//        employee.getAddresses().add(address2);
//        employee.getAddresses().add(address3);
//        address1.setEmployee(employee);
//        address2.setEmployee(employee);
//        address3.setEmployee(employee);
//        
//        AddressDAO addressDAO = new AddressDAOImpl();
//        addressDAO.insertAddress(address1);
//        addressDAO.insertAddress(address2);
//        addressDAO.insertAddress(address3);
    } 
    
    private static void example2(){
        
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = employeeDAO.findEmployee(1);
        
        Address address1 = new Address();
        address1.setCity("City1");
        address1.setState("State1");
        address1.setStreet("Street1");
        address1.setZipcode("Zip1");
        
        Address address2 = new Address();
        address2.setCity("City2");
        address2.setState("State2");
        address2.setStreet("Street2");
        address2.setZipcode("Zip2");
        
        Address address3 = new Address();
        address3.setCity("City3");
        address3.setState("State3");
        address3.setStreet("Street3");
        address3.setZipcode("Zip3");
        
        address1.setEmployee(employee);
        address2.setEmployee(employee);
        address3.setEmployee(employee);
        
        AddressDAO addressDAO = new AddressDAOImpl();
        addressDAO.insertAddress(address1);
        addressDAO.insertAddress(address2);
        addressDAO.insertAddress(address3);
    } 
    
    /**
     * Lazy load explain
     */
    private static void example3(){
        
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        Employee employee = (Employee) session.get(Employee.class, 1);
        
        /**
         * if you request the address before session.close()
         * employee load address list.then employee contain the address list
         * can access address list after session.close()
         */
        //employee.getAddresses().size();
        
        session.close();
        
        /**
         * if lazy="true" (Lazy init is enables)
         * employee does not load second level of variables until request  ( employee.getAddresses() )
         * after session.close(); you can't access the address list from the employee object
         * because it does not contain address list.
         * 
         * lazy="true" is default.
         * lazy="false" load all the levels of variables so employee contain address list
         */
        System.out.println("Size : " + employee.getAddresses().size());
        
    }
    
    /**
     * invers = true|false explain
     */
    private static void example4(){
        
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();//Transaction is need whrn insert and update
        
        Employee employee = (Employee) session.get(Employee.class, 1);
        
        Address address1 = new Address();
        address1.setCity("City1");
        address1.setState("State1");
        address1.setStreet("Street1");
        address1.setZipcode("Zip1");
        
        address1.setEmployee(employee);
        employee.getAddresses().add(address1);
        
        /**
         * in this relation ship owner is Address.Employee Update query is not need
         * only execute Address update
         * but if Employee.setFirst() like changes (First level variable changing) happen Employee Update query is also execute
         */
        
        /**
         * if inverse="true"  [Relationship owner is Address]
         *      session.save(address1); => insert into Address (city, state, street, zipcode, EMP_ID, id) values (?, ?, ?, ?, ?, ?)
         * 
         * if inverse="false" [Relationship owner is Employee]
         *      session.save(address1); => insert into Address (city, state, street, zipcode, EMP_ID, id) values (?, ?, ?, ?, ?, ?)
         *                                  update Address set EMP_ID=? where id=?
         */
        
        session.save(address1);
        session.update(employee);
        
        
        session.getTransaction().commit();
        session.close();
    }
}

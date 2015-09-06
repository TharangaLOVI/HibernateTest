/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernateonetoone;

import hibernateonetoone.dao.AddressDAO;
import hibernateonetoone.dao.AddressDAOImpl;
import hibernateonetoone.dao.EmployeeDAO;
import hibernateonetoone.dao.EmployeeDAOImpl;
import hibernateonetoone.models.Address;
import hibernateonetoone.models.Employee;
import java.util.Iterator;

/**
 *
 * @author Tharanga
 */
public class HiberNateOneToOne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Read - http://www.mkyong.com/hibernate/inverse-true-example-and-explanation/
        //Read - http://www.mkyong.com/hibernate/hibernate-cascade-example-save-update-delete-and-delete-orphan/
        
        AddressDAO addressDAO = new AddressDAOImpl();
        
        Address address1 = new Address();
        address1.setState("State1");
        address1.setCity("City1");
        address1.setStreet("Stree1");
        address1.setZipcode("Zip1");
        
        Address address2 = new Address();
        address2.setState("State2");
        address2.setCity("City2");
        address2.setStreet("Stree2");
        address2.setZipcode("Zip2");
        
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
       
        Employee employee1 = new Employee();
        employee1.setFirstName("F1");
        employee1.setLastName("L1");
        employee1.setSalary(50);
        
        address1.setEmployee(employee1);
        
        Employee employee2 = new Employee();
        employee2.setFirstName("F2");
        employee2.setLastName("L2");
        employee2.setSalary(50);
        
        address2.setEmployee(employee2);
        
        employeeDAO.insertEmployee(employee1);
        employeeDAO.insertEmployee(employee2);
        
        /**
         * constrained="true" mean Address can't exists with out employee
         * so have to employee and then insert address whish related
         */
        addressDAO.insertAddress(address1);
        addressDAO.insertAddress(address2);
        
        
        
        for(Iterator iterator = addressDAO.listAddress().iterator() ; iterator.hasNext();){
            Address a1 = (Address)iterator.next();
            System.out.println("Address : " + a1.getId() + " " + a1.getCity());
        }
    }
    
    
    
}

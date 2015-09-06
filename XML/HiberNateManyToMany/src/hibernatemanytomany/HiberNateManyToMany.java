/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernatemanytomany;

import hibernatemanytomany.dao.AddressDAO;
import hibernatemanytomany.dao.AddressDAOImpl;
import hibernatemanytomany.dao.EmployeeDAO;
import hibernatemanytomany.dao.EmployeeDAOImpl;
import hibernatemanytomany.models.Address;
import hibernatemanytomany.models.Employee;

/**
 *
 * @author Tharanga
 */
public class HiberNateManyToMany {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        AddressDAO addressDAO = new AddressDAOImpl();
        
        Employee employee = new Employee();
        employee.setFirstName("F1");
        employee.setLastName("L1");
        employee.setSalary(50);
        
        Address address1 = new Address();
        address1.setState("State1");
        address1.setCity("City1");
        address1.setStreet("Street1");
        address1.setZipcode("Zip1");
        
        Address address2 = new Address();
        address2.setState("State2");
        address2.setCity("City2");
        address2.setStreet("Street2");
        address2.setZipcode("Zip2");
        
        Address address3 = new Address();
        address3.setState("State3");
        address3.setCity("City3");
        address3.setStreet("Street3");
        address3.setZipcode("Zip3");
        
        employee.getAddresses().add(address1);
        employee.getAddresses().add(address2);
        employee.getAddresses().add(address3);
        
        employeeDAO.insertEmployee(employee);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernateonetomany.dao;

import hibernateonetomany.models.Address;
import java.util.List;

/**
 *
 * @author Tharanga
 */
public interface AddressDAO {
    
    public Integer insertAddress(Address address);
    
    public void updateAddress(Address address);
    
    public void removeAddress(Address address);
    
    public List<Address> listAddress();
    
    public Address findAddress(Integer id);
    
}

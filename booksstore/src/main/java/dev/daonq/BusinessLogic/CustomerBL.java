package dev.daonq.BusinessLogic;

import dev.daonq.DataAccess.CustomerDA;
import dev.daonq.Entity.Customer;

public class CustomerBL {
    CustomerDA da = new CustomerDA();
    public Customer getCustomerByEmail(String Email){
        try {
            Customer customer = da.getCustomerByEmail(Email);
            if(customer != null){
                return customer;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
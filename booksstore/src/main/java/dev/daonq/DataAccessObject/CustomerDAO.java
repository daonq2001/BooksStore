package dev.daonq.DataAccessObject;

import dev.daonq.Entity.Customer;

public interface CustomerDAO {
    Customer getCustomerByEmail(String Email);
}
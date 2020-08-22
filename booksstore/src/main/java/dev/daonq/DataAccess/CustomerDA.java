package dev.daonq.DataAccess;

import java.sql.ResultSet;

import dev.daonq.DataAccessObject.CustomerDAO;
import dev.daonq.Entity.Customer;

public class CustomerDA implements CustomerDAO {

    @Override
    public Customer getCustomerByEmail(String Email) {
        try {
            String sql = "SELECT * FROM Customers WHERE Email = '" + Email + "';";
            DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(sql);
            Customer customer = null;
            while(rs.next()){
                customer = new Customer();
                customer.setID(rs.getInt("ID"));
                customer.setEmail(rs.getString("Email"));
                customer.setPassword(rs.getString("Password"));
                customer.setName(rs.getString("Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setPhone(rs.getString("Phone"));
            }
            DBHelper.closeConnection();
            return customer;
        } catch (Exception e) {
            return null;
        }
    }
    
}
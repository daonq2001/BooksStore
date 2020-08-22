package dev.daonq.DataAccessObject;

import java.util.ArrayList;

import dev.daonq.Entity.Order;

public interface OrderDAO {
    Boolean insertOrder(int CustomerID, Double thanhtien);
    Order getOrderByCustomerID(int CustomerID);
    ArrayList<Order> getListOrderByCustomerID(int CustomerID);
}
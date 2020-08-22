package dev.daonq.BusinessLogic;

import java.util.ArrayList;

import dev.daonq.DataAccess.OrderDA;
import dev.daonq.Entity.Order;

public class OrderBL {
    OrderDA da = new OrderDA();

    public ArrayList<Order> getListOrderByCustomerID(int CustomerID) {
        try {
            ArrayList<Order> listOrders = da.getListOrderByCustomerID(CustomerID);
            if (listOrders != null) {
                return listOrders;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
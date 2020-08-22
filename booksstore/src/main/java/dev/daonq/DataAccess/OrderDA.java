package dev.daonq.DataAccess;

import java.sql.ResultSet;
import java.util.ArrayList;

import dev.daonq.DataAccessObject.OrderDAO;
import dev.daonq.Entity.Order;

public class OrderDA implements OrderDAO {

    @Override
    public Boolean insertOrder(Order order) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order getOrderByCustomerID(int CustomerID) {

        return null;
    }

    @Override
    public ArrayList<Order> getListOrderByCustomerID(int CustomerID) {
        try {
            String sql = "SELECT * FROM Orders WHERE CustomerID = " + CustomerID + ";";
            DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(sql);
            ArrayList<Order> listOrder = new ArrayList<>();
            while(rs.next()){
                Order order = getOrder(rs);
                listOrder.add(order);
            }
            DBHelper.closeConnection();
            return listOrder;
        } catch (Exception e) {
            return null;
        }
    }

    private Order getOrder(ResultSet rs) {
        try {
            Order order = new Order();
            order.setID(rs.getInt("ID"));
            order.setCustomerID(rs.getInt("CustomerID"));
            order.setDate(rs.getTimestamp("Date"));
            order.setTotalDue(rs.getDouble("TotalDue"));
            return order;
        } catch (Exception e) {
            return null;
        }
    }

}
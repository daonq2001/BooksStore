package dev.daonq.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dev.daonq.DataAccessObject.OrderDAO;
import dev.daonq.Entity.Order;

public class OrderDA implements OrderDAO {

    @Override
    public Boolean insertOrder(int CustomerID, Double thanhtien) {
        try {
            Connection con = DBHelper.getConnection();
            DBHelper.executeQuery("SET FOREIGN_KEY_CHECKS = 0;");
            String sql = "INSERT INTO Orders(CustomerID, TotalDue) values (?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, CustomerID);
            preparedStatement.setDouble(2, thanhtien);
            Boolean b = preparedStatement.execute();
            DBHelper.executeQuery("SET FOREIGN_KEY_CHECKS = 1;");
            DBHelper.closeConnection();
            return b;
        } catch (Exception e) {
            System.out.println("Đã có lỗi xảy ra trong quá trình thêm dữ liệu vào bảng Order.");
            return false;
        }
    }

    @Override
    public Order getOrderByCustomerID(int CustomerID) {
        try {
            String sql = "SELECT * FROM Orders WHERE CustomerID = " + CustomerID + " ORDER BY ID DESC LIMIT 1;";
            DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(sql);
            Order order = new Order();
            while (rs.next()) {
                order.setID(rs.getInt("ID"));
                order.setCustomerID(rs.getInt("CustomerID"));
                order.setDate(rs.getTimestamp("Date"));
            }
            DBHelper.closeConnection();
            return order;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<Order> getListOrderByCustomerID(int CustomerID) {
        try {
            String sql = "SELECT * FROM Orders WHERE CustomerID = " + CustomerID + ";";
            DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(sql);
            ArrayList<Order> listOrder = new ArrayList<>();
            while (rs.next()) {
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
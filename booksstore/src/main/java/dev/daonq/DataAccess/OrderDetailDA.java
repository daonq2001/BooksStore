package dev.daonq.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dev.daonq.DataAccessObject.OrderDetailDAO;
import dev.daonq.Entity.OrderDetail;

public class OrderDetailDa implements OrderDetailDAO {

    @Override
    public Boolean insertOrderDetail(OrderDetail orderDetail) {
        try {
            Connection con = DBHelper.getConnection();
            DBHelper.executeQuery("SET FOREIGN_KEY_CHECKS = 0;");
            String sql = "INSERT INTO OrderDetails(BookID, OrderID, Amount) values (?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, orderDetail.getBookID());
            preparedStatement.setInt(2, orderDetail.getOrderID());
            preparedStatement.setInt(3, orderDetail.getAmount());
            Boolean b = preparedStatement.execute();
            DBHelper.executeQuery("SET FOREIGN_KEY_CHECKS = 1;");
            DBHelper.closeConnection();
            return b;
        } catch (Exception e) {
            System.out.println("Đã có lỗi xảy ra trong quá trình thêm dữ liệu vào bảng OrderDetail.");
            return false;
        }
    }

    @Override
    public ArrayList<OrderDetail> getListOrderDetailByOrderID(int OrderID) {
        try {
            String sql = "SELECT * FROM OrderDetails WHERE OrderID = " + OrderID + ";";
            DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(sql);
            ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
            while (rs.next()) {
                OrderDetail orderDetail = getOrderDetail(rs);
                orderDetails.add(orderDetail);
            }
            DBHelper.closeConnection();
            return orderDetails;
        } catch (Exception e) {
            return null;
        }
    }

    private OrderDetail getOrderDetail(ResultSet rs) {
        try {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(rs.getInt("OrderID"));
            orderDetail.setBookID(rs.getInt("BookID"));
            orderDetail.setAmount(rs.getInt("Amount"));
            return orderDetail;
        } catch (Exception e) {
            return null;
        }
    }

}
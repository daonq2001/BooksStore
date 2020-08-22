package dev.daonq.DataAccess;

import java.sql.ResultSet;
import java.util.ArrayList;

import dev.daonq.DataAccessObject.OrderDetailDAO;
import dev.daonq.Entity.OrderDetail;

public class OrderDetailDa implements OrderDetailDAO {

    @Override
    public Boolean insertOrderDetail(OrderDetail orderDetail) {

        return null;
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
package dev.daonq.DataAccessObject;

import java.util.ArrayList;

import dev.daonq.Entity.OrderDetail;

public interface OrderDetailDAO {
    Boolean insertOrderDetail(OrderDetail orderDetail);
    ArrayList<OrderDetail> getListOrderDetailByOrderID(int OrderID);
}
package dev.daonq.BusinessLogic;

import java.util.ArrayList;

import dev.daonq.DataAccess.OrderDetailDa;
import dev.daonq.Entity.OrderDetail;

public class OrderDetailBL {
    OrderDetailDa da = new OrderDetailDa();

    public ArrayList<OrderDetail> getListOrderDetailByOrderID(int OrderID) {
        try {
            ArrayList<OrderDetail> listOrderDetails = da.getListOrderDetailByOrderID(OrderID);
            if (listOrderDetails != null) {
                return listOrderDetails;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean insertOrderDetail(OrderDetail orderDetail) {
        return da.insertOrderDetail(orderDetail);
    }
}
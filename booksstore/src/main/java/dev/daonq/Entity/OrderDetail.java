package dev.daonq.Entity;

public class OrderDetail {
    private int BookID;
    private int OrderID;
    private int Amount;

    public int getBookID() {
        return this.BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public int getOrderID() {
        return this.OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getAmount() {
        return this.Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

}
package dev.daonq.Entity;

import java.sql.Timestamp;

public class Order {
    private int ID;
    private int CustomerID;
    private Timestamp Date;
    private Double TotalDue;

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomerID() {
        return this.CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public Timestamp getDate() {
        return this.Date;
    }

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

    public Double getTotalDue() {
        return this.TotalDue;
    }

    public void setTotalDue(Double TotalDue) {
        this.TotalDue = TotalDue;
    }

}
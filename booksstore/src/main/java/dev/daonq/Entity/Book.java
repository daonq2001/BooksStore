package dev.daonq.Entity;

public class Book {
    private int ID;
    private int NumberofPages;
    private Double Price;
    private String Title;
    private String Author;
    private String IssuingCompany;
    private String DateofPublication;
    private String Dimensions;
    private String CoverType;
    private String PublishingCompany;
    private String SKU;

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumberofPages() {
        return this.NumberofPages;
    }

    public void setNumberofPages(int NumberofPages) {
        this.NumberofPages = NumberofPages;
    }

    public Double getPrice() {
        return this.Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAuthor() {
        return this.Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getIssuingCompany() {
        return this.IssuingCompany;
    }

    public void setIssuingCompany(String IssuingCompany) {
        this.IssuingCompany = IssuingCompany;
    }

    public String getDateofPublication() {
        return this.DateofPublication;
    }

    public void setDateofPublication(String DateofPublication) {
        this.DateofPublication = DateofPublication;
    }

    public String getDimensions() {
        return this.Dimensions;
    }

    public void setDimensions(String Dimensions) {
        this.Dimensions = Dimensions;
    }

    public String getCoverType() {
        return this.CoverType;
    }

    public void setCoverType(String CoverType) {
        this.CoverType = CoverType;
    }

    public String getPublishingCompany() {
        return this.PublishingCompany;
    }

    public void setPublishingCompany(String PublishingCompany) {
        this.PublishingCompany = PublishingCompany;
    }

    public String getSKU() {
        return this.SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

}
package dev.daonq.DataAccess;

import java.sql.ResultSet;
import java.util.ArrayList;

import dev.daonq.DataAccessObject.BookDAO;
import dev.daonq.Entity.Book;

public class BookDA implements BookDAO {

    @Override
    public ArrayList<Book> getListBook() {
        try {
            ArrayList<Book> books = new ArrayList<>();
            String sql = "SELECT * FROM Books;";
            DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(sql);
            while(rs.next()){
                Book book = getBook(rs);
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Book getBookByID(int ID) {
        try {
            String sql = "SELECT * FROM Books WHERE ID = " + ID + ";";
            DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(sql);
            Book book = null;
            while (rs.next()) {
                book = getBook(rs);
            }
            DBHelper.closeConnection();
            return book;
        } catch (Exception e) {
            return null;
        }
    }

    private Book getBook(ResultSet rs) {
        try {
            Book book = new Book();
            book.setID(rs.getInt("ID"));
            book.setTitle(rs.getString("Title"));
            book.setPrice(rs.getDouble("Price"));
            book.setAuthor(rs.getString("Author"));
            book.setIssuingCompany(rs.getString("IssuingCompany"));
            book.setDateofPublication(rs.getString("DateofPublication"));
            book.setDimensions(rs.getString("Dimensions"));
            book.setCoverType(rs.getString("CoverType"));
            book.setNumberofPages(rs.getInt("NumberofPages"));
            book.setPublishingCompany(rs.getString("PublishingCompany"));
            book.setSKU(rs.getString("SKU"));
            return book;
        } catch (Exception e) {
            return null;
        }
    }

}
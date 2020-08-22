package dev.daonq.DataAccessObject;

import java.util.ArrayList;

import dev.daonq.Entity.Book;

public interface BookDAO {
    ArrayList<Book> getListBook();
    Book getBookByID(int ID);
}
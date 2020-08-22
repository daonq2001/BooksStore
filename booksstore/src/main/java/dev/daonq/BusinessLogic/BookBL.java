package dev.daonq.BusinessLogic;

import java.util.ArrayList;

import dev.daonq.DataAccess.BookDA;
import dev.daonq.Entity.Book;

public class BookBL {
    BookDA da = new BookDA();

    public ArrayList<Book> getListBook(){
        ArrayList<Book> books = da.getListBook();
        if(books != null){
            return books;
        }
        return null;
    }

    public Book getBookByID(int ID){
        if(ID > 999 && ID < 1100){
            Book book = da.getBookByID(ID);
            if(book != null){
                return book;
            }
        }
        return null;
    }
}
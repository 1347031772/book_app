package org.lxl.work.entity;


public class Book {
    private String books_name;
    private String books_price;

    public Book(String books_name, String books_price) {
        this.books_name = books_name;
        this.books_price = books_price;

    }

    public String getBooks_name() {
        return books_name;
    }

    public void setBooks_name(String books_name) {
        this.books_name = books_name;
    }

    public String getBooks_price() {
        return books_price;
    }

    public void setBooks_price(String books_price) {
        this.books_price = books_price;
    }


}

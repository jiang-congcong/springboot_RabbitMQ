package com.ccj.rabbitmq.entity;

public class Book {

    private String bookName;
    private String auther;

    public Book(String bookName, String auther) {
        this.bookName = bookName;
        this.auther = auther;
    }

    public Book() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", auther='" + auther + '\'' +
                '}';
    }
}

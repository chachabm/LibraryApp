package org.project.libraryapp;

public class BookWrapper {
    private final String title;
    private final String year;
    private final String author;

    public BookWrapper(Book book) {
        this.title = book.getTitle();
        this.year = String.valueOf(book.getYear());
        this.author = book.getAuthor();
    }
    public BookWrapper(String title, String year, String author) {
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }
}
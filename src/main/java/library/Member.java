package main.java.library;

import java.util.ArrayList;

public class Member {
    private int id;
    private String name;
    private ArrayList<String> borrowedBooks = new ArrayList<>();

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public ArrayList<String> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(String isbn) {
        borrowedBooks.add(isbn);
    }

    public void returnBook(String isbn) {
        borrowedBooks.remove(isbn);
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}

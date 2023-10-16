package org.example;
import java.util.Scanner;

public class Book {
    private int bookId;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;


    public int getId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckOut() {
        return isCheckedOut;
    }

    public void setCheckOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;

    }

    public void checkOut () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String userName = scanner.nextLine();

        this.isCheckedOut = true;
        checkedOutTo = userName;

        System.out.println("You have successfully checked out '" + title + "' with your name: " + checkedOutTo);
    }
    public void checkIn() {
        this.checkedOutTo = "";
        this.isCheckedOut = false;
    }

    public Book(int bookId, String isbn, String title, boolean isCheckOut, String checkedOutTo) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = isCheckOut;
        this.checkedOutTo = checkedOutTo;
    }

    public static void showAvailableBooks(Book[] books) {
        for (Book book : books) {
            if (!book.isCheckedOut) {
                String bookInfo = "ID: " + book.getId() + ", ISBN: " + book.getIsbn() + ", Title: " + book.getTitle();
                System.out.println(bookInfo);
            }
        }
    }

    public static void showCheckedOutBook(Book[] books) {
        for (Book book : books) {
            if (book.isCheckedOut) {
                String bookInfo = "ID: " + book.getId() + ", ISBN: " + book.getIsbn() + ", Title: " + book.getTitle() + ", Checked out by: " + book.getCheckedOutTo();
                System.out.println(bookInfo);
            }
        }

    }
}

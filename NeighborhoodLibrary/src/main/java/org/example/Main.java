package org.example;

import java.util.Scanner;
import static org.example.Book.showAvailableBooks;
import static org.example.Book.showCheckedOutBook;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book[] books = new Book[20];

        books[0] = new Book(1,"123","The Shadow of the Wind",true,"john");
        books[1] = new Book(2,"124","To Kill a Mockingbird",true,"mary");
        books[2] = new Book(3,"125","1984",false,"");
        books[3] = new Book(4,"125","The Great Gatsby",false,"");
        books[4] = new Book(5,"126","Pride and Prejudice",true,"Jane");
        books[5] = new Book(6,"126","The Catcher in the Rye",false,"");
        books[6] = new Book(7,"127","One Hundred Years of Solitude",true,"chris");
        books[7] = new Book(8,"128", "The Alchemist",true,"nathan");
        books[8] = new Book(9,"129","The Hobbit",false,"");
        books[9] = new Book(10,"140","Normal People",false,"");
        books[10] = new Book(11,"130","The Da Vinci Code",false,"");
        books[11] = new Book(12,"131","The Girl on the Train",false,"");
        books[12] = new Book(13,"132","Educated",false,"");
        books[13] = new Book(14,"133","Sapiens: A Brief History of Humankind",false,"");
        books[14] = new Book(15,"134","The Immortal Life of Henrietta Lacks",false,"");
        books[15] = new Book(16,"135","Becoming",false,"");
        books[16] = new Book(17,"136","The Silent Patient",false,"");
        books[17] = new Book(18,"137","Where the Crawdads Sing",false,"");
        books[18] = new Book(19,"138","The Night Circus",true,"adam");
        books[19] = new Book(20,"139","The Goldfinch",false,"");



        while(true) {
            System.out.println("What do you want to?");
            System.out.println("1 - Show Available Books");
            System.out.println("2 - Show Checked Out Books");
            System.out.println("3 - Exit - closes out of the application");
            System.out.println("Enter your command");

            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                    showAvailableBooks(books);
                    System.out.println("What would you like to do?");
                    System.out.println("1 - Select a book to check out");
                    System.out.println("2 - Exit - goes back to home screen");
                    System.out.println("Enter your command");

                    int availableBooksOptions = scanner.nextInt();
                    scanner.nextLine();


                    switch (availableBooksOptions) {
                        case 1:
                            showAvailableBooks(books);
                            System.out.println("Enter the book number to check out:");
                            int bookNumber = scanner.nextInt();
                            scanner.nextLine();

                            if (bookNumber >= 1 && bookNumber <= books.length) {
                                Book selectedbook = books[bookNumber - 1];
                                selectedbook.checkOut();
                            }
                            else{
                                System.out.println("Invalid book number");
                            }
                            break;

                        case 2:
                        default:
                            System.out.println("Returning to home screen.");
                            break;
                    }
                    break;

                case 2:
                    showCheckedOutBook(books);
                    System.out.println("Would you like to: ");
                    System.out.println(" C - Check in a book");
                    System.out.println(" X - Exit - goes back to home screen");
                    System.out.println("Enter a command letter: ");

                    String checkedOutBooksOptions = scanner.nextLine();

                    if (checkedOutBooksOptions.equalsIgnoreCase("c")) {
                        System.out.println("Enter the book number to check in: ");
                        int bookNumber = scanner.nextInt();
                        scanner.nextLine();

                        if (bookNumber >= 1 && bookNumber <= books.length) {
                            Book selectedBook = books[bookNumber - 1];
                            selectedBook.checkIn();
                        }
                        else {
                            System.out.println("Invalid book number.");
                        }
                    }
                    else if (checkedOutBooksOptions.equalsIgnoreCase("x")) {
                    }
                    default:
                        System.out.println("Invalid command. Returning to the home screen.");
                    break;
                    case 3:
                    System.exit(0);
                }
            }
        }
    }



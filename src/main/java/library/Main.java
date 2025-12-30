package library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== LIBRARY SYSTEM ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Register Member");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(isbn, title, author, true));
                }
                case 2 -> library.showBooks();
                case 3 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    library.registerMember(new Member(id, sc.nextLine()));
                }
                case 4 -> {
                    System.out.print("Member ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("ISBN: ");
                    library.borrowBook(id, sc.nextLine());
                }
                case 5 -> {
                    System.out.print("Member ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Days Late: ");
                    library.returnBook(id, isbn, sc.nextInt());
                }
                case 6 -> library.showStatistics();

                case 7 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}

package library;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    // Reservation system: ISBN → Member ID
    private HashMap<String, Integer> reservations = new HashMap<>();

    private final String BOOK_FILE = "data/books.txt";
    private final String MEMBER_FILE = "data/members.txt";

    public Library() {
        loadBooks();
        loadMembers();
    }

    /* ---------- BOOK & MEMBER MANAGEMENT ---------- */

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public void registerMember(Member member) {
        members.add(member);
        saveMembers();
    }

    public Book findBook(String isbn) {
        for (Book b : books)
            if (b.getIsbn().equals(isbn))
                return b;
        return null;
    }

    public Member findMember(int id) {
        for (Member m : members)
            if (m.getId() == id)
                return m;
        return null;
    }

    /* ---------- BORROW / RETURN ---------- */

    public void borrowBook(int memberId, String isbn) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid member or book!");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book not available. You can reserve it.");
            return;
        }

        // Reservation check
        if (reservations.containsKey(isbn) &&
            reservations.get(isbn) != memberId) {
            System.out.println("Book reserved by another member!");
            return;
        }

        book.setAvailable(false);
        member.borrowBook(isbn);
        reservations.remove(isbn);

        saveBooks();
        saveMembers();

        System.out.println("Book borrowed successfully.");
    }

    public void returnBook(int memberId, String isbn, int daysLate) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) return;

        book.setAvailable(true);
        member.returnBook(isbn);

        if (daysLate > 0)
            System.out.println("Fine: ₹" + daysLate * 5);

        saveBooks();
        saveMembers();
    }

    /* ---------- RESERVATION SYSTEM (ADVANCED FEATURE) ---------- */

    public void reserveBook(int memberId, String isbn) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid details!");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Book is available. No need to reserve.");
            return;
        }

        reservations.put(isbn, memberId);
        System.out.println("Book reserved successfully.");
    }

    /* ---------- DISPLAY & SEARCH ---------- */

    public void showBooks() {
        books.forEach(b ->
            System.out.println(
                b.getIsbn() + " | " +
                b.getTitle() + " | " +
                b.getAuthor() + " | " +
                (b.isAvailable() ? "Available" : "Borrowed")
            )
        );
    }

    /* ---------- STATISTICS ---------- */

    public void showStatistics() {
        long available = books.stream().filter(Book::isAvailable).count();
        long borrowed = books.size() - available;

        System.out.println("Total Books: " + books.size());
        System.out.println("Available: " + available);
        System.out.println("Borrowed: " + borrowed);
    }

    /* ---------- CSV EXPORT (ADVANCED FEATURE) ---------- */

    public void exportBooksToCSV() {
        FileHandler.exportBooksToCSV(books);
    }

    /* ---------- FILE LOAD / SAVE ---------- */

    private void loadBooks() {
        for (String line : FileHandler.readFile(BOOK_FILE)) {
            String[] d = line.split(",");
            books.add(new Book(d[0], d[1], d[2], Boolean.parseBoolean(d[3])));
        }
    }

    private void saveBooks() {
        ArrayList<String> data = new ArrayList<>();
        for (Book b : books) data.add(b.toString());
        FileHandler.writeFile(BOOK_FILE, data);
    }

    private void loadMembers() {
        for (String line : FileHandler.readFile(MEMBER_FILE)) {
            String[] d = line.split(",");
            members.add(new Member(Integer.parseInt(d[0]), d[1]));
        }
    }

    private void saveMembers() {
        ArrayList<String> data = new ArrayList<>();
        for (Member m : members) data.add(m.toString());
        FileHandler.writeFile(MEMBER_FILE, data);
    }
}

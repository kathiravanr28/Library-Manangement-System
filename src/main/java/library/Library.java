package library;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    private final String BOOK_FILE = "data/books.txt";
    private final String MEMBER_FILE = "data/members.txt";

    public Library() {
        loadBooks();
        loadMembers();
    }

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

    public void borrowBook(int memberId, String isbn) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null || !book.isAvailable()) {
            System.out.println("Borrow failed!");
            return;
        }

        book.setAvailable(false);
        member.borrowBook(isbn);
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

    public void showBooks() {
        books.forEach(b ->
            System.out.println(b.getIsbn() + " | " + b.getTitle() + " | " +
                               (b.isAvailable() ? "Available" : "Borrowed"))
        );
    }

    /* ---------- File Load/Save ---------- */

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

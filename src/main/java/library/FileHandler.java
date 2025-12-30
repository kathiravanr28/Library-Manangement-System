package library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static ArrayList<String> readFile(String path) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ignored) {}
        return lines;
    }

    public static void writeFile(String path, ArrayList<String> data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (String line : data) {
                pw.println(line);
            }
        } catch (IOException e) {
            System.out.println("File write error!");
        }
    }

     public static void exportBooksToCSV(List<Book> books) {
        String filePath = "data/books_export.csv";

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

            // CSV Header
            pw.println("ISBN,Title,Author,Available");

            // CSV Rows
            for (Book book : books) {
                pw.println(
                    book.getIsbn() + "," +
                    book.getTitle() + "," +
                    book.getAuthor() + "," +
                    book.isAvailable()
                );
            }

            System.out.println("Books exported successfully to " + filePath);

        } catch (IOException e) {
            System.out.println("Error exporting books to CSV!");
        }
    }
}

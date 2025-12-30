package library;

import java.io.*;
import java.util.ArrayList;

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
}

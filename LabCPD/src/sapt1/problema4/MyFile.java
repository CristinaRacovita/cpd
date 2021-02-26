package sapt1.problema4;

import java.io.*;
import java.util.Scanner;

public class MyFile {
    private final String name;
    private final PrintWriter writer;
    private int stringLength;

    public MyFile(String name) {
        PrintWriter auxWriter;
        this.name = name;
        try {
            auxWriter = new PrintWriter(name);
        } catch (FileNotFoundException e) {
            auxWriter = null;
            e.printStackTrace();
        }
        writer = auxWriter;
    }

    public void write(String str) {
        stringLength = str.length();

        writer.println(str);
        writer.close();
    }

    public char readFromStart(int index) {
        return readCharacterAtIndex(index);
    }

    public char readFromEnd(int index) {
        return readCharacterAtIndex(index);
    }

    private char readCharacterAtIndex(int index) {
        try {
            File file = new File(name);
            Scanner scanner = new Scanner(file);

            String line = scanner.nextLine();

            return line.charAt(index);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return '?'; //Error

        }
    }

    public boolean isNotEmpty() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            return br.readLine() != null;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public int lengthOfFile() {
        return stringLength;
    }
}

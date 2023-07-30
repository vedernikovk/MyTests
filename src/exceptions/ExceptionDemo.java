package exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionDemo {
    public static void main(String[] args) throws IOException {
        // demoSuppressedException("/non-existent-path/non-existent-file.txt");

        try {
            demoExceptionalResource();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void demoSuppressedException(String filePath) throws IOException {
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new IOException(e);
        } finally {
            fileIn.close();
        }
    }

    public static void demoExceptionalResource() throws Exception {
        try (ExceptionalResource exceptionalResource = new ExceptionalResource()) {
            exceptionalResource.processSomething();
        }
    }
}

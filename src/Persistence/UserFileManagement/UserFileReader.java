package Persistence.UserFileManagement;

import java.io.*;

public class UserFileReader {
    private static BufferedReader bufferedReader;

    private UserFileReader() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("FilePersistence/user.csv"));
    }

    public static BufferedReader getBufferedReader() throws IOException {
        new UserFileReader();
        return bufferedReader;
    }
}

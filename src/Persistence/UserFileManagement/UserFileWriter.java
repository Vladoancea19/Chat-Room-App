package Persistence.UserFileManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserFileWriter {
    private static File file;
    private static FileWriter fileWriter;

    private UserFileWriter() throws IOException {
        file = new File("FilePersistence/user.csv");
        fileWriter = new FileWriter(file, true);
    }

    public static File getFile() throws IOException {
        new UserFileWriter();
        return file;
    }

    public static FileWriter getFileWriter() {
        return fileWriter;
    }
}

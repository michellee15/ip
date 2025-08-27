package storage;

import exceptions.JerryException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public File load() throws JerryException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File directory = new File("./data");
                if (!directory.exists() && !directory.mkdirs()) {
                    throw new JerryException("Error! Failed to create data directory");
                }
                file = new File(directory, "jerry.txt");
                if (!file.exists() && !file.createNewFile()) {
                    throw new JerryException("Error! Failed to create storage file");
                }
            }
            return file;
        } catch (IOException e) {
            throw new JerryException("Error! Failed to access storage file: " + e.getMessage());
        }
    }

    public void writeToFile(String data) throws JerryException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new JerryException("Error! Failed to write to storage file: " + e.getMessage());
        }
    }
}

import exceptions.InvalidCommandException;
import exceptions.InvalidCommandFormatException;
import exceptions.JerryException;

import storage.Storage;
import tasklist.TaskList;


import java.io.File;
import java.util.Scanner;

public class Jerry {

    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private String type = "";

    public Jerry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (JerryException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (JerryException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws JerryException {
        new Jerry("data/jerry.txt").run();
    }

}
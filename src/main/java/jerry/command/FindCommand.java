package jerry.command;

import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.task.Task;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

public class FindCommand extends Command {

    String findWord;
    public FindCommand(String userInput) throws JerryException {
        String[] entries = userInput.trim().split(" ",2);
        if (entries.length < 2 || entries[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("Task number must be positive!");
        }
        this.findWord = entries[1].trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().contains(this.findWord.toLowerCase())) {
                result.append(index).append(". ").append(task).append("\n");
                index++;
            }
            if (index == 1) {
                this.response = "No matching tasks found for: " + findWord;
            } else {
                this.response = result.toString().trim();
            }
        }
        ui.displayOutput(this.response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return this.response;
    }


}

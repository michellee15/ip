package jerry.parser;

import jerry.command.ByeCommand;
import jerry.command.Command;
import jerry.command.CommandEnum;
import jerry.command.DeadlineCommand;
import jerry.command.DeleteCommand;
import jerry.command.EventCommand;
import jerry.command.FindCommand;
import jerry.command.ListCommand;
import jerry.command.MarkCommand;
import jerry.command.TodoCommand;
import jerry.command.UnmarkCommand;
import jerry.exceptions.InvalidCommandException;
import jerry.exceptions.JerryException;

/**
 * The Parser class interprets user input and translates it into the corresponding Command object.
 * It identifies the type of command and instantiates the appropriate subclass
 * of Command to represent the user's intended action.
 */
public class Parser {

    /**
     * Parses a user input string and returns the corresponding Command object.
     * The method expects user input to start with a valid command.
     * An exception is thrown if the keyword is invalid or unrecognized.
     *
     * @param input user input to be parsed
     * @return Command object representing user's action
     * @throws JerryException if the input is invalid or cannot be parsed into a known command.
     */
    public static Command parse(String input) throws JerryException {
        String[] entries = input.trim().split(" ", 2);
        assert !entries[0].isEmpty() : "Command keyword should not be empty";
        CommandEnum command;
        try {
            command = CommandEnum.valueOf(entries[0].trim().substring(0,1).toUpperCase()
            + entries[0].trim().substring(1).toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("I don't understand what you mean by: "
                    + input + "\nUse these commands at the start of your sentence instead: "
                    + "bye/list/todo/deadline/event/mark/unmark/delete");
        }

        if (command == CommandEnum.Bye) {
            return new ByeCommand();
        } else if (command == CommandEnum.List) {
            return new ListCommand();
        } else if (command == CommandEnum.Todo) {
            return new TodoCommand(input);
        } else if (command == CommandEnum.Deadline) {
            return new DeadlineCommand(input);
        } else if (command == CommandEnum.Event) {
            return new EventCommand(input);
        } else if (command == CommandEnum.Unmark) {
            return new UnmarkCommand(input);
        } else if (command == CommandEnum.Mark) {
            return new MarkCommand(input);
        } else if (command == CommandEnum.Delete) {
            return new DeleteCommand(input);
        } else if (command == CommandEnum.Find) {
            return new FindCommand(input);
        } else {
            throw new InvalidCommandException("Sorry! I don't understand what you mean by: "
                    + input + "\nUse these commands at the start of your sentence instead: "
                    + "bye/list/todo/deadline/event/mark/unmark/delete");
        }
    }
}

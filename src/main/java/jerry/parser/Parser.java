package jerry.parser;

import jerry.exceptions.InvalidCommandException;
import jerry.exceptions.JerryException;

import jerry.command.ByeCommand;
import jerry.command.Command;
import jerry.command.CommandEnum;
import jerry.command.DeadlineCommand;
import jerry.command.DeleteCommand;
import jerry.command.EventCommand;
import jerry.command.ListCommand;
import jerry.command.MarkCommand;
import jerry.command.TodoCommand;
import jerry.command.UnmarkCommand;

/**
 * The Parser class interprets user input and translates it into
 * the corresponding Command object.
 * It identifies the type of command and instantiates the appropriate subclass
 * of Command to represent the user's intended action.
 */
public class Parser {

    /**
     * Parses a user input string and returns the corresponding Command object.
     * <p>
     * The method expects user input to start with a valid command.
     * An exception is thrown if the keyword is invalid or unrecognized.
     *
     * @param input user input to be parsed
     * @return Command object representing user's action
     * @throws JerryException if the input is invalid or cannot be parsed into a known command.
     */
    public static Command parse(String input) throws JerryException {
        String[] entries = input.trim().split(" ", 2);
        CommandEnum command;
        try {
            command = CommandEnum.valueOf(entries[0].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("I don't understand what you mean by: " + input +
                    "\nUse these commands at the start of your sentence instead: " +
                    "bye/list/todo/deadline/event/mark/unmark/delete");
        }

        if (command == CommandEnum.BYE) {
            return new ByeCommand();
        } else if (command == CommandEnum.LIST) {
            return new ListCommand();
        } else if (command == CommandEnum.TODO) {
            return new TodoCommand(input);
        } else if (command == CommandEnum.DEADLINE) {
            return new DeadlineCommand(input);
        } else if (command == CommandEnum.EVENT) {
            return new EventCommand(input);
        } else if (command == CommandEnum.UNMARK) {
            return new UnmarkCommand(input);
        } else if (command == CommandEnum.MARK) {
            return new MarkCommand(input);
        } else if (command == CommandEnum.DELETE) {
            return new DeleteCommand(input);
        } else {
            throw new InvalidCommandException("Sorry! I don't understand what you mean by: " + input +
                    "\nUse these commands at the start of your sentence instead: " +
                    "bye/list/todo/deadline/event/mark/unmark/delete");
        }
    }
}

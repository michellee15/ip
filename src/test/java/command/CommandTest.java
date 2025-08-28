package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import jerry.command.DeadlineCommand;
import jerry.command.DeleteCommand;
import jerry.command.EventCommand;
import jerry.command.MarkCommand;
import jerry.command.TodoCommand;
import jerry.command.UnmarkCommand;

import jerry.exceptions.InvalidCommandFormatException;

public class CommandTest {
    @Test
    public void createTodoCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(2, new TodoCommand("todo "));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("You must specify the task in the format: <task>", error.getMessage());
        }
    }

    @Test
    public void createDeadlineCommand_wrongInput_exceptionThrown() {
        assertEquals(2, new DeadlineCommand("deadline test"));
        fail();
    }

    @Test
    public void createEventCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(2, new EventCommand("event test /from test"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Invalid format. Use: <task> /from <start> /to <end>", error.getMessage());
        }
    }

    @Test
    public void createDeleteCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(0, new DeleteCommand("delete test"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("The position must be a valid integer.", error.getMessage());
        }
    }

    @Test
    public void createMarkCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(0, new MarkCommand("mark test"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("The position must be a valid integer.", error.getMessage());
        }
    }

    @Test
    public void createMarkCommand_wrongInput2_exceptionThrown() {
        try {
            assertEquals(0, new MarkCommand("mark"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("You must specify the position of the task to mark.", error.getMessage());
        }
    }
    @Test
    public void createUnmarkCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(0, new UnmarkCommand("mark test"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("The position must be a valid integer.", error.getMessage());
        }
    }

    @Test
    public void createUnmarkCommand_wrongInput2_exceptionThrown() {
        try {
            assertEquals(0, new UnmarkCommand("mark"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("You must specify the position of the task to mark.", error.getMessage());
        }
    }
}

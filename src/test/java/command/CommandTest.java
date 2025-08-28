package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;
import org.junit.jupiter.api.Test;

import jerry.command.DeadlineCommand;
import jerry.command.DeleteCommand;
import jerry.command.EventCommand;
import jerry.command.MarkCommand;
import jerry.command.TodoCommand;
import jerry.command.UnmarkCommand;

import jerry.exceptions.InvalidCommandFormatException;

public class CommandTest {
    final private TaskList TASKLIST = new TaskList();
    final private Ui UI = new Ui();
    final private Storage STORAGE = new Storage("");

    @Test
    public void TodoCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(2, new TodoCommand("todo "));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("You forgot to describe what your todo is...", error.getMessage());
        }
    }

    @Test
    public void DeadlineCommand_wrongInput_exceptionThrown() {
        try {
            DeadlineCommand deadlineCommand = new DeadlineCommand("deadline test");
            deadlineCommand.execute(TASKLIST, UI, STORAGE);
        } catch (JerryException error) {
            assertEquals("Deadline must have '/by' keyword followed by the due date", error.getMessage());
        }
    }

    @Test
    public void EventCommand_wrongInput_exceptionThrown() {
        try {
            EventCommand eventCommand = new EventCommand("event test from test");
            eventCommand.execute(TASKLIST, UI, STORAGE);
        } catch (JerryException error) {
            assertEquals("Event must have '/from' and 'to' keywords.", error.getMessage());
        }
    }

    @Test
    public void DeleteCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(0, new DeleteCommand("delete test"));
        } catch (JerryException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }

    @Test
    public void MarkCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(0, new MarkCommand("mark test"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }

    @Test
    public void MarkCommand_wrongInput2_exceptionThrown() {
        try {
            assertEquals(0, new MarkCommand("mark"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }
    @Test
    public void UnmarkCommand_wrongInput_exceptionThrown() {
        try {
            assertEquals(0, new UnmarkCommand("mark test"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }

    @Test
    public void UnmarkCommand_wrongInput2_exceptionThrown() {
        try {
            assertEquals(0, new UnmarkCommand("mark"));
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }
}

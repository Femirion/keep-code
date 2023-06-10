package newcode.entity;

import old.mock.Command;
import old.mock.CommandType;

import java.util.Date;

public abstract class WrappedCommand {
    protected final Command command;

    public WrappedCommand(Command command) {
        this.command = command;
    }

    public boolean mustBeDeleted() {
        return !command.isAttemptsNumberExhausted();
    }

    public abstract boolean shouldBeSkipped();

    public boolean isTimeToSend() {
        return command.isTimeToSend();
    }

    public String getCommandText() {
        return command.getCommandText();
    }

    public CommandType getCommandType() {
        return command.getCommandType();
    }

    public void setSendDate(Date date) {
        command.setSendDate(date);
    }

    public void incSendCounter() {
        command.incSendCounter();
    }
}

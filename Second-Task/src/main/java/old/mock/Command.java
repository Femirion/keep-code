package old.mock;

import java.util.Date;

public class Command {
    private CommandType commandType;

    public String getCommandText() {
        return "";
    }

    public void setSendDate(Date date) {}

    public void incSendCounter() {
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public boolean isAttemptsNumberExhausted() {
        return false;
    }

    public boolean isTimeToSend() {
        return false;
    }

}

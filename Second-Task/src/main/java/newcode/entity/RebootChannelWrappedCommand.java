package newcode.entity;

import old.mock.Command;
import old.mock.CommandType;

public class RebootChannelWrappedCommand extends WrappedCommand {
    public RebootChannelWrappedCommand(Command command) {
        super(command);
    }

    @Override
    public boolean shouldBeSkipped() {
        return !isTimeToSend();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.REBOOT_CHANNEL;
    }
}

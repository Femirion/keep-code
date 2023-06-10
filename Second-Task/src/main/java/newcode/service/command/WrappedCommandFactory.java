package newcode.service.command;

import newcode.entity.DefaultWrappedCommand;
import newcode.entity.RebootChannelWrappedCommand;
import newcode.entity.WrappedCommand;
import old.mock.Command;

public class WrappedCommandFactory {

    public static WrappedCommand createWrappedCommand(Command command) {
        return switch (command.getCommandType()) {
            case REBOOT_CHANNEL -> new RebootChannelWrappedCommand(command);
            default -> new DefaultWrappedCommand(command);
        };
    }
}

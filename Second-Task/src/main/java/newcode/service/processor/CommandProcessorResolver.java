package newcode.service.processor;

import newcode.entity.WrappedCommand;
import old.mock.CommandType;

import java.util.Map;
import java.util.Optional;

public class CommandProcessorResolver {
    private static final CommandProcessor DEFAULT_COMMAND_PROCESSOR = new DefaultCommandProcessor();
    private static final Map<CommandType, CommandProcessor> processors = Map.of(CommandType.REBOOT_CHANNEL, new DefaultCommandProcessor());

    public CommandProcessor resolve(WrappedCommand command) {
        return Optional.ofNullable(processors.get(command.getCommandType()))
                .orElse(DEFAULT_COMMAND_PROCESSOR);
    }
}

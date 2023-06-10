package newcode.entity;

import old.mock.Command;

public class DefaultWrappedCommand extends WrappedCommand{
    public DefaultWrappedCommand(Command command) {
        super(command);
    }

    @Override
    public boolean shouldBeSkipped() {
        return false;
    }
}

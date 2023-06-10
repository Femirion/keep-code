package newcode.service.processor;

import newcode.entity.WrappedCommand;
import old.mock.ChannelHandlerContext;
import old.mock.InetSocketAddress;

import java.util.Date;

public class DefaultCommandProcessor extends AbstractCommandProcessor {

    @Override
    public void postProcess(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress, WrappedCommand wrappedCommand) {
        wrappedCommand.setSendDate(new Date());
        wrappedCommand.incSendCounter();
    }

    @Override
    public void deleteCommand(WrappedCommand wrappedCommand) {
        // todo delete default command
    }
}

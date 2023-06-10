package newcode.service.notification;

import newcode.entity.WrappedCommand;
import old.mock.ChannelHandlerContext;
import old.mock.InetSocketAddress;

public class ContextNotificator {

    public void sendCommandToContext(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress,
                                     WrappedCommand wrappedCommand) {
        (channelHandlerContext, inetSocketAddress, wrappedCommand.getCommandText());
    }

}

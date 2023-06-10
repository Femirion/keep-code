package newcode;

import newcode.entity.WrappedCommand;
import newcode.service.command.CommandProvider;
import newcode.service.command.WrappedCommandFactory;
import newcode.service.notification.ChannelNotificator;
import newcode.service.processor.CommandProcessor;
import newcode.service.processor.CommandProcessorResolver;
import newcode.service.util.InetSocketAddressProvider;
import old.mock.ChannelHandlerContext;
import old.mock.Command;
import old.mock.InetSocketAddress;

public class NewCode {
    private InetSocketAddressProvider inetSocketAddressProvider;
    private CommandProvider commandProvider;
    private ChannelNotificator channelNotificator;
    private CommandProcessorResolver processorResolver;

    void process(ChannelHandlerContext channelHandlerContext) {
        InetSocketAddress inetSocketAddress = inetSocketAddressProvider.getInetSocketAddress();
        for (Command command : commandProvider.getAllCommands()) {
            WrappedCommand wrappedCommand = WrappedCommandFactory.createWrappedCommand(command);
            CommandProcessor commandProcessor = processorResolver.resolve(wrappedCommand);
            commandProcessor.process(channelHandlerContext, inetSocketAddress, wrappedCommand);
        }

        channelNotificator.sendKeepAliveOkAndFlush(channelHandlerContext);
    }
}

package newcode.service.processor;

import newcode.entity.WrappedCommand;
import newcode.service.notification.AdminNotificator;
import newcode.service.notification.ContextNotificator;
import old.mock.ChannelHandlerContext;
import old.mock.InetSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCommandProcessor implements CommandProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DefaultCommandProcessor.class);
    private ContextNotificator contextNotificator;
    private AdminNotificator adminNotificator;

    @Override
    public void process(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress,
                        WrappedCommand wrappedCommand) {
        preProcess(channelHandlerContext, inetSocketAddress, wrappedCommand);

        if (wrappedCommand.mustBeDeleted()) {
            deleteCommand(wrappedCommand);
            logger.info("Command {} was deleted", wrappedCommand);
            return;
        }

        if (wrappedCommand.shouldBeSkipped()) {
            logger.debug("Command {} was skipped", wrappedCommand);
            return;
        }

        contextNotificator.sendCommandToContext(channelHandlerContext, inetSocketAddress, wrappedCommand);
        logger.info("Command {} was sent to Context", wrappedCommand);
        adminNotificator.adminNotify(inetSocketAddress, wrappedCommand.getCommandText());
        logger.info("Command {} was sent to Admin", wrappedCommand);

        postProcess(channelHandlerContext, inetSocketAddress, wrappedCommand);
    }

    public abstract void deleteCommand(WrappedCommand wrappedCommand);
}

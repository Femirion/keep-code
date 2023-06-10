package newcode.service.processor;

import newcode.entity.WrappedCommand;
import old.mock.ChannelHandlerContext;
import old.mock.InetSocketAddress;

public interface CommandProcessor {
    void process(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress, WrappedCommand wrappedCommand);
    void deleteCommand(WrappedCommand wrappedCommand);
    default void preProcess(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress, WrappedCommand wrappedCommand) {}
    default void postProcess(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress, WrappedCommand wrappedCommand) {}
    default boolean shouldBeSkipped(WrappedCommand wrappedCommand) {
        return wrappedCommand.mustBeDeleted();
    }
}

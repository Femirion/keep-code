package old;

import old.mock.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OldCode {

    private static final Logger logger = LoggerFactory.getLogger(OldCode.class);
    private Command currentCommand;

    void process(ChannelHandlerContext channelHandlerContext) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(getIpAddress(), getUdpPort());


        for (Command command : getAllCommands()) {
            if (command.getCommandType() == CommandType.REBOOT_CHANNEL) {
                if (!command.isAttemptsNumberExhausted()) {
                    if (command.isTimeToSend()) {
                        sendCommandToContext(channelHandlerContext, inetSocketAddress, command.getCommandText());


                        try {
                            AdminController.getInstance()
                                    .processUssdMessage(new DblIncomeUssdMessage(
                                            inetSocketAddress,
                                            EnumGoip.getByModel(getGoipModel()),
                                            command.getCommandText()
                                    ), false);
                        } catch (Exception ignored) {
                        }


                        command.setSendDate(new Date());


                        logger.info("send message : {}", command.getCommandText());


                        command.incSendCounter();
                    }
                } else {
                    deleteCommand(command.getCommandType());
                }
            } else {
                if (!currentCommand.isAttemptsNumberExhausted()) {
                    sendCommandToContext(channelHandlerContext, inetSocketAddress, command.getCommandText());


                    try {
                        AdminController.getInstance()
                                .processUssdMessage(new DblIncomeUssdMessage(
                                        inetSocketAddress,
                                        EnumGoip.getByModel(getGoipModel()),
                                        command.getCommandText()
                                ), false);
                    } catch (Exception ignored) {
                    }


                    logger.info("send message : {}", command.getCommandText());


                    command.setSendDate(new Date());
                    command.incSendCounter();
                } else {
                    CommandType typeToRemove = command.getCommandType();
                    deleteCommand(typeToRemove);
                }
            }
        }


        sendKeepAliveOkAndFlush(channelHandlerContext);
    }

    private void sendCommandToContext(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress, String commandText) {

    }

    private List<Command> getAllCommands() {
        return Collections.emptyList();
    }

    private String getUdpPort() {
        return "";
    }

    private String getIpAddress() {
        return "";
    }

    private String getGoipModel() {
        return "";
    }

    private void deleteCommand(CommandType typeToRemove) {
    }

    private void sendKeepAliveOkAndFlush(ChannelHandlerContext channelHandlerContext) {
    }

}

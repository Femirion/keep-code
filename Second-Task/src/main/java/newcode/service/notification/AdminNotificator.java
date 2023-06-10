package newcode.service.notification;

import newcode.service.util.GoipModelProvider;
import old.mock.AdminController;
import old.mock.DblIncomeUssdMessage;
import old.mock.EnumGoip;
import old.mock.InetSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminNotificator {
    private static final Logger logger = LoggerFactory.getLogger(AdminNotificator.class);
    private GoipModelProvider goipModelProvider;

    public void adminNotify(InetSocketAddress inetSocketAddress, String commandText) {
        try {
            AdminController.getInstance().processUssdMessage(
                    new DblIncomeUssdMessage(
                            inetSocketAddress,
                            EnumGoip.getByModel(goipModelProvider.getGoipModel()),
                            commandText
                    ),
                    false);
        } catch (Exception ex) {
            logger.trace("Exception while sending command to Admin", ex);
        }
    }
}

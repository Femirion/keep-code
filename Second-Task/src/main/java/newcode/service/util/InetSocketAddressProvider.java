package newcode.service.util;

import old.mock.InetSocketAddress;

public class InetSocketAddressProvider {
    public InetSocketAddress getInetSocketAddress() {
        return new InetSocketAddress(getIpAddress(), getUdpPort());
    }

    private String getUdpPort() {
        return "";
    }

    private String getIpAddress() {
        return "";
    }
}

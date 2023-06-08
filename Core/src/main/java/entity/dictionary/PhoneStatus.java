package entity.dictionary;

import java.util.Arrays;

public enum PhoneStatus {
    DISABLED("disabled"),
    ENABLED("enabled");

    private final String status;

    PhoneStatus(String status) {
        this.status = status;
    }

    public static PhoneStatus of(String status) {
        return Arrays.stream(PhoneStatus.values())
                .filter(s -> s.status.equals(status))
                .findAny()
                .orElse(DISABLED);
    }
}

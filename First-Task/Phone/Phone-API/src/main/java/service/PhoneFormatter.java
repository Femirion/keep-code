package service;

import dto.PhoneDto;

public class PhoneFormatter {

    public String format(PhoneDto phone) {
        return "[number=" + phone.number()
                + ", countryId=" + phone.countryId()
                + ", updatedAt=" + phone.updatedAt()
                + ", dataHumans=" + phone.dataHumans()
                + ", fullNumber=" + phone.fullNumber()
                + ", countryName=" + phone.countryName()
                + ", maxDate=" + phone.maxDate()
                + ", status=" + phone.status()
                + "]";
    }

}

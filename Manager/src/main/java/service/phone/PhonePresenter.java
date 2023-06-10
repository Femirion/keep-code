package service.phone;

import dto.CountryDto;
import dto.PhoneDto;

public interface PhonePresenter {
    void presentPhone(PhoneDto phoneDto);
    void preparePhones();
}

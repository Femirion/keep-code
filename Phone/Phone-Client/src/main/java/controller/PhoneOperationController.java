package controller;

import converter.PhoneDtoConverter;
import dto.PhoneDto;
import domain.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PhoneOperationController {
    private static final Logger log = LoggerFactory.getLogger(PhoneOperationController.class);
    private final PhoneService phoneService;
    private final PhoneDtoConverter phoneConverter;

    public PhoneOperationController(PhoneService phoneService, PhoneDtoConverter phoneDtoConverter) {
        this.phoneService = phoneService;
        this.phoneConverter = phoneDtoConverter;
    }

    public void addPhone(PhoneDto phoneDto) {
        log.debug("addPhone: {}", phoneDto);
        phoneService.addPhone(phoneConverter.toEntity(phoneDto));
    }

    public void addPhoneList(List<PhoneDto> countryDtoList) {
        log.debug("addPhoneList: {}", countryDtoList);
        phoneService.addPhoneList(phoneConverter.toEntity(countryDtoList));
    }

    public void deletePhone(String number) {
        log.debug("deletePhone: {}", number);
        phoneService.deletePhone(number);
    }

    public void deleteAllPhones() {
        log.debug("deleteAllPhones");
        phoneService.deleteAllPhones();
    }
}

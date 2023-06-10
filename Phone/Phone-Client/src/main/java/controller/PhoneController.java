package controller;

import converter.PhoneDtoConverter;
import domain.service.PhoneLoader;
import domain.service.PhoneLoaderImpl;
import domain.service.PhoneService;
import domain.service.PhoneServiceImpl;
import dto.PhoneDto;
import dto.dictionary.PhoneStatus;
import gateway.api.PhoneApiGateway;
import gateway.api.rest.PhoneRestApiConverter;
import gateway.api.rest.PhoneRestGateway;
import gateway.api.rest.parser.*;
import gateway.data.memory.PhoneInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class PhoneController {
    private static final Logger log = LoggerFactory.getLogger(PhoneController.class);
    private final PhoneService phoneService;
    private final PhoneLoader phoneLoader;
    private final PhoneDtoConverter phoneConverter;

    public PhoneController() {
        this.phoneService = new PhoneServiceImpl(new PhoneInMemoryRepository());

        PhoneParser<String> numberParser = new PhoneNumberParser();
        PhoneParser<Long> countryIdParser = new PhoneCountryIdParser();
        PhoneParser<LocalDateTime> updatedAtParser = new UpdatedAtParser();
        PhoneParser<String> dataHumansParser = new DataHumansParser();
        PhoneParser<String> fullNumberParser = new FullNumberParser();
        PhoneParser<String> countryNameParser = new CountryTextParser();
        PhoneParser<LocalDateTime> maxDateParser = new MaxDateParser();
        PhoneParser<PhoneStatus> phoneStatusParser = new StatusParser();
        PhoneApiGateway gateway = new PhoneRestGateway(
                new PhoneRestApiConverter(
                        numberParser,
                        countryIdParser,
                        updatedAtParser,
                        dataHumansParser,
                        fullNumberParser,
                        countryNameParser,
                        maxDateParser,
                        phoneStatusParser
                ));
        this.phoneLoader = new PhoneLoaderImpl(gateway);

        this.phoneConverter = new PhoneDtoConverter();
    }

    public PhoneController(PhoneService phoneService, PhoneLoader phoneLoader, PhoneDtoConverter phoneConverter) {
        this.phoneService = phoneService;
        this.phoneLoader = phoneLoader;
        this.phoneConverter = phoneConverter;
    }

    public List<PhoneDto> getPhones() {
        log.debug("get all phones");
        return phoneConverter.toDto(phoneService.getAllPhones());
    }

    public PhoneDto getPhone(String number) {
        log.debug("get phone by number: {}", number);
        return phoneService.getPhone(number)
                .map(phoneConverter::toDto)
                .orElse(null);
    }

    public List<PhoneDto> load(long countryId) {
        log.debug("load phones by countryId: {}", countryId);
        return phoneConverter.toDto(phoneLoader.loadByCountry(countryId));
    }

}

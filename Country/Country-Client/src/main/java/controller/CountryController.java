package controller;

import domain.sevice.CountryLoader;
import domain.sevice.CountryLoaderImpl;
import domain.sevice.CountryService;
import domain.sevice.CountryServiceImpl;
import dto.CountryDto;
import gateway.api.CountryApiGateway;
import gateway.api.rest.CountryRestApiConverter;
import gateway.api.rest.parser.CountryRestApiErrorHandler;
import service.RestApiErrorHandler;
import gateway.api.rest.CountryRestGateway;
import gateway.api.rest.parser.CountryIdParser;
import gateway.api.rest.parser.CountryNameParser;
import gateway.api.rest.parser.CountryParser;
import gateway.data.memory.CountryInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import converter.CountryDtoConverter;

import java.util.List;

public class CountryController {
    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;
    private final CountryLoader countryLoader;
    private final CountryDtoConverter countryConverter;

    public CountryController() {
        CountryParser<String> countryNameParser = new CountryNameParser();
        CountryParser<Long> countryIdParser = new CountryIdParser();
        RestApiErrorHandler errorHandler = new CountryRestApiErrorHandler();
        CountryApiGateway gateway = new CountryRestGateway(new CountryRestApiConverter(countryNameParser, countryIdParser, errorHandler));
        this.countryLoader = new CountryLoaderImpl(gateway);
        this.countryService = new CountryServiceImpl(new CountryInMemoryRepository());
        this.countryConverter = new CountryDtoConverter();
    }

    public CountryController(CountryService countryService, CountryLoader countryLoader, CountryDtoConverter countryConverter) {
        this.countryService = countryService;
        this.countryLoader = countryLoader;
        this.countryConverter = countryConverter;
    }

    public List<CountryDto> getCountries() {
        log.debug("getCountries");
        return countryConverter.toDto(countryService.getAllCountries());
    }

    public CountryDto getCountry(long countryId) {
        log.debug("getCountry: {}", countryId);
        return countryService.getCountry(countryId)
                .map(countryConverter::toDto)
                .orElse(null);
    }

    public List<CountryDto> load() {
        log.debug("load");
        return countryConverter.toDto(countryLoader.load());
    }
}

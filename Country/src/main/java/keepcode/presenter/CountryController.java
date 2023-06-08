package keepcode.presenter;

import entity.CountryDto;
import keepcode.domain.sevice.CountryLoader;
import keepcode.domain.sevice.CountryLoaderImpl;
import keepcode.domain.sevice.CountryService;
import keepcode.domain.sevice.CountryServiceImpl;
import keepcode.gateway.api.CountryApiGateway;
import keepcode.gateway.api.rest.CountryRestApiConverter;
import keepcode.gateway.api.rest.CountryRestGateway;
import keepcode.gateway.api.rest.parser.CountryIdParser;
import keepcode.gateway.api.rest.parser.CountryNameParser;
import keepcode.gateway.api.rest.parser.CountryParser;
import keepcode.gateway.data.memory.CountryInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CountryController {
    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;
    private final CountryLoader countryLoader;
    private final CountryDtoConverter countryConverter;

    public CountryController() {
        CountryParser<String> countryNameParser = new CountryNameParser();
        CountryParser<Long> countryIdParser = new CountryIdParser();
        CountryApiGateway gateway = new CountryRestGateway(new CountryRestApiConverter(countryNameParser, countryIdParser));
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

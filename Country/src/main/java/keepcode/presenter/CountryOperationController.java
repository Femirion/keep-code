package keepcode.presenter;

import entity.CountryDto;
import keepcode.domain.sevice.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CountryOperationController {
    private static final Logger log = LoggerFactory.getLogger(CountryOperationController.class);

    private final CountryService countryService;
    private final CountryDtoConverter countryConverter;

    public CountryOperationController(CountryService countryService, CountryDtoConverter countryConverter) {
        this.countryService = countryService;
        this.countryConverter = countryConverter;
    }

    public void addCounty(CountryDto countryDto) {
        log.debug("addCounty: {}", countryDto);
        countryService.addCountry(countryConverter.toEntity(countryDto));
    }

    public void addCountryList(List<CountryDto> countryDtoList) {
        log.debug("addCountryList: {}", countryDtoList);
        countryService.addCountryList(countryConverter.toEntity(countryDtoList));
    }

    public void deleteCountry(long countryId) {
        log.debug("deleteCountry: {}", countryId);
        countryService.deleteCountry(countryId);
    }

    public void deleteAllCountries() {
        log.debug("deleteAllCountries");
        countryService.deleteAllCountries();
    }
}

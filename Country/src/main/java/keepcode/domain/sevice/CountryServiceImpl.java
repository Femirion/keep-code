package keepcode.domain.sevice;

import keepcode.domain.entity.Country;
import keepcode.gateway.data.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {
    private static final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void addCountry(Country country) {
        countryRepository.addCountry(country);
        log.info("Country added: {}", country);
    }

    @Override
    public void deleteCountry(long countryId) {
        countryRepository.deleteCountry(countryId);
        log.info("Country deleted: {}", countryId);
    }

    @Override
    public Optional<Country> getCountry(long countryId) {
        return countryRepository.getCountry(countryId);
    }

    @Override
    public void deleteAllCountries() {
        countryRepository.deleteAllCountries();
        log.info("All countries deleted");
    }

    @Override
    public void addCountryList(List<Country> countries) {
        countryRepository.addCountryList(countries);
        log.info("Countries added: {}", countries);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.getAllCountries();
    }
}

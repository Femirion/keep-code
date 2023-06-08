package keepcode.domain.sevice;

import keepcode.domain.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> getCountry(long countryId);
    List<Country> getAllCountries();
    void addCountry(Country country);
    void addCountryList(List<Country> countries);
    void deleteCountry(long countryId);
    void deleteAllCountries();
}

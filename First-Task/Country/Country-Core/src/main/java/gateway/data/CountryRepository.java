package gateway.data;

import domain.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    Optional<Country> getCountry(long id);
    List<Country> getAllCountries();
    void addCountry(Country country);
    void addCountryList(List<Country> countries);
    void deleteCountry(long id);
    void deleteAllCountries();
}

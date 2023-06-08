package keepcode.gateway.data.memory;

import keepcode.domain.entity.Country;
import keepcode.gateway.data.CountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CountryInMemoryRepository implements CountryRepository {
    private final Map<Long, Country> countries = new ConcurrentHashMap<>();

    @Override
    public void addCountry(Country country) {
        countries.put(country.id(), country);
    }

    @Override
    public void addCountryList(List<Country> countries) {
        countries.forEach(this::addCountry);
    }

    @Override
    public void deleteCountry(long id) {
        countries.remove(id);
    }

    @Override
    public Optional<Country> getCountry(long id) {
        return Optional.ofNullable(countries.get(id));
    }

    @Override
    public List<Country> getAllCountries() {
        return new ArrayList<>(countries.values());
    }

    @Override
    public void deleteAllCountries() {
        countries.clear();
    }
}

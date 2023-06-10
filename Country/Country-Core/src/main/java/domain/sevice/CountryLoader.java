package domain.sevice;

import domain.entity.Country;

import java.util.List;

public interface CountryLoader {
    List<Country> load();
}

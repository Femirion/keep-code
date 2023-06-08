package keepcode.domain.sevice;

import keepcode.domain.entity.Country;

import java.util.List;

public interface CountryLoader {
    List<Country> load();
}

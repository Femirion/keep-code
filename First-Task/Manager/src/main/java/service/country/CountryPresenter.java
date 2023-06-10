package service.country;

import dto.CountryDto;

public interface CountryPresenter {
    void presentCountry(CountryDto countryDto);
    void prepareCountries();
}

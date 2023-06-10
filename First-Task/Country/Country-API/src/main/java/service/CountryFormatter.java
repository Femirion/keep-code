package service;

import dto.CountryDto;

public class CountryFormatter {

    public String format(CountryDto country) {
        return "[countryId=" + country.id()
                + ", countryName=" + country.name()
                + "]";
    }

}

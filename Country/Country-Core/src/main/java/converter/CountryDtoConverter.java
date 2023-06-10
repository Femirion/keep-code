package converter;

import dto.CountryDto;
import domain.entity.Country;

import java.util.List;

public class CountryDtoConverter {

    public CountryDto toDto(Country country) {
        return new CountryDto(country.name(), country.id());
    }

    public List<CountryDto> toDto(List<Country> countryList) {
        return countryList.stream()
                .map(this::toDto)
                .toList();
    }

    public Country toEntity(CountryDto countryDto) {
        return new Country(countryDto.name(), countryDto.id());
    }

    public List<Country> toEntity(List<CountryDto> countryDtoList) {
        return countryDtoList.stream()
                .map(this::toEntity)
                .toList();
    }
}

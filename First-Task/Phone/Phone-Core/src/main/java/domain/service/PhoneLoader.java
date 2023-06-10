package domain.service;

import domain.entity.Phone;

import java.util.List;

public interface PhoneLoader {
    List<Phone> loadByCountry(long countryId);
}

import dto.CountryDto;
import dto.PhoneDto;
import service.ManagerService;
import service.country.CountryFilePresenter;
import service.country.CountryPresenter;
import service.phone.PhoneFilePresenter;
import service.phone.PhonePresenter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ManagerService managerService = new ManagerService();
        CountryPresenter countryPresenter = new CountryFilePresenter();
        PhonePresenter phonePresenter = new PhoneFilePresenter();

        List<CountryDto> countries = managerService.getAllCountries();
        countryPresenter.prepareCountries();
        countries.forEach(countryPresenter::presentCountry);

        List<PhoneDto> phones = managerService.getAllPhones(countries);
        phonePresenter.preparePhones();
        phones.forEach(phonePresenter::presentPhone);
    }
}

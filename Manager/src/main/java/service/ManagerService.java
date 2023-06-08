package service;

import entity.CountryDto;
import entity.PhoneDto;
import keepcode.presenter.CountryController;
import keepcode.presenter.PhoneController;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    private final CountryController countryController;
    private final PhoneController phoneController;

    public ManagerService() {
        this.countryController = new CountryController();
        this.phoneController = new PhoneController();
    }

    public ManagerService(CountryController countryController, PhoneController phoneController) {
        this.countryController = countryController;
        this.phoneController = phoneController;
    }

    public List<CountryDto> getAllCountries() {
        return countryController.load();
    }

    public List<PhoneDto> getAllPhonesByCountry(long countryId) {
        return phoneController.load(countryId);
    }

    public List<PhoneDto> getAllPhones(List<CountryDto> countries) {
        List<PhoneDto> result = new ArrayList<>();
        for(CountryDto country : countries) {
            result.addAll(phoneController.load(country.id()));
            System.out.println("Loaded phones for country " + country.name() + ", total phones: " + result.size());
        }
        return result;
        // todo can be improved with parallelStream
//        return countries.parallelStream()
//                .map(country -> phoneController.load(country.id()))
//                .reduce((r1, r2) -> {
//                                r1.addAll(r2);
//                                return r1;
//                        })
//                .orElse(Collections.emptyList());
    }
}

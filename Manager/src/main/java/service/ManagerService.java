package service;

import controller.CountryController;
import controller.PhoneController;
import dto.CountryDto;
import dto.PhoneDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    private static final Logger log = LoggerFactory.getLogger(ManagerService.class);
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
        log.debug("Loading countries");
        List<CountryDto> result = countryController.load();
        log.debug("Loaded countries, total countries: {}", result.size());
        return result;
    }

    public List<PhoneDto> getAllPhonesByCountry(long countryId) {
        log.debug("Loading phones for country {}", countryId);
        List<PhoneDto> result = phoneController.load(countryId);
        log.debug("Loaded phones for country {}, total phones: {}", countryId, result.size());
        return result;
    }

    public List<PhoneDto> getAllPhones(List<CountryDto> countries) {
        List<PhoneDto> result = new ArrayList<>();
        for(CountryDto country : countries) {
            result.addAll(phoneController.load(country.id()));
            log.info("Loaded phones for country {}, total count of phones: {}", country.name(), result.size());
        }
        return result;
        // todo can be improved with parallelStream or CompletableFuture
//        return countries.parallelStream()
//                .map(country -> phoneController.load(country.id()))
//                .reduce((r1, r2) -> {
//                                r1.addAll(r2);
//                                return r1;
//                        })
//                .orElse(Collections.emptyList());
    }
}

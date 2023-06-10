import dto.CountryDto;
import dto.PhoneDto;
import exception.CommonIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CountryFormatter;
import service.ManagerService;
import service.PhoneFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final Path COUNTRIES_FILE_PATH = Path.of("counties.txt");
    private static final Path PHONES_FILE_PATH = Path.of("phones.txt");

    public static void main(String[] args) {
        ManagerService managerService = new ManagerService();

        List<CountryDto> countries = managerService.getAllCountries();
        prepareCountry();
        CountryFormatter countryFormatter = new CountryFormatter();
        countries.forEach(c -> writeCountryToFile(c, countryFormatter));

        List<PhoneDto> phones = managerService.getAllPhones(countries);
        preparePhones();
        PhoneFormatter phoneFormatter = new PhoneFormatter();
        phones.forEach(p -> writePhoneToFile(p, phoneFormatter));
    }

    private static void writePhoneToFile(PhoneDto phoneDto, PhoneFormatter formatter) {
        try {
            Files.writeString(COUNTRIES_FILE_PATH, formatter.format(phoneDto) + System.lineSeparator(), StandardOpenOption.APPEND);
            log.debug("Phone {} was written to file", phoneDto);
        } catch (IOException e) {
            throw new CommonIOException(e.getMessage(), e);
        }
    }

    private static void writeCountryToFile(CountryDto countryDto, CountryFormatter formatter) {
        try {
            Files.writeString(COUNTRIES_FILE_PATH, formatter.format(countryDto) + System.lineSeparator(), StandardOpenOption.APPEND);
            log.debug("Country {} was written to file", countryDto);
        } catch (IOException e) {
            throw new CommonIOException(e.getMessage(), e);
        }
    }

    public static void prepareCountry() {
        try {
            Files.deleteIfExists(COUNTRIES_FILE_PATH);
            log.debug("Countries file was deleted");
        } catch (IOException e) {
            throw new CommonIOException("Can't delete countries file", e);
        }
        try {
            Files.createFile(COUNTRIES_FILE_PATH);
            log.debug("Countries file was created");
        } catch (IOException e) {
            throw new CommonIOException("Can't create countries file", e);
        }
    }

    public static void preparePhones() {
        try {
            Files.deleteIfExists(PHONES_FILE_PATH);
            log.debug("Phones file was deleted");
        } catch (IOException e) {
            throw new CommonIOException("Can't delete phones file", e);
        }
        try {
            Files.createFile(PHONES_FILE_PATH);
            log.debug("Phones file was created");
        } catch (IOException e) {
            throw new CommonIOException("Can't create phones file", e);
        }
    }
}

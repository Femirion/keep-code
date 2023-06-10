package service.country;

import dto.CountryDto;
import exception.CommonIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CountryFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CountryFilePresenter implements CountryPresenter {
    private static final Logger log = LoggerFactory.getLogger(CountryFilePresenter.class);
    private static final Path COUNTRIES_FILE_PATH = Path.of("countries.txt");
    private static final CountryFormatter FORMATTER = new CountryFormatter();
    @Override
    public void presentCountry(CountryDto countryDto) {
        writeCountryToFile(countryDto);
    }

    private void writeCountryToFile(CountryDto countryDto) {
        try {
            Files.writeString(COUNTRIES_FILE_PATH, FORMATTER.format(countryDto) + System.lineSeparator(), StandardOpenOption.APPEND);
            log.debug("Country {} was written to file", countryDto);
        } catch (IOException e) {
            throw new CommonIOException(e.getMessage(), e);
        }
    }

    public void prepareCountries() {
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
}

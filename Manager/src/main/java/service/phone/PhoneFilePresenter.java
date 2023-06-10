package service.phone;

import dto.CountryDto;
import dto.PhoneDto;
import exception.CommonIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CountryFormatter;
import service.PhoneFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PhoneFilePresenter implements PhonePresenter {
    private static final Logger log = LoggerFactory.getLogger(PhoneFilePresenter.class);
    private static final Path PHONES_FILE_PATH = Path.of("phones.txt");
    private static final PhoneFormatter FORMATTER = new PhoneFormatter();

    @Override
    public void presentPhone(PhoneDto phoneDto) {
        writePhoneToFile(phoneDto);
    }

    private void writePhoneToFile(PhoneDto phoneDto) {
        try {
            Files.writeString(PHONES_FILE_PATH, FORMATTER.format(phoneDto) + System.lineSeparator(), StandardOpenOption.APPEND);
            log.debug("Phone {} was written to file", phoneDto);
        } catch (IOException e) {
            throw new CommonIOException(e.getMessage(), e);
        }
    }

    public void preparePhones() {
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

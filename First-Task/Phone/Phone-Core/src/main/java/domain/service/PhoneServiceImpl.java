package domain.service;

import domain.entity.Phone;
import gateway.data.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class PhoneServiceImpl implements PhoneService {
    private static final Logger log = LoggerFactory.getLogger(PhoneServiceImpl.class);
    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Optional<Phone> getPhone(String number) {
        return phoneRepository.getPhone(number);
    }

    @Override
    public List<Phone> getAllPhones() {
        return phoneRepository.getAllPhones();
    }

    @Override
    public void addPhone(Phone phone) {
        phoneRepository.addPhone(phone);
        log.info("Phone {} added", phone);
    }

    @Override
    public void addPhoneList(List<Phone> phones) {
        phoneRepository.addPhoneList(phones);
        log.info("Phones {} added", phones);
    }

    @Override
    public void deletePhone(String number) {
        phoneRepository.deletePhone(number);
        log.info("Phone {} deleted", number);
    }

    @Override
    public void deleteAllPhones() {
        phoneRepository.deleteAllPhones();
        log.info("All phones deleted");
    }
}

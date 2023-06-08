package keepcode.gateway.data.memory;

import keepcode.domain.entity.Phone;
import keepcode.gateway.data.PhoneRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PhoneInMemoryRepository implements PhoneRepository {
    private final Map<String, Phone> phones = new ConcurrentHashMap<>();

    @Override
    public Optional<Phone> getPhone(String number) {
        return Optional.ofNullable(phones.get(number));
    }

    @Override
    public List<Phone> getAllPhones() {
        return new ArrayList<>(phones.values());
    }

    @Override
    public void addPhone(Phone phone) {
        phones.put(phone.number(), phone);
    }

    @Override
    public void addPhoneList(List<Phone> phones) {
        phones.forEach(this::addPhone);
    }

    @Override
    public void deletePhone(String number) {
        phones.remove(number);
    }

    @Override
    public void deleteAllPhones() {
        phones.clear();
    }
}

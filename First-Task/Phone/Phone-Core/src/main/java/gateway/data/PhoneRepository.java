package gateway.data;

import domain.entity.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository {
    Optional<Phone> getPhone(String number);
    List<Phone> getAllPhones();
    void addPhone(Phone phone);
    void addPhoneList(List<Phone> phones);
    void deletePhone(String number);
    void deleteAllPhones();
}

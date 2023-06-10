package converter;

import dto.PhoneDto;
import domain.entity.Phone;

import java.util.List;

public class PhoneDtoConverter {

    public PhoneDto toDto(Phone phone) {
        return new PhoneDto(
                phone.number(),
                phone.countryId(),
                phone.updatedAt(),
                phone.dataHumans(),
                phone.fullNumber(),
                phone.countryName(),
                phone.maxDate(),
                phone.status()
        );
    }

    public List<PhoneDto> toDto(List<Phone> phoneList) {
        return phoneList.stream()
                .map(this::toDto)
                .toList();
    }

    public Phone toEntity(PhoneDto phoneDto) {
        return new Phone(
                phoneDto.number(),
                phoneDto.countryId(),
                phoneDto.updatedAt(),
                phoneDto.dataHumans(),
                phoneDto.fullNumber(),
                phoneDto.countryName(),
                phoneDto.maxDate(),
                phoneDto.status()
        );
    }

    public List<Phone> toEntity(List<PhoneDto> phoneDtoList) {
        return phoneDtoList.stream()
                .map(this::toEntity)
                .toList();
    }
}

package entity;

import entity.dictionary.PhoneStatus;

import java.time.LocalDateTime;

public record PhoneDto (
        String number,
        long countryId,
        LocalDateTime updatedAt,
        String dataHumans,
        String fullNumber,
        String countryName,
        LocalDateTime maxDate,
        PhoneStatus status
) {
}

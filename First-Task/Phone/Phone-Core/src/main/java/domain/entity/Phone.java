package domain.entity;

import dto.dictionary.PhoneStatus;

import java.time.LocalDateTime;

/**
 *
 *  data example:
 *      {
 *          "number":"9295417243",
 *          "country":7,
 *          "updated_at":"2023-06-04 10:01:40",
 *          "data_humans":"1 day ago",
 *          "full_number":"+79295417243",
 *          "country_text":"Russia",
 *          "maxdate":"2023-06-02 12:15:43",
 *          "status":"disabled"
 *      }
 *
 */
public record Phone (
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

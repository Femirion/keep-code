package domain.entity;

/**
 * data example:
 *      {
 *          "country":7,
 *          "country_text":"Russia"
 *      }
 */
public record Country (String name, long id) {
}

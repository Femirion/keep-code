package keepcode.gateway.api.rest.parser;

import gateway.parser.LocalDateTimeParser;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class MaxDateParser extends LocalDateTimeParser implements PhoneParser<LocalDateTime> {
    private static final Pattern UPDATED_AT_PATTERN = Pattern.compile("\"maxdate\":\"(.+?)\"");

    public MaxDateParser() {
        super(UPDATED_AT_PATTERN, FORMATTER);
    }
}

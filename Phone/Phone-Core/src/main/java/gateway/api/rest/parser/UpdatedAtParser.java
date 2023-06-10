package gateway.api.rest.parser;

import gateway.parser.LocalDateTimeParser;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class UpdatedAtParser extends LocalDateTimeParser implements PhoneParser<LocalDateTime> {
    private static final Pattern UPDATED_AT_PATTERN = Pattern.compile("\"updated_at\":\"(.+?)\"");
    public UpdatedAtParser() {
        super(UPDATED_AT_PATTERN, FORMATTER);
    }
}

package gateway.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

public class LocalDateTimeParser  implements Parser<LocalDateTime> {
    private static final Logger log = LoggerFactory.getLogger(LocalDateTimeParser.class);
    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(ISO_LOCAL_DATE)
            .appendLiteral(' ')
            .append(ISO_LOCAL_TIME)
            .toFormatter();
    private final Pattern pattern;
    private final DateTimeFormatter formatter;

    public LocalDateTimeParser(Pattern pattern, DateTimeFormatter formatter) {
        this.pattern = pattern;
        this.formatter = formatter;
    }

    @Override
    public List<LocalDateTime> parse(String input) {
        List<LocalDateTime> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            try {
                result.add(LocalDateTime.parse(matcher.group(1), formatter));
            } catch (NumberFormatException ex) {
                handleError(input, ex);
            }
        }
        return result;
    }

    @Override
    public void handleError(String input, Exception ex) {
        log.error("can not parse LocalDateTime from input={}", input, ex);
    }
}

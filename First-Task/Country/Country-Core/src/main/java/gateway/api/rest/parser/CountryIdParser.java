package gateway.api.rest.parser;

import gateway.parser.LongParser;

import java.util.regex.Pattern;

public class CountryIdParser extends LongParser implements CountryParser<Long> {
    private static final Pattern COUNTRY_ID_PATTERN = Pattern.compile("\"country\":(\\d+)");
    public CountryIdParser() {
        super(COUNTRY_ID_PATTERN);
    }
}

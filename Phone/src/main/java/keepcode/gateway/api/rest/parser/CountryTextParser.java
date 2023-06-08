package keepcode.gateway.api.rest.parser;

import gateway.parser.StringParser;

import java.util.regex.Pattern;

public class CountryTextParser extends StringParser implements PhoneParser<String> {
    private static final Pattern DATA_HUMANS_PATTERN = Pattern.compile("\"country_text\":\"(.+?)\"");

    public CountryTextParser() {
        super(DATA_HUMANS_PATTERN);
    }
}

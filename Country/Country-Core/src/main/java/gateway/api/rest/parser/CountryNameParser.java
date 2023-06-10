package gateway.api.rest.parser;

import gateway.parser.StringParser;

import java.util.regex.Pattern;

public class CountryNameParser extends StringParser implements CountryParser<String> {
    private static final Pattern COUNTRY_NAME_PATTERN = Pattern.compile("\"country_text\":\"(.+?)\"");
    public CountryNameParser() {
        super(COUNTRY_NAME_PATTERN);
    }
}

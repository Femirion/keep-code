package gateway.api.rest.parser;

import gateway.parser.LongParser;

import java.util.regex.Pattern;

public class PhoneCountryIdParser extends LongParser implements PhoneParser<Long> {
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("\"country\":(\\d+?)");
    public PhoneCountryIdParser() {
        super(PHONE_NUMBER_PATTERN);
    }
}

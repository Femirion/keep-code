package gateway.api.rest.parser;

import gateway.parser.StringParser;

import java.util.regex.Pattern;

public class PhoneNumberParser extends StringParser implements PhoneParser<String> {
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("\"number\":\"(.+?)\"");

    public PhoneNumberParser() {
        super(PHONE_NUMBER_PATTERN);
    }
}

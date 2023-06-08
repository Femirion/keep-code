package keepcode.gateway.api.rest.parser;

import gateway.parser.StringParser;

import java.util.regex.Pattern;

public class FullNumberParser extends StringParser implements PhoneParser<String> {
    private static final Pattern FULL_PHONE_NUMBER_PATTERN = Pattern.compile("\"full_number\":\"(.+?)\"");

    public FullNumberParser() {
        super(FULL_PHONE_NUMBER_PATTERN);
    }
}

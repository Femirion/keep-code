package keepcode.gateway.api.rest.parser;

import gateway.parser.StringParser;

import java.util.regex.Pattern;

public class DataHumansParser extends StringParser implements PhoneParser<String> {
    private static final Pattern DATA_HUMANS_PATTERN = Pattern.compile("\"data_humans\":\"(.+?)\"");

    public DataHumansParser() {
        super(DATA_HUMANS_PATTERN);
    }
}

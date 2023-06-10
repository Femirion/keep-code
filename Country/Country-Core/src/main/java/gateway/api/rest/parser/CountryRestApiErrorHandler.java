package gateway.api.rest.parser;

import exception.InteractionException;
import exception.ParseException;
import service.RestApiErrorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryRestApiErrorHandler implements RestApiErrorHandler {
    private static final Pattern RESPONSE_PATTERN = Pattern.compile("\"response\":(\\d+)");
    private static final long SUCCESS_RESPONSE = 1L;


    public void handle(String input) {
        Matcher matcher = RESPONSE_PATTERN.matcher(input);
        if (matcher.find()) {
            try {
                long resultCode = Long.parseLong(matcher.group(1));
                if (SUCCESS_RESPONSE != (resultCode)) {
                    throw new InteractionException("Country API error. Error msg=" + resultCode);
                }
            } catch (NumberFormatException ex) {
                throw new ParseException("Country API error. Can't parse response=" + input, ex);
            }
        } else {
            throw new ParseException("Country API error. Can't parse response=" + input);
        }
    }
}

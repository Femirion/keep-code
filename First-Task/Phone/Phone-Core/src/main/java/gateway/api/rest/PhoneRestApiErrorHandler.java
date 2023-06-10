package gateway.api.rest;

import exception.InteractionException;
import exception.ParseException;
import service.RestApiErrorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneRestApiErrorHandler implements RestApiErrorHandler {
    private static final Pattern RESPONSE_PATTERN = Pattern.compile("\"response\":\"(.+?)\"");
    private static final String SUCCESS_RESPONSE = "1";


    public void handle(String input) {
        Matcher matcher = RESPONSE_PATTERN.matcher(input);
        if (matcher.find()) {
            String resultCode = matcher.group(1);
            if (!SUCCESS_RESPONSE.equals(resultCode)) {
                throw new InteractionException("Phone API error. Error msg=" + resultCode);
            }
        } else {
            throw new ParseException("Phone API error. Can't parse response=" + input);
        }
    }
}

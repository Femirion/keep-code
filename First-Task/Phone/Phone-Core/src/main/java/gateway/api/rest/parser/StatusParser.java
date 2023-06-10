package gateway.api.rest.parser;

import dto.dictionary.PhoneStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatusParser implements PhoneParser<PhoneStatus> {
    private static final Pattern STATUS_PATTERN = Pattern.compile("\"status\":\"(.+?)\"");

    @Override
    public List<PhoneStatus> parse(String input) {
        List<PhoneStatus> result = new ArrayList<>();
        Matcher matcher = STATUS_PATTERN.matcher(input);
        while (matcher.find()) {
            result.add(PhoneStatus.of(matcher.group(1)));
        }
        return result;
    }
}

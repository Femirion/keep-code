package gateway.parser;

import exception.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LongParser implements Parser<Long> {
    private final Pattern pattern;

    public LongParser(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public List<Long> parse(String input) {
        List<Long> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            try {
                result.add(Long.valueOf(matcher.group(1)));
            } catch (Exception ex) {
                handleError(input, ex);
            }
        }
        return result;
    }

    @Override
    public void handleError(String input, Exception ex) {
        throw new ParseException("can not parse Long from input=" + input, ex);
    }
}

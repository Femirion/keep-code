package gateway.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringParser implements Parser<String> {
    private final Pattern pattern;

    public StringParser(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public List<String> parse(String input) {
        List<String> result = new ArrayList<>();
        Matcher countryNamematcher = pattern.matcher(input);
        while (countryNamematcher.find()) {
            result.add(countryNamematcher.group(1));
        }
        return result;
    }
}

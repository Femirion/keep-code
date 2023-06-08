package keepcode.gateway.api.rest;

import entity.dictionary.PhoneStatus;
import exception.InteractionException;
import keepcode.domain.entity.Phone;
import keepcode.gateway.api.rest.parser.PhoneParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PhoneRestApiConverter {
    private static final Logger log = LoggerFactory.getLogger(PhoneRestApiConverter.class);
    private final PhoneParser<String> numberParser;
    private final PhoneParser<Long> countryIdParser;
    private final PhoneParser<LocalDateTime> updatedAtParser;
    private final PhoneParser<String> dataHumansParser;
    private final PhoneParser<String> fullNumberParser;
    private final PhoneParser<String> countryTextParser;
    private final PhoneParser<LocalDateTime> maxDateParser;
    private final PhoneParser<PhoneStatus> phoneStatusParser;

    public PhoneRestApiConverter(PhoneParser<String> numberParser, PhoneParser<Long> countryIdParser,
                                 PhoneParser<LocalDateTime> updatedAtParser, PhoneParser<String> dataHumansParser,
                                 PhoneParser<String> fullNumberParser, PhoneParser<String> countryTextParser,
                                 PhoneParser<LocalDateTime> maxDateParser, PhoneParser<PhoneStatus> phoneStatusParser) {
        this.numberParser = numberParser;
        this.countryIdParser = countryIdParser;
        this.updatedAtParser = updatedAtParser;
        this.dataHumansParser = dataHumansParser;
        this.fullNumberParser = fullNumberParser;
        this.countryTextParser = countryTextParser;
        this.maxDateParser = maxDateParser;
        this.phoneStatusParser = phoneStatusParser;
    }

    public CompletableFuture<List<Phone>> convert(CompletableFuture<HttpResponse<String>> response) {
        try {
            return response.thenApply(r -> convert(r.body())).get();
        } catch (Exception ex) {
            throw new InteractionException(ex.getMessage(), ex);
        }
    }

    private CompletableFuture<List<Phone>> convert(String input) {
        CompletableFuture<List<String>> numbers = CompletableFuture.supplyAsync(() -> numberParser.parse(input));
        CompletableFuture<List<Long>> countryIds = CompletableFuture.supplyAsync(() -> countryIdParser.parse(input));
        CompletableFuture<List<LocalDateTime>> updatedAts = CompletableFuture.supplyAsync(() -> updatedAtParser.parse(input));
        CompletableFuture<List<String>> dataHumans = CompletableFuture.supplyAsync(() -> dataHumansParser.parse(input));
        CompletableFuture<List<String>> fullNumbers = CompletableFuture.supplyAsync(() -> fullNumberParser.parse(input));
        CompletableFuture<List<String>> countryTexts = CompletableFuture.supplyAsync(() -> countryTextParser.parse(input));
        CompletableFuture<List<LocalDateTime>> maxDates = CompletableFuture.supplyAsync(() -> maxDateParser.parse(input));
        CompletableFuture<List<PhoneStatus>> phoneStatuses = CompletableFuture.supplyAsync(() -> phoneStatusParser.parse(input));

        return CompletableFuture.allOf(
                        numbers, countryIds, updatedAts, dataHumans,
                        fullNumbers, countryTexts, maxDates, phoneStatuses)
                .thenApply(ignoredVoid -> buildPhoneList(
                        numbers.join(), countryIds.join(),
                        updatedAts.join(), dataHumans.join(), fullNumbers.join(),
                        countryTexts.join(), maxDates.join(), phoneStatuses.join()));
    }

    private List<Phone> buildPhoneList(List<String> numbers, List<Long> countryIds, List<LocalDateTime> updatedAts,
                                       List<String> dataHumans, List<String> fullNumbers, List<String> countryTexts,
                                       List<LocalDateTime> maxDates, List<PhoneStatus> phoneStatuses) {
        if (argumentsHaveDifferentSize(numbers, countryIds, updatedAts, dataHumans, fullNumbers,
                countryTexts, maxDates, phoneStatuses)) {
            log.warn("PhoneRestApiConverter.buildPhoneList: arguments have different size");
            return Collections.emptyList();
        }
        List<Phone> result = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            result.add(new Phone(
                    numbers.get(i),
                    countryIds.get(i),
                    updatedAts.get(i),
                    dataHumans.get(i),
                    fullNumbers.get(i),
                    countryTexts.get(i),
                    maxDates.get(i),
                    phoneStatuses.get(i)
            ));
        }
        return result;
    }

    private boolean argumentsHaveDifferentSize(List<String> numbers, List<Long> countryIds, List<LocalDateTime> updatedAts,
                                       List<String> dataHumans, List<String> fullNumbers, List<String> countryTexts,
                                       List<LocalDateTime> maxDates, List<PhoneStatus> phoneStatuses) {
        return numbers.size() != countryIds.size()
                || numbers.size() != updatedAts.size()
                || numbers.size() != dataHumans.size()
                || numbers.size() != fullNumbers.size()
                || numbers.size() != countryTexts.size()
                || numbers.size() != maxDates.size()
                || numbers.size() != phoneStatuses.size();
    }
}

package gateway.api.rest;

import exception.InteractionException;
import domain.entity.Country;
import gateway.api.rest.parser.CountryParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.RestApiErrorHandler;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CountryRestApiConverter {
    private static final Logger log = LoggerFactory.getLogger(CountryRestApiConverter.class);
    private final CountryParser<String> countryNameParser;
    private final CountryParser<Long> countryIdParser;
    private final RestApiErrorHandler errorHandler;

    public CountryRestApiConverter(CountryParser<String> countryNameParser, CountryParser<Long> countryIdParser,
                                   RestApiErrorHandler errorHandler) {
        this.countryNameParser = countryNameParser;
        this.countryIdParser = countryIdParser;
        this.errorHandler = errorHandler;
    }

    public CompletableFuture<List<Country>> convert(CompletableFuture<HttpResponse<String>> response) {
        try {
            return response.thenApply(r -> convert(r.body())).get();
        } catch (Exception ex) {
            throw new InteractionException(ex.getMessage(), ex);
        }
    }

    private CompletableFuture<List<Country>> convert(String input) {
        errorHandler.handle(input);
        CompletableFuture<List<String>> countryNames = CompletableFuture.supplyAsync(() -> countryNameParser.parse(input));
        CompletableFuture<List<Long>> countryIds = CompletableFuture.supplyAsync(() -> countryIdParser.parse(input));
        return countryNames.thenCombineAsync(countryIds, this::buildCountryList);
    }

    private List<Country> buildCountryList(List<String> names, List<Long> ids) {
        if (ids.size() != names.size()) {
            log.warn("Country names and ids size is not equal");
            return Collections.emptyList();
        }
        List<Country> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(new Country(names.get(i), ids.get(i)));
        }
        return result;
    }
}

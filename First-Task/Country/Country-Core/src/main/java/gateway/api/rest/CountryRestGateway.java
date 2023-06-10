package gateway.api.rest;

import domain.entity.Country;
import gateway.api.CountryApiGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CountryRestGateway implements CountryApiGateway {
    private static final Logger log = LoggerFactory.getLogger(CountryRestGateway.class);
    private static final String COUNTRIES_URL = "https://onlinesim.ru/api/getFreeCountryList";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String CONTENT_TYPE = "text/plain;charset=UTF-8";
    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private final CountryRestApiConverter countryConverter;

    public CountryRestGateway(CountryRestApiConverter countryRestApiConverter) {
        this.countryConverter = countryRestApiConverter;
    }

    @Override
    public CompletableFuture<List<Country>> getCountries() {
        log.debug("prepare for loading countries from external system");
        HttpRequest request = buildRequest();
        CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder()
                    .build()
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return countryConverter.convert(response);
    }

    private HttpRequest buildRequest() {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(COUNTRIES_URL))
                    .headers(CONTENT_TYPE_HEADER, CONTENT_TYPE)
                    .GET()
                    .timeout(TIMEOUT)
                    .build();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

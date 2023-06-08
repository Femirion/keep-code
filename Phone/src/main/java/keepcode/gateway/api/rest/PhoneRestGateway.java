package keepcode.gateway.api.rest;

import keepcode.domain.entity.Phone;
import keepcode.gateway.api.PhoneApiGateway;
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

public class PhoneRestGateway implements PhoneApiGateway {
    private static final Logger log = LoggerFactory.getLogger(PhoneRestGateway.class);
    private static final String PHONES_URL = "https://onlinesim.ru/api/getFreePhoneList?country=";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String CONTENT_TYPE = "text/plain;charset=UTF-8";
    private final PhoneRestApiConverter phoneConverter;

    public PhoneRestGateway(PhoneRestApiConverter phoneRestApiConverter) {
        this.phoneConverter = phoneRestApiConverter;
    }

    @Override
    public CompletableFuture<List<Phone>> getPhonesByCountry(long countryId) {
        log.debug("prepare for loading phones from external system, by countryId: {}", countryId);
        HttpRequest request = buildRequest(countryId);
        CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder()
                    .build()
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return phoneConverter.convert(response);
    }

    private HttpRequest buildRequest(long countryId) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(PHONES_URL + countryId))
                    .headers(CONTENT_TYPE_HEADER, CONTENT_TYPE)
                    .GET()
                    .timeout(Duration.ofSeconds(5))
                    .build();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

}

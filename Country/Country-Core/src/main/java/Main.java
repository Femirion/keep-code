import gateway.api.rest.CountryRestApiConverter;
import gateway.api.CountryApiGateway;
import gateway.api.rest.parser.CountryRestApiErrorHandler;
import service.RestApiErrorHandler;
import gateway.api.rest.CountryRestGateway;
import gateway.api.rest.parser.CountryIdParser;
import gateway.api.rest.parser.CountryNameParser;
import gateway.api.rest.parser.CountryParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        CountryParser<String> countryNameParser = new CountryNameParser();
        CountryParser<Long> countryIdParser = new CountryIdParser();
        RestApiErrorHandler errorHandler = new CountryRestApiErrorHandler();
        CountryApiGateway gate = new CountryRestGateway(new CountryRestApiConverter(countryNameParser, countryIdParser, errorHandler));

        gate.getCountries().get()
                .forEach(c -> log.debug(c.toString()));
    }
}

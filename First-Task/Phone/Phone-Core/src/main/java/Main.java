import dto.dictionary.PhoneStatus;
import gateway.api.PhoneApiGateway;
import gateway.api.rest.PhoneRestApiConverter;
import gateway.api.rest.PhoneRestApiErrorHandler;
import gateway.api.rest.PhoneRestGateway;
import gateway.api.rest.parser.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.RestApiErrorHandler;

import java.time.LocalDateTime;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        PhoneParser<String> numberParser = new PhoneNumberParser();
        PhoneParser<Long> countryIdParser = new PhoneCountryIdParser();
        PhoneParser<LocalDateTime> updatedAtParser = new UpdatedAtParser();
        PhoneParser<String> dataHumansParser = new DataHumansParser();
        PhoneParser<String> fullNumberParser = new FullNumberParser();
        PhoneParser<String> countryNameParser = new CountryTextParser();
        PhoneParser<LocalDateTime> maxDateParser = new MaxDateParser();
        PhoneParser<PhoneStatus> phoneStatusParser = new StatusParser();
        RestApiErrorHandler errorHandler = new PhoneRestApiErrorHandler();

        PhoneApiGateway gate = new PhoneRestGateway(
                new PhoneRestApiConverter(
                        numberParser,
                        countryIdParser,
                        updatedAtParser,
                        dataHumansParser,
                        fullNumberParser,
                        countryNameParser,
                        maxDateParser,
                        phoneStatusParser,
                        errorHandler
                ));
        // 7 - Russia
        gate.getPhonesByCountry(7).get()
                .forEach(c -> log.info(c.toString()));
    }
}

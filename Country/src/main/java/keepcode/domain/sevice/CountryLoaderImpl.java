package keepcode.domain.sevice;

import exception.InteractionException;
import keepcode.domain.entity.Country;
import keepcode.gateway.api.CountryApiGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CountryLoaderImpl implements CountryLoader {
    private static final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);
    private final CountryApiGateway countryApiGateway;

    public CountryLoaderImpl(CountryApiGateway countryApiGateway) {
        this.countryApiGateway = countryApiGateway;
    }

    @Override
    public synchronized List<Country> load() {
        try {
            log.debug("start loading countries from external system");
            List<Country> result = countryApiGateway.getCountries().get();
            log.debug("finish loading countries from external system, size: {}", result.size());
            return result;
        } catch (Exception ex) {
            throw new InteractionException("can not load countries from external system", ex);
        }
    }
}

package domain.service;

import exception.InteractionException;
import domain.entity.Phone;
import gateway.api.PhoneApiGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PhoneLoaderImpl implements PhoneLoader {
    private static final Logger log = LoggerFactory.getLogger(PhoneLoaderImpl.class);
    private final PhoneApiGateway phoneApiGateway;

    public PhoneLoaderImpl(PhoneApiGateway phoneApiGateway) {
        this.phoneApiGateway = phoneApiGateway;
    }

    @Override
    public synchronized List<Phone> loadByCountry(long countryId) {
        try {
            log.debug("start loading phones from external system");
            List<Phone> result = phoneApiGateway.getPhonesByCountry(countryId).get();
            log.debug("finish loading phones from external system, size={}", result.size());
            return result;
        } catch (Exception ex) {
            throw new InteractionException("can not load phones from external system", ex);
        }
    }
}

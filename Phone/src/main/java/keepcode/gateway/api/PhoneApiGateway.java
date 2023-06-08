package keepcode.gateway.api;

import keepcode.domain.entity.Phone;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PhoneApiGateway {
    CompletableFuture<List<Phone>> getPhonesByCountry(long countryId);
}

package gateway.api;

import domain.entity.Phone;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PhoneApiGateway {
    CompletableFuture<List<Phone>> getPhonesByCountry(long countryId);
}

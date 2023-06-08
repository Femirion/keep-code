package keepcode.gateway.api;

import keepcode.domain.entity.Country;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CountryApiGateway {
    CompletableFuture<List<Country>> getCountries();
}

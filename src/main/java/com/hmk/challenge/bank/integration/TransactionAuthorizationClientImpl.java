package com.hmk.challenge.bank.integration;

import com.hmk.challenge.bank.business.port.out.TransactionAuthorizationClient;
import com.hmk.challenge.bank.integration.response.AuthorizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class TransactionAuthorizationClientImpl implements TransactionAuthorizationClient {

    private static String PATH_AUTHORIZED = "/authorized";
    private static String PATH_UNAUTHORIZED = "/unauthorized";

    private final RestTemplate restTemplate;

    @Value("${transaction.authorize.client.url}")
    private String url;

    @Override
    public boolean authorize(UUID accountId, BigDecimal amount) {
        var path = Objects.nonNull(amount) && amount.compareTo(BigDecimal.ONE) == 0 ? PATH_UNAUTHORIZED : PATH_AUTHORIZED;
        var request = new AuthorizationRequest(accountId, amount);
        var response = restTemplate.postForEntity(url + path, request, AuthorizationResponse.class);
        return HttpStatus.OK.value() == response.getStatusCode().value() && response.getBody().authorized();
    }

}

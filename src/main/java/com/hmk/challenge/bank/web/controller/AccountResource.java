package com.hmk.challenge.bank.web.controller;

import com.hmk.challenge.bank.business.port.in.CreateAccountUseCase;
import com.hmk.challenge.bank.business.port.in.FindAccountByIdUseCase;
import com.hmk.challenge.bank.web.request.AccountRequest;
import com.hmk.challenge.bank.web.response.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountResource {

    private final CreateAccountUseCase createAccountUseCase;

    private final FindAccountByIdUseCase findAccountByIdUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@RequestBody @Valid AccountRequest accountRequest) {
        var account = accountRequest.to();
        return AccountResponse.from(createAccountUseCase.execute(account));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse getById(@PathVariable("id") UUID accountId) {
        return AccountResponse.from(findAccountByIdUseCase.execute(accountId));
    }

}
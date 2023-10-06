package com.hmk.challenge.bank.web.controller;

import com.hmk.challenge.bank.business.port.in.SendTransferUseCase;
import com.hmk.challenge.bank.web.request.TransactionRequest;
import com.hmk.challenge.bank.web.response.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionResource {

    private final SendTransferUseCase sendTransferUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse transfer(@RequestBody @Valid TransactionRequest transactionRequest) {
        var transaction = transactionRequest.to();
        return TransactionResponse.from(sendTransferUseCase.execute(transaction));
    }

}
package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import com.garden.demo.account.model.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountHandler accountHandler;

    @PostMapping("/register")
    public Mono<ResponseEntity> registerAccount(@RequestBody AccountRequest account){
        return accountHandler.createAccount(account).flatMap(it ->
                Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("register account "+it.getUserName()+" success.")));
    }


}

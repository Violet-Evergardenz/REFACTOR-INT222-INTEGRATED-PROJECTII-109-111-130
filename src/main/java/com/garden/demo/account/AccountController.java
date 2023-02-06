package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import com.garden.demo.account.model.AccountRequest;
import com.garden.demo.account.model.AccountResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountHandler accountHandler;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/profile")
    public Mono<ResponseEntity> profileAccount(@RequestParam("id") String id){
       return accountHandler.getAccount(Integer.parseInt(id)).flatMap(it ->
               Mono.just(ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(it, AccountResponse.class))));
    }

    @PutMapping("/changepassword")
    public Mono<ResponseEntity> changePassword(@RequestBody AccountRequest account){
        return  accountHandler.editPassword(modelMapper.map(account,Account.class),account.getAccountId(),account.getNewPassword()).flatMap(it ->
                Mono.just(ResponseEntity.status(HttpStatus.OK).body(it)));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity> registerAccount(@RequestBody Account account){
        return  accountHandler.createAccount(account).flatMap(it ->
                Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("register account "+it.getUserName()+" success.")));
    }





}

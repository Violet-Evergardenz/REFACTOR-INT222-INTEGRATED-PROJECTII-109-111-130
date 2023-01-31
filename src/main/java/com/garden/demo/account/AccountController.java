package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import com.garden.demo.account.model.AccountDTO;
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
               Mono.just(ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(it, AccountDTO.class))));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity> registerAccount(@RequestBody Account account){
        return  accountHandler.createAccount(account).flatMap(it ->
                Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("register account "+it.getUserName()+" success.")));
    }


}

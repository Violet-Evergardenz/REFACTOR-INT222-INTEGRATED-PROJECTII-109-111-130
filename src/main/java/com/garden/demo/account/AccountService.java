package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import com.garden.demo.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountsRepository accountsRepository;

    public Mono<Account> saveAccount(Account acc){
        return Mono.just(accountsRepository.save(acc));
    }

    public Mono<Account> getAccount(long id) {
        return Mono.just(accountsRepository.findById(id).orElseThrow(()-> new MessageException(HttpStatus.NOT_FOUND,"find not found ")));
    }
//.orElseThrow(()-> new MessageException(HttpStatus.NOT_FOUND,"find not found "))
    public Mono<Account[]> getAllAccountByUserName(String username){
        return Mono.just(accountsRepository.findAllByUserName(username));
    }

    public Mono<Account> editPassword(Account acc){
        return Mono.just(accountsRepository.save(acc));
    }

    public Mono<Account> editProfile(Account account){
        return Mono.just(accountsRepository.save(account));
    }

    public void deleteAccount(long id){
       accountsRepository.deleteById(id);
    }
}

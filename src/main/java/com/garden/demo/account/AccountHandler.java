package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import com.garden.demo.account.model.AccountRequest;
import com.garden.demo.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class AccountHandler {
    private final String validatePasswordRegex  = "^([^0-9]*|[^A-Z]*|[^a-z]*|.{0,6}|[a-zA-z0-9]*)";
    private final String validateWhiteSpace  = "[^\s]*";

    @Autowired
    private AccountService accountService;

    public Mono<Account> getAccount(int id){
        return accountService.getAccount(id) ;
    }

    public Mono<String> editPassword (String oldPassword,long id, String newPassword){
        if(this.validateInfoRegister(newPassword,this.validatePasswordRegex)){
            throw new MessageException(HttpStatus.BAD_REQUEST,"invalid password at least seven characters, special character, number, upper case character ");
        }
        if(!this.validateInfoRegister(newPassword,this.validateWhiteSpace)){
            throw new MessageException(HttpStatus.BAD_REQUEST,"invalid password have whitespace");
        }
        return accountService.getAccount(id).flatMap(it -> {
            if(it.getPassword().equals(oldPassword)){
                it.setPassword(newPassword);
                accountService.editPassword(it);
                return Mono.just("Change password success.");
            } else {
                return Mono.error(new MessageException(HttpStatus.BAD_REQUEST,"password is incorrect"));
            }
        });
    }

    public Mono<Account> createAccount(Account acc) {
        if(this.validateInfoRegister(acc.getPassword(),this.validatePasswordRegex)){
            throw new MessageException(HttpStatus.BAD_REQUEST,"invalid password at least seven characters, special character, number, upper case character ");
        }
        if(!this.validateInfoRegister(acc.getPassword(),this.validateWhiteSpace)){
            throw new MessageException(HttpStatus.BAD_REQUEST,"invalid password have whitespace");
        }
        return accountService.getAllAccountByUserName(acc.getUserName()).flatMap(it -> {
            if(it.length == 0 || it == null){
                return accountService.saveAccount(acc);
            }else {
                return Mono.error(new MessageException(HttpStatus.BAD_REQUEST,"this username is already."));
            }
        });
    }

    public Mono<Account> editProfile(AccountRequest acc){
        return accountService.getAccount(acc.getAccountId()).flatMap(it -> {
            it.setAccountName(acc.getAccountName());
            return accountService.editProfile(it);
        });
    }

    public Mono<Account> deleteAccount(long id){
        return accountService.getAccount(id).flatMap(it -> {
            accountService.deleteAccount(id);
            return Mono.just(it);
        });
    }

    public Boolean validateInfoRegister (String str,String regex){
        if(str.matches(regex)){
            return true;
        }else{
            return false;
        }
    }

    public enum ACCOUNT_ROLE {
        ADMIN("ADMIN"),
        CUSTOMER("CUSTOMER")
        ;

        private final String code;

        ACCOUNT_ROLE(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}

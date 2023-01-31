package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import com.garden.demo.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {
    private final String validatePasswordRegex  = "^([^0-9]*|[^A-Z]*|[^a-z]*|.{0,6}|[a-zA-z0-9]*)";
    private final String validateWhiteSpace  = "[^\s]*";

    @Autowired
    private AccountService accountService;

    public Mono<Account> getAccount(int id){
        return accountService.getAccount(id);
    }

    public Mono<Account> createAccount(Account acc) {
        if(this.validateInfoRegister(acc.getPassword(),this.validatePasswordRegex)){
            throw new MessageException(HttpStatus.BAD_REQUEST,"invalid password at least seven characters, special character, number, upper case character ");
        }
        if(!this.validateInfoRegister(acc.getPassword(),this.validateWhiteSpace)){
            throw new MessageException(HttpStatus.BAD_REQUEST,"invalid password have whitespace");
        }
        return accountService.saveAccount(acc);
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

package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import com.garden.demo.account.model.AccountRequest;
import com.garden.demo.exception.MessageException;
import com.garden.demo.exception.ResponseEntityErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import reactor.core.publisher.Mono;

@Service
public class AccountHandler {

    @Autowired
    private AccountsRepository accountsRepository;

    public Mono<Account> createAccount(AccountRequest accReq) {
        if(this.validateInfoRegister(accReq)){
            throw new MessageException(HttpStatus.BAD_REQUEST,"invalid password");
        }
        Account newAccount = new Account(accReq.getAccountName(),
                accReq.getUserName(),
                accReq.getPassword(),
                ACCOUNT_ROLE.CUSTOMER.getCode());
        return Mono.just(accountsRepository.save(newAccount));
    }

    public Boolean validateInfoRegister (AccountRequest accouunt){
        String validatePasswordRegex  = "^([^0-9]*|[^A-Z]*|[^a-z]*|.{0,6}|[a-zA-z0-9]*|)";
        String validateWhiteSpace  = "[^\s]*";
        if(accouunt.getPassword().matches(validateWhiteSpace)){
            if(accouunt.getPassword().matches(validatePasswordRegex)){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
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

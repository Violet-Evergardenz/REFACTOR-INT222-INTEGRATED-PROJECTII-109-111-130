package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

public interface AccountsRepository extends JpaRepository<Account,Long> {
    Account[] findAllByUserName(String userName);
}

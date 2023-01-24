package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Account,Long> {
}

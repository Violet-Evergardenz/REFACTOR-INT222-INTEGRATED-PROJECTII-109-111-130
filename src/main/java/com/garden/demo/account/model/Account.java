package com.garden.demo.account.model;

import com.garden.demo.account.AccountHandler;
import jakarta.persistence.*;

@Entity
@Table(name="accounts")
public class Account {
  @Id
  @Column(name = "account_id")
  private long accountId ;
  private String accountName;
  @Column(name = "username")
  private String userName;
  private String password;
  private String accountRole = AccountHandler.ACCOUNT_ROLE.CUSTOMER.getCode();

  public Account(String accountName, String userName, String password) {
    this.accountName = accountName;
    this.userName = userName;
    this.password = password;
  }

  public Account() {

  }
//  public long getAccountId() {
//    return accountId;
//  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }


  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


//  public String getAccountRole() {
//    return accountRole;
//  }

  public void setAccountRole(String accountRole) {
    this.accountRole = accountRole;
  }

}

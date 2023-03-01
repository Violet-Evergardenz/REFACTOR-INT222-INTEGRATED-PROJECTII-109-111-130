package com.garden.demo.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garden.demo.account.model.Account;
import com.garden.demo.account.model.AccountRequest;
import com.garden.demo.account.model.AccountResponse;
import com.garden.demo.exception.MessageException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpStatusCodeException;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountHandler accountHandler;
    @Mock
    private AccountService accountService;
    @Mock
    private ModelMapper modelMapper;

    private MockMvc mockMvc;

    @Before
    public  void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void profileAccountSuccess() throws Exception {

        Account account = new Account();
        account.setAccountId(1);
        account.setAccountName("test");
        account.setPassword("test");
        account.setUserName("test");

        when(accountHandler.getAccount(1)).thenReturn(Mono.just(account));

        mockMvc.perform(MockMvcRequestBuilders.get("/account/profile").param("id","1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(accountHandler).getAccount(1);

    }

    @Test
    public void changePasswordSuccess() throws Exception {

//        AccountRequest accountRequest = new AccountRequest();
//        accountRequest.setAccountId(1);
//        accountRequest.setAccountName("test");
//        accountRequest.setPassword("test");
//        accountRequest.setNewPassword("test");
//        accountRequest.setUserName("test");

        String requestBody = "{\"accountId\":1,\"accountName\":\"test\",\"userName\":\"test\",\"password\":\"test\",\"newPassword\":\"test\"}";

//        when(modelMapper.map(accountRequest, Account.class)).thenReturn(account);
        when(accountHandler.editPassword("test", 1, "test"))
                .thenReturn(Mono.just("Change password success."));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/account/changepassword")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isOk());

    }

}

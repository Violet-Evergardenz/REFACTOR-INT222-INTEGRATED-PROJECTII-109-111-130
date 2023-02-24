package com.garden.demo.account;

import com.garden.demo.account.model.Account;
import com.garden.demo.account.model.AccountResponse;
import com.garden.demo.exception.MessageException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountHandler accountHandler;
    @Mock
    private AccountService accountService;


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


}

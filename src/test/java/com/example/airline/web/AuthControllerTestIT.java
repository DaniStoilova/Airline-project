package com.example.airline.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;




    private GreenMail greenMail;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(port, host,"smtp"));
        greenMail.start();
        greenMail.setUser(username, password);
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistration() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/users/register").
                            param("email", "lora@l.bg").
                            param("firstName", "lora").
                            param("lastName", "lora").
                            param("password", "7777").
                            param("confirmPassword", "7777").
                            param("phoneNumber", "76543").
                            with(csrf())
                    ).
                    andExpect(status().is3xxRedirection()).
                    andExpect(redirectedUrl("login"));

            MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
            Assertions.assertEquals(1, receivedMessages.length);

            MimeMessage welcomeMessage = receivedMessages[0];

            Assertions.assertTrue(welcomeMessage.getContent().toString().
                    contains("lora"));

    }

    @Test
    void testRegisterWithWrongInputDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").
                        param("email", "lora").
                        param("firstName", "lora").
                        param("lastName", "lora").
                        param("password", "7777").
                        param("confirmPassword", "7777").
                        param("phoneNumber", "1234567").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("register"));

    }
    @Test
    void testLoginError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error")
                        .param("email", "test@test.bg")
                        .param("password", "4321")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));
    }
    @Test
    void testShowLogin() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/users/login")).
                    andExpect(status().isOk()).
                    andExpect(view().name("login"));

    }
    @Test
    void testLoginWrongInputData() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login").
                        param("email", "test@bg").
                        param("password", "432").
                        with(csrf()))
                .andExpect(status().is2xxSuccessful());

    }




}

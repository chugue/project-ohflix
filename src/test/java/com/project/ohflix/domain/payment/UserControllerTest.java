package com.project.ohflix.domain.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ohflix.domain.profileIcon.ProfileIconResponse;
import com.project.ohflix.domain.user.SessionUser;
import com.project.ohflix.domain.user.UserRepository;
import com.project.ohflix.domain.user.UserResponse;
import com.project.ohflix.domain.user.UserService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@Sql("classpath:db/data.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager em;


    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        em.clear();

    }

    @Test
    public void profile_setting_success_test() throws Exception {
        // given
        MockHttpSession session = new MockHttpSession();
        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(2);
        session.setAttribute("sessionUser", sessionUser);
        // when
        MvcResult result = mockMvc.perform(get("/api/profile-setting")
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        HttpServletRequest request = result.getRequest();
        UserResponse.ProfileSettingDTO ProfileSettingDTO =
                (UserResponse.ProfileSettingDTO) request.getAttribute("ProfileSettingDTO");

        assertThat(ProfileSettingDTO).isNotNull();
        assertThat(ProfileSettingDTO.getUserId()).isEqualTo(2);

    }
}

package com.project.ohflix.domain.profileIcon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.user.SessionUser;
import com.project.ohflix.domain.user.UserRepository;
import com.project.ohflix.domain.user.UserService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@Sql("classpath:db/data.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ProfileIconControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private EntityManager em;
    private MockHttpSession session;
    @MockBean
    private ProfileIconService profileIconService;


    @Test
    public void ProfileIcon_test() throws Exception {

        // GET 요청 및 검증
        MvcResult result = mvc.perform(get("/api/profile-icons"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/profile-icons"))
                .andExpect(model().attributeExists("profileIconList"))
                .andReturn();

        // 모델 속성 값 검증
        List<ProfileIconResponse.ProfileIconListDTO> profileIconList =
                (List<ProfileIconResponse.ProfileIconListDTO>) result.getModelAndView().getModel().get("profileIconList");

        System.out.println(profileIconList.toString());

        assertThat(profileIconList).isNotNull();
        assertThat(profileIconList.get(0).getName()).isEqualTo("Icon 1"); // 첫 번째 아이콘의 이름 검증
        assertThat(profileIconList.get(0).getPath()).isEqualTo("/static/images/profiles/netflix-profile.png"); // 첫 번째 아이콘의 경로 검증
    }


}
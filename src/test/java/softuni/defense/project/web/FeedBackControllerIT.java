package softuni.defense.project.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import softuni.defense.project.config.UserAuthProvider;
import softuni.defense.project.model.dtos.UserDto;
import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.entities.UserRoleEntity;
import softuni.defense.project.model.enums.UserRoleEnum;
import softuni.defense.project.repositories.FeedbackRepository;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.repositories.UserRoleRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedBackControllerIT {

    @Autowired
    private MockMvc mockedMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserAuthProvider manager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        if (userRoleRepository.findByRole(UserRoleEnum.USER).isEmpty()) {
            this.userRoleRepository.save(new UserRoleEntity(UserRoleEnum.USER));
        }
        if (userRepository.findByEmail("anna@example.com").isEmpty()) {
        String encodedPass = passwordEncoder.encode("topsecret123");
            UserRoleEntity roleEntity = userRoleRepository.findByRole(UserRoleEnum.USER).get();
            this.userRepository.save(new UserEntity("anna@example.com", encodedPass, "Anna", "Dusseldorf", List.of(roleEntity)));
        }
    }

    @Test
    public void testSubmitUserFeedbackOK() throws Exception {
        String jsonPayload = "{\n" +
                "  \"inputFirstName\": \"Anna\",\n" +
                "  \"selectedOption\": \"SATISFIED\",\n" +
                "  \"reasonDescription\": \"Explore cars.\",\n" +
                "  \"adviceDescription\": \"Keep up the good job!\",\n" +
                "  \"qualityServiceRate\": 5,\n" +
                "  \"timelinessRate\": 4,\n" +
                "  \"customerServiceRate\": 5,\n" +
                "  \"priceRate\": 3,\n" +
                "  \"cleanlinessRate\": 4,\n" +
                "  \"recommendOption\": \"Probably\",\n" +
                "  \"ownerUser\": \"anna@example.com\"\n" +
                "}";

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Authentication auth = login("anna@example.com", "topsecret123");


        mockMvc.perform(post("http://localhost:8080/feedback")
                        .principal(auth)
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                )
                .andDo(print())
                .andExpect(status().isOk());

        List<FeedbackEntity> all = feedbackRepository.findAll();

        Assertions.assertFalse(all.isEmpty());

        FeedbackEntity feedback = all.get(0);

        Assertions.assertEquals("Anna", feedback.getInputFirstName());
        Assertions.assertEquals("SATISFIED", feedback.getSelectedOption());
        Assertions.assertEquals("Explore cars.", feedback.getReasonDescription());
        Assertions.assertEquals("Keep up the good job!", feedback.getAdviceDescription());
        Assertions.assertEquals(5, feedback.getQualityServiceRate());
        Assertions.assertEquals(4, feedback.getTimelinessRate());
        Assertions.assertEquals(5, feedback.getCustomerServiceRate());
        Assertions.assertEquals(3, feedback.getPriceRate());
        Assertions.assertEquals(4, feedback.getCleanlinessRate());
        Assertions.assertEquals("Probably", feedback.getRecommendOption());
        Assertions.assertEquals("anna@example.com", feedback.getOwnerUser().getEmail());

    }

    @Test
    public void testSubmitUserFeedbackUnauthorized() throws Exception {
        String jsonPayload = "{\n" +
                "  \"inputFirstName\": \"Anna\",\n" +
                "  \"selectedOption\": \"SATISFIED\",\n" +
                "  \"reasonDescription\": \"Explore cars.\",\n" +
                "  \"adviceDescription\": \"Keep up the good job!\",\n" +
                "  \"qualityServiceRate\": 5,\n" +
                "  \"timelinessRate\": 4,\n" +
                "  \"customerServiceRate\": 5,\n" +
                "  \"priceRate\": 3,\n" +
                "  \"cleanlinessRate\": 4,\n" +
                "  \"recommendOption\": \"Probably\",\n" +
                "  \"ownerUser\": \"anna@example.com\"\n" +
                "}";



        mockedMvc.perform(post("http://localhost:8080/feedback")
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    private Authentication login(String name, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password);
        UserEntity userEntity = this.userRepository.findByEmail(name).get();

        UserDto userDto = new UserDto(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getRoles().get(0).getRole()
        );
        String token = manager.createToken(userDto);
        SecurityContextHolder.getContext().setAuthentication(manager.validateToken(token));
        return auth;
    }
}

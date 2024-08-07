package softuni.defense.project.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.entities.UserRoleEntity;
import softuni.defense.project.model.enums.UserRoleEnum;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.repositories.UserRoleRepository;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @BeforeEach
    void setUp() {
        this.userRoleRepository.save(new UserRoleEntity(UserRoleEnum.USER));
    }

    @Test
    public void testRegistration() throws Exception {

        String jsonPayload = "{\n" +
                "  \"email\": \"anna@example.com\",\n" +
                "  \"password\": \"topsecret123\",\n" +
                "  \"firstName\": \"Anna\",\n" +
                "  \"lastName\": \"Dusseldorf\"\n" +
                "}";


        mockMvc.perform(post("http://localhost:8080/users/register")
                        .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());

        Optional<UserEntity> userEntityOpt = userRepository.findByEmail("anna@example.com");

        Assertions.assertTrue(userEntityOpt.isPresent());

        UserEntity userEntity = userEntityOpt.get();

        Assertions.assertEquals("anna@example.com", userEntity.getEmail());
        Assertions.assertEquals("Anna", userEntity.getFirstName());
        Assertions.assertEquals("Dusseldorf", userEntity.getLastName());

        Assertions.assertTrue(passwordEncoder.matches("topsecret123", userEntity.getPassword()));

        Assertions.assertEquals("USER", userEntity.getRoles().get(0).getRole().name());

    }
}

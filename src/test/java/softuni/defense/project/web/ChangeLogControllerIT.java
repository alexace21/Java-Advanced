package softuni.defense.project.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import softuni.defense.project.config.UserAuthProvider;
import softuni.defense.project.model.dtos.UserDto;
import softuni.defense.project.model.entities.*;
import softuni.defense.project.model.enums.TypeChangeEnum;
import softuni.defense.project.model.enums.UserRoleEnum;
import softuni.defense.project.repositories.*;
import softuni.defense.project.service.impl.ChangeLogServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ChangeLogControllerIT {

    @Autowired
    private MockMvc mockedMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserAuthProvider manager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChangeLogServiceImpl changeLogService;
    @Autowired
    private UserChangeLogRepository userChangeLogRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        if (userRoleRepository.findByRole(UserRoleEnum.ADMIN).isEmpty()) {
            this.userRoleRepository.save(new UserRoleEntity(UserRoleEnum.ADMIN));
        }
        if (userRepository.findByEmail("aleks.asenov@outlook.com").isEmpty()) {
            String encodedPass = passwordEncoder.encode("topsecret123");
            UserRoleEntity roleEntity = userRoleRepository.findByRole(UserRoleEnum.ADMIN).get();
            UserEntity savedUserEntity = this.userRepository.save(new UserEntity("aleks.asenov@outlook.com", encodedPass, "Axel", "Asenov", List.of(roleEntity)));

            changeLogService.createUserChangeLog(savedUserEntity);
        }
    }


    @Test
    public void testGetAllFeedbackLogsUnauthorized() throws Exception {
        mockedMvc.perform(get("http://localhost:8080/change-log/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void testGetAllRegisteredUsersOk() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Authentication auth = login("aleks.asenov@outlook.com", "topsecret123");

        mockMvc.perform(get("http://localhost:8080/change-log/users")
                        .principal(auth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                )
                .andDo(print())
                .andExpect(status().isOk());

        List<UserChangeLogEntity> logEntities = userChangeLogRepository.findAll();

        Assertions.assertFalse(logEntities.isEmpty());

        UserChangeLogEntity userChangeLogEntity = logEntities.stream().filter(log -> log.getEmail().equals("aleks.asenov@outlook.com")).findFirst().get();

        Assertions.assertEquals("aleks.asenov@outlook.com", userChangeLogEntity.getEmail());
        Assertions.assertEquals("ADMIN", userChangeLogEntity.getRole());
        Assertions.assertEquals("Axel", userChangeLogEntity.getFirstName());
        Assertions.assertEquals("Asenov", userChangeLogEntity.getLastName());
        Assertions.assertEquals("NEW", userChangeLogEntity.getNewValue());
        Assertions.assertEquals(TypeChangeEnum.CREATION, userChangeLogEntity.getTypeChange());
        Assertions.assertEquals(LocalDate.now(), userChangeLogEntity.getRegisteredDate());
        
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

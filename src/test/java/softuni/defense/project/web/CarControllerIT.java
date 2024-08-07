package softuni.defense.project.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import softuni.defense.project.config.UserAuthProvider;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.model.dtos.UserDto;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.entities.UserRoleEntity;
import softuni.defense.project.model.enums.UserRoleEnum;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.repositories.UserRoleRepository;
import softuni.defense.project.service.impl.CarServiceImpl;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIT {
    @Autowired
    private MockMvc mockedMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserAuthProvider manager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @MockBean
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        if (userRoleRepository.findByRole(UserRoleEnum.ADMIN).isEmpty()) {
            this.userRoleRepository.save(new UserRoleEntity(UserRoleEnum.ADMIN));
        }
        if (userRepository.findByEmail("aleks.asenov2@outlook.com").isEmpty()) {
            String encodedPass = passwordEncoder.encode("topsecret123");
            UserRoleEntity roleEntity = userRoleRepository.findByRole(UserRoleEnum.ADMIN).get();
            this.userRepository.save(new UserEntity("aleks.asenov2@outlook.com", encodedPass, "Axel", "Asenov", List.of(roleEntity)));
        }

    }


    @Test
    public void testCreateCarOfferUnauthorized() throws Exception {
        String jsonPayload = "{\n" +
                "  \"city_mpg\": 9,\n" +
                "  \"combination_mpg\": 8,\n" +
                "  \"cylinders\": 6,\n" +
                "  \"displacement\": 4.5,\n" +
                "  \"drive\": \"RWD\",\n" +
                "  \"fuel_type\": \"Gas\",\n" +
                "  \"highway_mpg\": 7,\n" +
                "  \"make\": \"Toyota\",\n" +
                "  \"model\": \"Corolla\",\n" +
                "  \"transmission\": \"Automatic\",\n" +
                "  \"year\": 2012,\n" +
                "  \"price\": \"3000\"\n" +
                "  \"owner\": \"aleks.asenov2@outlook.com\"\n" +
                "}";



        mockedMvc.perform(post("http://localhost:8080/cars/create")
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateCarOfferOk() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Authentication auth = login("aleks.asenov2@outlook.com", "topsecret123");

        String jsonPayload = "{\n" +
                "  \"city_mpg\": 9,\n" +
                "  \"combination_mpg\": 8,\n" +
                "  \"cylinders\": 6,\n" +
                "  \"displacement\": 4.5,\n" +
                "  \"drive\": \"RWD\",\n" +
                "  \"fuel_type\": \"Gas\",\n" +
                "  \"highway_mpg\": 7,\n" +
                "  \"make\": \"Toyota\",\n" +
                "  \"model\": \"Corolla\",\n" +
                "  \"transmission\": \"Automatic\",\n" +
                "  \"year\": 2012,\n" +
                "  \"price\": \"3000\",\n" +
                "  \"owner\": \"aleks.asenov2@outlook.com\"\n" +
                "}";

        CarDTO expectedResult = new CarDTO(
               Long.valueOf(1), Long.valueOf(9), Long.valueOf(8), 6, 4.5, "RWD", "Gas", Long.valueOf(7), "Toyota", "Corolla", "Automatic", Long.valueOf(2012), "3000", "aleks.asenov2@outlook.com"
        );
        Mockito.when(carService.createCarOffer(expectedResult)).thenReturn(expectedResult);

        mockMvc.perform(post("http://localhost:8080/cars/create")
                        .principal(auth)
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                )
                .andDo(print())
                .andExpect(status().isCreated());
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

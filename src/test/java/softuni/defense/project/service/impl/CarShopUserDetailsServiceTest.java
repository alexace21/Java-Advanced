package softuni.defense.project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.entities.UserRoleEntity;
import softuni.defense.project.model.enums.UserRoleEnum;
import softuni.defense.project.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarShopUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CarShopUserDetailsService userDetailsService;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("encryptedPassword");
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setRoles(List.of(new UserRoleEntity(UserRoleEnum.ADMIN))); // Assuming UserRoleEntity and UserRoleEnum exist
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("encryptedPassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));

        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void loadUserByUsername_ShouldThrowUsernameNotFoundException_WhenUserDoesNotExist() {
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("unknown@example.com");
        });

        verify(userRepository).findByEmail("unknown@example.com");
    }
}

package softuni.defense.project.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import softuni.defense.project.config.exception.UserAuthenticationEntryPoint;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.service.impl.CarShopUserDetailsService;

@Configuration
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthProvider userAuthProvider;

    public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint, UserAuthProvider userAuthProvider) {
        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
        this.userAuthProvider = userAuthProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        // Setup which URL-s are available to who
                        authorizeRequests ->
                                authorizeRequests
                                        // all static resources to "common locations" (css, images, js) are available to anyone
                                        // some more resources for all users
                                        .requestMatchers("/", "/users/login-error","/users/login", "/users/register", "/error", "/shop", "/api/convert").permitAll()
                                        // all other URL-s should be authenticated.
                                        .anyRequest()
                                        .authenticated()
                )
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).disable()
                .build();
    }

    @Bean
    public CarShopUserDetailsService userDetailsService(UserRepository userRepository) {
        return new CarShopUserDetailsService(userRepository);
    }
}

//                .formLogin(formLogin ->
//                        formLogin
//                                // Where is our custom login form?
//                                .loginPage("/users/login")
//                                // what is the name of the username parameter in the Login POST request?
//                                .usernameParameter("email")
//                                // what is the name of the password parameter in the Login POST request?
//                                .passwordParameter("password")
//                                // What will happen if the login is successful
//                                .defaultSuccessUrl("/", false)
//                                // What will happen if the login fails
//                                .failureForwardUrl("/users/login-error")
//                )
//                .logout(
//                        logout ->
//                                logout
//                                        // what is the logout URL?
//                                        .logoutUrl("/users/logout")
//                                        // Where to go after successful logout?
//                                        .logoutSuccessUrl("/")
//                                        // invalidate the session after logout.
//                                        .invalidateHttpSession(true)
//                )

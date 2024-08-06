package softuni.defense.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import softuni.defense.project.config.exception.UserAuthenticationEntryPoint;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.service.impl.CarShopUserDetailsService;

@Configuration
@EnableMethodSecurity
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
                                        .requestMatchers("/","/users/login", "/users/register", "/shop", "/api/convert").permitAll()
                                        // all other URL-s should be authenticated.
                                        .anyRequest()
                                        .authenticated()
                )
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    @Bean
    public CarShopUserDetailsService userDetailsService(UserRepository userRepository) {
        return new CarShopUserDetailsService(userRepository);
    }
}


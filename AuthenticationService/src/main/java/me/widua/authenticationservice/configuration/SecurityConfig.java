package me.widua.authenticationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final PasswordEncoder encoder ;
    private final

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and().build();
    }

    @Bean
    DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider() ;
        provider.setUserDetailsService();
        provider.setPasswordEncoder();
        return provider ;
    }


}

package me.widua.authenticationservice.configuration;

import me.widua.authenticationservice.JWT.JWTAuthenticationFilter;
import me.widua.authenticationservice.JWT.JWTAuthorizationFilter;
import me.widua.authenticationservice.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final PasswordEncoder encoder ;
    private final UserDetailsServiceImpl userDetailsService ;

    private final AuthenticationManager authManager ;

    @Autowired
    SecurityConfig( PasswordEncoder encoder , UserDetailsServiceImpl userDetailsService , AuthenticationManager authManager){
        this.encoder = encoder ;
        this.userDetailsService = userDetailsService ;
        this.authManager = authManager ;
    }

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .addFilter(new JWTAuthenticationFilter(authManager))
                .addFilter(new JWTAuthorizationFilter(authManager))
                .build();
    }

    @Bean
    DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider() ;
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider ;
    }


}

package me.widua.authenticationservice.configuration;

import me.widua.authenticationservice.JWT.JWTAuthenticationFilter;
import me.widua.authenticationservice.JWT.JWTAuthorizationFilter;
import me.widua.authenticationservice.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig {

    private final PasswordEncoder encoder ;
    private final UserDetailsServiceImpl userDetailsService ;
    private final AuthenticationConfiguration authenticationConfiguration ;

    @Autowired
    SecurityConfig( PasswordEncoder encoder ,
                    UserDetailsServiceImpl userDetailsService ,
                    AuthenticationConfiguration authenticationConfiguration){
        this.encoder = encoder ;
        this.userDetailsService = userDetailsService ;
        this.authenticationConfiguration = authenticationConfiguration ;
    }

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .httpBasic().and().build() ;
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider() ;
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider ;
    }

}

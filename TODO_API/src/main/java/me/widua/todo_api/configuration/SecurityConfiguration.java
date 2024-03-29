package me.widua.todo_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

   @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
       return http
               .httpBasic()
               .and().build();
   }

}

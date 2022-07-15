package me.widua.todo_api.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TODO_API_Config {

    @Bean
    @LoadBalanced
    RestTemplate template(){
        return new RestTemplate() ;
    }

}

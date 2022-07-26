package me.widua.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseModel{
    private String username ;
    private HttpStatus registerStatus ;
    private String optionalMessage ;
}

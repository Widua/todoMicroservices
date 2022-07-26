package me.widua.authenticationservice.JWT;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@NoArgsConstructor
@Getter
@Configuration
public class JWTTokenUtils {

    private final String secret = "63e946770109a3c18affc8b6b8e7e84aacffd2ba264f853259a996bb43bf3a0d";

    public Date getExpireDate(){
        return new Date( System.currentTimeMillis() + 300000 ) ;
    }
}

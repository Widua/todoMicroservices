package me.widua.authenticationservice.JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import me.widua.authenticationservice.models.LoginModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager manager ;

    private final JWTTokenUtils jwtConfig = new JWTTokenUtils() ;

    public JWTAuthenticationFilter(AuthenticationManager manager){
        this.manager = manager ;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            LoginModel user = new ObjectMapper().readValue(request.getInputStream() , LoginModel.class) ;
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername() , user.getPassword()) ;
            return manager.authenticate(auth);
         } catch (IOException ex){
            throw new RuntimeException("Filter can't read request!") ;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .setExpiration(jwtConfig.getExpireDate())
                .signWith(Keys.hmacShaKeyFor( jwtConfig.getSecret().getBytes() ))
                .compact() ;
        response.addHeader("Authorization","Bearer "+token);
    }
}
package me.widua.authenticationservice.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTTokenUtils jwtConfig = new JWTTokenUtils() ;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization") ;
        if (header == null || !header.startsWith("Bearer")){
            chain.doFilter(request,response);
            return ;
        }

        UsernamePasswordAuthenticationToken authToken = getAuthentication(request) ;
        SecurityContextHolder.getContext().setAuthentication(authToken);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ","");
        String username = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        if (username != null){
            return new UsernamePasswordAuthenticationToken(username,null,null) ;
        }
        return null ;
    }
}

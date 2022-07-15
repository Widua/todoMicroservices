package me.widua.authenticationservice.security;

import me.widua.authenticationservice.repositories.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserModelRepository repository ;

    @Autowired
    UserDetailsServiceImpl(UserModelRepository repository){
        this.repository = repository ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getUserModelByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException(String.format("USER %s DOES NOT EXIST",username)) );
    }
}

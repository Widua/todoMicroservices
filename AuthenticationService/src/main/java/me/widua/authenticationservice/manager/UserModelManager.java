package me.widua.authenticationservice.manager;

import me.widua.authenticationservice.models.RegisterResponseModel;
import me.widua.authenticationservice.models.UserModel;
import me.widua.authenticationservice.models.UserRegisterModel;
import me.widua.authenticationservice.repositories.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserModelManager {

    private final UserModelRepository repository ;
    private final PasswordEncoder encoder ;

    @Autowired
    UserModelManager(UserModelRepository repository , PasswordEncoder encoder){
        this.repository = repository ;
        this.encoder = encoder ;
    }

    public ResponseEntity<RegisterResponseModel> registerUser (UserRegisterModel userRegisterModel){
        UserModel user = new UserModel() ;
        user.setAuthorities(Set.of( new SimpleGrantedAuthority("ROLE_USER")));
        user.setUsername(userRegisterModel.getUsername());

        if (repository.getUserModelByUsername(user.getUsername()).isPresent()){
            RegisterResponseModel response = new RegisterResponseModel(user.getUsername() , HttpStatus.BAD_REQUEST , "USER EXIST!") ;
            return ResponseEntity.badRequest().body(response);
        }

        if (!userRegisterModel.getPassword().equals(userRegisterModel.getRetypedPassword())){
            RegisterResponseModel response = new RegisterResponseModel( user.getUsername() , HttpStatus.BAD_REQUEST , "Passwords not match!") ;
            return ResponseEntity.badRequest().body(response);
        }
        user.setPassword(encoder.encode(userRegisterModel.getPassword()));
        user.setEnabled(true);
        repository.save(user) ;
        return ResponseEntity.ok(new RegisterResponseModel(user.getUsername() , HttpStatus.OK , "User created!"));
    }



}

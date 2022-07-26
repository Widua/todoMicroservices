package me.widua.authenticationservice.manager;

import me.widua.authenticationservice.models.RegisterResponseModel;
import me.widua.authenticationservice.models.UserModel;
import me.widua.authenticationservice.models.UserRegisterModel;
import me.widua.authenticationservice.repositories.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserModelManager {

    private final UserModelRepository repository ;

    @Autowired
    UserModelManager(UserModelRepository repository){
        this.repository = repository ;
    }

    public ResponseEntity<RegisterResponseModel> registerUser (UserRegisterModel userRegisterModel){
        UserModel user = new UserModel() ;
        user.setAuthorities(Set.of( new SimpleGrantedAuthority("ROLE_USER")));
        user.setUsername(userRegisterModel.getUsername());

        if (!userRegisterModel.getPassword().equals(userRegisterModel.getRetypedPassword())){
            RegisterResponseModel response = new RegisterResponseModel( user.getUsername() , HttpStatus.BAD_REQUEST , "Passwords not match!") ;
            return ResponseEntity.badRequest().body(response);
        }
        user.setPassword(userRegisterModel.getPassword());
        user.setEnabled(true);
        repository.save(user) ;
        return ResponseEntity.ok(new RegisterResponseModel(user.getUsername() , HttpStatus.OK , "User created!"));
    }



}

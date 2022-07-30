package me.widua.authenticationservice.api;

import me.widua.authenticationservice.manager.UserModelManager;
import me.widua.authenticationservice.models.RegisterResponseModel;
import me.widua.authenticationservice.models.UserRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserAPI {

    private final UserModelManager manager ;

    @Autowired
    public UserAPI(UserModelManager manager){
        this.manager = manager ;
    }

    @GetMapping("/test")
    public String getTest(){
        return "TEST PASSED";
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<RegisterResponseModel> register(@RequestBody UserRegisterModel registerModel){
        return manager.registerUser(registerModel) ;
    }


}

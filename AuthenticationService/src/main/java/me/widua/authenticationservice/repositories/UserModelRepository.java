package me.widua.authenticationservice.repositories;

import me.widua.authenticationservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserModelRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> getUserModelByUsername(String username) ;
}
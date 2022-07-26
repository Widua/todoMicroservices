package me.widua.authenticationservice.repositories;

import me.widua.authenticationservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> getUserModelByUsername(String username) ;
}
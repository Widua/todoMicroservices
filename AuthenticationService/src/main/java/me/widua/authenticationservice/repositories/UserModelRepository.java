package me.widua.authenticationservice.repositories;

import me.widua.authenticationservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModelRepository extends JpaRepository<UserModel, Integer> {
}
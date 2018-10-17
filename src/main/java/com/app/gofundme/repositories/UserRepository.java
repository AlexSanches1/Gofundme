package com.app.gofundme.repositories;

import com.app.gofundme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserByEmailAndPassword(String email, String password);
}

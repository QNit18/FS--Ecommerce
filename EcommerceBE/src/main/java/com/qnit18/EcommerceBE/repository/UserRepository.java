package com.qnit18.EcommerceBE.repository;

import com.qnit18.EcommerceBE.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

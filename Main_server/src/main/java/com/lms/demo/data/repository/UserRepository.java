package com.lms.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lms.demo.data.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsername(String username);
}
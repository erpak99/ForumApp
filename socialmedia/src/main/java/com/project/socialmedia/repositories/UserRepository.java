package com.project.socialmedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmedia.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

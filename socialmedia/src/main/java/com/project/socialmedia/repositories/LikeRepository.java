package com.project.socialmedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmedia.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}

package com.project.socialmedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmedia.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByUser_Id(Long userId);
}

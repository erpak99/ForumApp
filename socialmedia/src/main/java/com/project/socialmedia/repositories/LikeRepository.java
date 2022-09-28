package com.project.socialmedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmedia.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	List<Like> findByUser_IdAndPost_Id(Long userId, Long postId);

	List<Like> findByUser_Id(Long userId);

	List<Like> findByPost_Id(Long postId);

}

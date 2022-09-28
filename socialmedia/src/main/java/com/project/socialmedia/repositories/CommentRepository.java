package com.project.socialmedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmedia.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByUser_IdAndPost_Id(Long userId, Long postId);

	List<Comment> findByUser_Id(Long userId);

	List<Comment> findByPost_Id(Long postId);

}

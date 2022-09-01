package com.project.socialmedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.socialmedia.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> getByUser_Id(Long id);

	List<Comment> getByPost_Id(Long id);

}

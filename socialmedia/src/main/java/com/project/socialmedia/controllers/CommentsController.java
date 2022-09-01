package com.project.socialmedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmedia.core.results.DataResult;
import com.project.socialmedia.entities.Comment;
import com.project.socialmedia.requests.CommentCreateRequest;
import com.project.socialmedia.requests.CommentUpdateRequest;
import com.project.socialmedia.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentsController {

	private CommentService commentService;
	
	public CommentsController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Comment>> getAllComments() {
		return commentService.getAllComments();
	}
	
	@GetMapping("/{id}")
	public DataResult<Comment> getCommentById(@PathVariable Long id) {
		return commentService.getCommentById(id);										
	}
	
	@GetMapping("/getcommentsbyuserid")
	public DataResult<List<Comment>> getCommentsByUserId(@RequestParam Long id) {
		return commentService.getCommentsByUserId(id);
	}
	
	@GetMapping("/getcommentsbypostid")
	public DataResult<List<Comment>> getCommentsByPostId(@RequestParam Long id) {
		return commentService.getCommentsByPostId(id);
	}
	
	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest newCommentRequest) {
		return commentService.createOneComment(newCommentRequest);
	}
	
	@PutMapping("/{id}")
	public DataResult<Comment> updateOneComment(@PathVariable Long id, @RequestBody CommentUpdateRequest updateComment) {
		return commentService.updateOneComment(id, updateComment);			
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneComment(@PathVariable Long id) {
		commentService.deleteOneComment(id);
	}



}

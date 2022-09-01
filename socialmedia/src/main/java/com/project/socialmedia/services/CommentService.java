package com.project.socialmedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.socialmedia.core.results.DataResult;
import com.project.socialmedia.core.results.SuccessDataResult;
import com.project.socialmedia.entities.Comment;
import com.project.socialmedia.entities.Post;
import com.project.socialmedia.entities.User;
import com.project.socialmedia.repositories.CommentRepository;
import com.project.socialmedia.requests.CommentCreateRequest;
import com.project.socialmedia.requests.CommentUpdateRequest;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private PostService postService;
	private UserService userService;

	public CommentService(CommentRepository commentRepository, PostService postService, UserService userService) {
		super();
		this.commentRepository = commentRepository;
		this.postService = postService;
		this.userService = userService;
	}

	public DataResult<List<Comment>> getAllComments() {
		return new SuccessDataResult<List<Comment>>(commentRepository.findAll(),"All comments...");
	}

	public DataResult<List<Comment>> getCommentsByUserId(Long id) {
		return new SuccessDataResult<List<Comment>>(commentRepository.getByUser_Id(id),"All comments by specific user id...");
	}

	public DataResult<List<Comment>> getCommentsByPostId(Long id) {
		return new SuccessDataResult<List<Comment>>(commentRepository.getByPost_Id(id),"All comments by specific post id...");
	}

	public DataResult<Comment> getCommentById(Long id) {
		return new SuccessDataResult<Comment>(commentRepository.findById(id).orElse(null), "Comment by specific id...") ;
	}

	public Comment createOneComment(CommentCreateRequest newCommentRequest) {
		User user = userService.getOneUserById(newCommentRequest.getUserId());
		Post post = postService.getPostById(newCommentRequest.getPostId());
		if(user != null && post != null) {
			Comment toSave = new Comment();
			toSave.setId(newCommentRequest.getId());
			toSave.setPost(post);
			toSave.setUser(user);
			toSave.setText(newCommentRequest.getText());
			return commentRepository.save(toSave);
		} else
			return null;
	}

	public DataResult<Comment> updateOneComment(Long id, CommentUpdateRequest updateComment) {
		Optional<Comment> comment = commentRepository.findById(id);
		if(comment.isPresent()) {
			Comment toUpdate = comment.get();
			toUpdate.setText(updateComment.getText());
			commentRepository.save(toUpdate);
			return new SuccessDataResult<Comment>(toUpdate, "Comment is updated...");
		} else
			return null;
	}

	public void deleteOneComment(Long id) {
		commentRepository.deleteById(id);
	}
	
	
}

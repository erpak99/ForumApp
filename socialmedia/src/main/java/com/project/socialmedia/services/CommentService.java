package com.project.socialmedia.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.socialmedia.core.results.DataResult;
import com.project.socialmedia.core.results.SuccessDataResult;
import com.project.socialmedia.entities.Comment;
import com.project.socialmedia.entities.Post;
import com.project.socialmedia.entities.User;
import com.project.socialmedia.repositories.CommentRepository;
import com.project.socialmedia.requests.CommentCreateRequest;
import com.project.socialmedia.requests.CommentUpdateRequest;
import com.project.socialmedia.responses.CommentResponse;

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

	public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId,
		     Optional<Long> postId) {
		List<Comment> comments;
		if(userId.isPresent() && postId.isPresent()) {
			comments = commentRepository.findByUser_IdAndPost_Id(userId.get(), postId.get());
		}else if(userId.isPresent()) {
			comments = commentRepository.findByUser_Id(userId.get());
		}else if(postId.isPresent()) {
			comments = commentRepository.findByPost_Id(postId.get());
		}else
			comments = commentRepository.findAll();
		return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
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

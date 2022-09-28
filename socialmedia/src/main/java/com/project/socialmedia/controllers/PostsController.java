package com.project.socialmedia.controllers;

import java.util.List;
import java.util.Optional;

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
import com.project.socialmedia.entities.Post;
import com.project.socialmedia.requests.PostCreateRequest;
import com.project.socialmedia.requests.PostUpdateRequest;
import com.project.socialmedia.responses.PostResponse;
import com.project.socialmedia.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostsController {

	private PostService postService;

	public PostsController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
		return this.postService.getAllPosts(userId);
	}
	
	
	@GetMapping("/{postId}")
	public Post getPostById(@PathVariable Long postId) {
		return this.postService.getPostById(postId);
	}
										
	@PostMapping
	public Post createOnePost(@RequestBody PostCreateRequest newPostCreateRequest) {
		return this.postService.createOnePost(newPostCreateRequest);
	}
	
	@PutMapping("/{postId}")
	public DataResult<Post> updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest newPostUpdateRequest ) {
		return this.postService.updateOnePost(postId, newPostUpdateRequest);
	}
	
	@DeleteMapping("/{postId}")
	public void deleteOnePost(@PathVariable Long postId) {
		postService.deleteOnePost(postId);
	}
	
	
	
}

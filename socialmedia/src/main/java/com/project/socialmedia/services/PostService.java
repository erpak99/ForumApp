package com.project.socialmedia.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.socialmedia.core.results.DataResult;
import com.project.socialmedia.core.results.SuccessDataResult;
import com.project.socialmedia.entities.Post;
import com.project.socialmedia.entities.User;
import com.project.socialmedia.repositories.PostRepository;
import com.project.socialmedia.requests.PostCreateRequest;
import com.project.socialmedia.requests.PostUpdateRequest;
import com.project.socialmedia.responses.LikeResponse;
import com.project.socialmedia.responses.PostResponse;

@Service
public class PostService {

	private PostRepository postRepository;
	private LikeService likeService;
	private UserService userService;

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	@Autowired
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;
		if(userId.isPresent()) {
			 list = postRepository.findByUser_Id(userId.get());
		}else
			list = postRepository.findAll();
		return list.stream().map(p -> {
			List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
			return new PostResponse(p, likes);
			}
				).collect(Collectors.toList());
	}	

	public Post getPostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(PostCreateRequest newPostCreateRequest) {
		User user = userService.getOneUserById(newPostCreateRequest.getUserId());
		if(user==null)
			return null;
		
		Post toSave = new Post();
		toSave.setId(newPostCreateRequest.getId());
		toSave.setText(newPostCreateRequest.getText());
		toSave.setTitle(newPostCreateRequest.getTitle());
		toSave.setUser(user);
				
		return postRepository.save(toSave);
	}

	public DataResult<Post> updateOnePost(Long postId, PostUpdateRequest newPostUpdateRequest) {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isPresent()) {
			Post foundPost = post.get();
			foundPost.setText(newPostUpdateRequest.getText());
			foundPost.setTitle(newPostUpdateRequest.getTitle());
			postRepository.save(foundPost);
			return new SuccessDataResult<Post>(foundPost, "Post is updated...");
		}
		return null;	
	}

	public void deleteOnePost(Long postId) {
		this.postRepository.deleteById(postId);		
	}
}
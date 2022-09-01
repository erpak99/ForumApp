package com.project.socialmedia.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.socialmedia.core.results.DataResult;
import com.project.socialmedia.core.results.SuccessDataResult;
import com.project.socialmedia.entities.Post;
import com.project.socialmedia.entities.User;
import com.project.socialmedia.repositories.PostRepository;
import com.project.socialmedia.requests.PostCreateRequest;
import com.project.socialmedia.requests.PostUpdateRequest;
import com.project.socialmedia.responses.PostResponse;

@Service
public class PostService {

	private PostRepository postRepository;
	private UserService userService;

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	public List<PostResponse> getAllPosts() {
		List<Post> list;
		list = postRepository.findAll();
		return list.stream().map(p-> new PostResponse(p)).collect(Collectors.toList());
	}

	public DataResult<List<Post>> getPostsByUserId(Long id) {
		return new SuccessDataResult<List<Post>>(this.postRepository.getByUser_Id(id),"All posts by specific user id...");
	}

	public Post getPostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(PostCreateRequest newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if(user==null)
			return null;
		
		Post toSave = new Post();
		toSave.setId(newPostRequest.getUserId());
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUser(user);
				
		return postRepository.save(toSave);
	}

	public DataResult<Post> updateOnePost(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isPresent()) {
			Post foundPost = post.get();
			foundPost.setText(updatePost.getText());
			foundPost.setTitle(updatePost.getTitle());
			postRepository.save(foundPost);
			return new SuccessDataResult<Post>(foundPost, "Post is updated...");
		}
		return null;
		
	}

	public void deleteOnePost(Long postId) {
		this.postRepository.deleteById(postId);		
	}

}
package com.project.socialmedia.requests;

public class CommentCreateRequest {

	Long id;
	
	String text;
	
	Long userId;
	
	Long postId;

	public CommentCreateRequest() {
		
	}
	
	public CommentCreateRequest(Long id, String text, Long userId, Long postId) {
		super();
		this.id = id;
		this.text = text;
		this.userId = userId;
		this.postId = postId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
	
}

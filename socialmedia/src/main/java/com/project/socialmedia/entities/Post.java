package com.project.socialmedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	Long id;
	
	@ManyToOne(fetch=FetchType.EAGER) //lazy ile post goruntulendiginde user'i gormek istemiyoruz, tersi eager
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE) // bir user silindiginde ona ait postlar da silinsin
	User user;	
	
	String title;
	
	@Lob
	@Column(columnDefinition = "text")
	String text;
				
	public Post() {
	
	}
	
	public Post(Long id, User user, String title, String text) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}

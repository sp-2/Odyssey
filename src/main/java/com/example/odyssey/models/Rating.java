package com.example.odyssey.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="rating")
public class Rating {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Max(5)
    @Min(1)
    private int value;
    
    @Column(length = 5000)
    private String comment_text;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User rated_by_user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="piano_id")
    private Piano piano;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Rating() {
    }
    
	public Rating(int value) {
		this.value = value;
	}
	
	public Rating(int value, String comment_text) {
		this.value = value;
		this.comment_text = comment_text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public User getRated_by_user() {
		return rated_by_user;
	}

	public void setRated_by_user(User rated_by_user) {
		this.rated_by_user = rated_by_user;
	}

	public Piano getPiano() {
		return piano;
	}

	public void setPiano(Piano piano) {
		this.piano = piano;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}

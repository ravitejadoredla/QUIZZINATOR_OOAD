package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="scoreboard")

public class Scoreboard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	
	private Long id;
	
	@Column(nullable = false,  length = 45)
	public String quizid;
	
	@Column(nullable = false,  length = 45)
	public String username;
	
	@Column(  length = 45)
	public int score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuizid() {
		return quizid;
	}

	public void setQuizid(String quizid) {
		this.quizid = quizid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	

}

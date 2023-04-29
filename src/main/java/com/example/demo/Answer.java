package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="response")

public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	
	private Long id;
	@Column(nullable = false,  length = 45)
	private String quizid;
	
	@Column(nullable=false,length=45)
	private String username;
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(nullable=false)
	private int score; 
	
	@Column (nullable=false)
	private boolean is_correct;
	
	@Column(nullable = false, length = 45)
	private String questionnum;
	
	@Column(nullable = false,length = 45)
	private String answer;
	
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

	public String getQuestionnum() {
		return questionnum;
	}

	public void setQuestionnum(String questionnum) {
		this.questionnum = questionnum;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
	public boolean isIs_correct() {
		return is_correct;
	}

	public void setIs_correct(boolean is_correct) {
		this.is_correct = is_correct;
	}
	public int getScore() {
	    return score;
	}

	public void setScore(int score) {
	    this.score = score;
	}

	

}

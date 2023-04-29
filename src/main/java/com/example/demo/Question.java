package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Quesdb")

public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	
	private Long id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	@Column(nullable = false, unique = true, length = 45)
	public String quizid;
	
	
	
	
	@Column(nullable=false, length=64)
	public String questionnum;
	
	@Column(nullable=false, length=64)
	public String quest;
	
	@Column(nullable=false, length=64)
	public String option1;
	
	@Column(nullable=false, length=64)
	public String option2;
	
	@Column(nullable=false, length=64)
	public String option3;
	
	@Column(nullable=false, length=64)
	public String option4;
	
	@Column(nullable=false, length=64)
	public String correctans;
	
		public String getQuestionnum() {
		return questionnum;
	}
	public void setQuestionnum(String questionnum) {
		this.questionnum = questionnum;
	}
	public String getquest() {
		return quest;
	}
	public void setquest(String quest) {
		this.quest = quest;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getCorrectans() {
		return correctans;
	}
	public void setCorrectans(String correctans) {
		this.correctans = correctans;
	}
	public String getquizid() {
		return quizid;
	}
	public void setquizid(String quizid) {
		this.quizid = quizid;
	}
	
	
	
	
	
	
}

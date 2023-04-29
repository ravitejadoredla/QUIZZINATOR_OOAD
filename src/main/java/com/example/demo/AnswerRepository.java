package com.example.demo;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	Answer findByQuizidAndQuestionnum(String quizid, int questionnum);
	Answer findByQuizidAndQuestionnum(String quizid, String questionnum);

    @Query("SELECT SUM(a.score) FROM Answer a WHERE a.quizid = ?1")
    Integer getScoreByQuizid(String quizid);
    
    java.util.List<Answer> findByQuizid(String quizid);
    
    

}


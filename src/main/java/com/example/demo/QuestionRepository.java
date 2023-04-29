package com.example.demo;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findByQuizidAndQuestionnum(String quizid, String questionnum);
	
	@Query("SELECT m FROM Question m WHERE m.quizid = ?1")
	java.util.List<Question> findAll(String quizid);
	
	@Modifying
	@Query("UPDATE Question q SET q.quest = ?1, q.option1 = ?2, q.option2 = ?3, q.option3 = ?4, q.option4 = ?5, q.correctans = ?6 WHERE q.quizid = ?7 AND q.questionnum = ?8")
	void updateQuestion(String quest,String option1,String option2,String option3,String option4,String correctans,String quizid,String questionnum);
	
	@Modifying
	@Query("DELETE FROM Question q WHERE q.quizid = ?1 AND q.questionnum = ?2")
	void deleteQuestion(String quizid, String questionnum);
	
	@Modifying
	@Query("DELETE FROM Question q WHERE q.quizid = ?1")
	void deleteQuiz(String quizid);
}


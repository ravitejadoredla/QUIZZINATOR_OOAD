package com.example.demo;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScoreboardRepository extends JpaRepository<Scoreboard, Long> {
	@Query("SELECT u FROM Scoreboard u WHERE u.quizid = ?1 ORDER BY score desc")
	java.util.List<Scoreboard> findAllbyQuizid(String quizid);
	
	@Query("SELECT ROUND(AVG(score),2) FROM Scoreboard where quizid = ?1")
	Double findAvg(String quizid);
	
	@Query("SELECT MAX(score) FROM Scoreboard where quizid = ?1")
	Integer findMax(String quizid);
	
	@Query("SELECT ROUND(AVG(score),2) FROM Scoreboard where quizid = ?1")
	Double findAvgs();
	
	

}
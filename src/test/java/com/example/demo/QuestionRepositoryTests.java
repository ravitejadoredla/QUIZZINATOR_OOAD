package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)


public class QuestionRepositoryTests {

	@Autowired
	private QuestionRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateQuestion() {
		Question ques = new Question();
		ques.setCorrectans("bha");
		
		ques.setOption1("ary");
		ques.setOption2("bha");
		ques.setOption3("aviji");
		ques.setOption4("athar");
		ques.setquizid("S1");
		ques.setQuestionnum("Q11");
		ques.setquest("who are");
		
		
		
		Question savedques = repo.save(ques);
		
		
		  Question existques = entityManager.find(Question.class, savedques.getId());
		 
		
		 assertThat(existques.getquest()).isEqualTo(ques.getquest());  
	}
	
	
	/*
	 * @Test public void testFindProfessorByPID() { String correctans = "avijit";
	 * 
	 * Question ques = repo.findByCorrectAns(correctans);
	 * assertThat(ques).isNotNull(); }
	 */
	  
@Test public void testFindquestiona() { String quizid = "i1"; 
	  
	  List<Question> ques = repo.findAll(quizid); 
	  
	  assertThat(ques).isNotNull(); }
	 
	
	
	
}
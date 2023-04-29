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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)


public class AnswerRepositoryTests {

	@Autowired
	private AnswerRepository repo;
	
	@Autowired
	private QuestionRepository quesrepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateAnswer() {
		Answer ans = new Answer();
		ans.setAnswer("bharath");
		ans.setQuestionnum("s1");
		ans.setQuizid("e1");
		
		
		
		
		
		Answer savedans = repo.save(ans);
		
		
		  Answer existans = entityManager.find(Answer.class, savedans.getId());
		 
		
		 assertThat(existans.getQuestionnum()).isEqualTo(ans.getQuestionnum());  
	}
	
	
	public String processAnswers(@RequestParam String quizid, @RequestParam String[] questionnum, @RequestParam String[] option, Model model) {
	    int score = 0;
	    Answer result;
	    
	    for (int i = 0; i < questionnum.length; i++) {
	        Question question = quesrepo.findByQuizidAndQuestionnum(quizid, questionnum[i]);
	        if (question != null && option[i] != null && option[i].equals(question.getCorrectans())) {
	            score++;
	        }
	        result = new Answer();
	        result.setQuizid(quizid);
	        result.setQuestionnum(questionnum[i]);
	        result.setAnswer(option[i]);
	        result.setScore(score);
	        repo.save(result);
	    }
	    model.addAttribute("score", score);
	    return "login";
	}
	public int getScoreByQuizid(String quizid) {
		quizid="i1";
        List<Answer> answers = repo.findByQuizid(quizid);
        int totalScore = 0;
        for (Answer answer : answers) {
            totalScore += answer.getScore();
            
        }
        System.out.println(totalScore);
        return totalScore;
        
    }

	
	
	/*
	 * @Test public void testFindProfessorByPID() { String correctans = "avijit";
	 * 
	 * Question ques = repo.findByCorrectAns(correctans);
	 * assertThat(ques).isNotNull(); }
	 */
	  
	/*
	 * @Test public void testFindquestiona() { String quizid = "i1";
	 * 
	 * List<Question> ques = repo.findAll(quizid);
	 * 
	 * assertThat(ques).isNotNull(); }
	 */
	 
	
	
	
}

package com.example.demo;

import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;



import java.util.List;
import javax.sql.DataSource;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class QuestionController {
	
	@Autowired
	private QuestionRepository repo;
	
	@Autowired
	private AnswerRepository ansrepo;
	
	@Autowired
	private ScoreboardRepository scorerepo;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/add")
	public String showquestionform(Model model) {
		model.addAttribute("question", new Question());
		
		 
		return "/addquestion";
	}
	
	@PostMapping("/process_ques")
	public String processQuestion(Question question) {
		repo.save(question);
		
		return "/index";
	}
	
	
	
	
	@GetMapping("/displayques")
	public String viewUsersList(Model model,@RequestParam("quizid")String quizid,@RequestParam("username")String username) {
		
		List<Question> listques = (List<Question>) repo.findAll(quizid);
		model.addAttribute("listques",listques);
		model.addAttribute("quizid",quizid);
		model.addAttribute("username",username);
		
		
		
		return "displayques";
	}
	
	
	
	
	/*
	 * @PostMapping("/mcqs") public String processAnswers(@RequestParam String
	 * quizid, @RequestParam String questionnum, @RequestParam String option, Model
	 * model) { Answer ans = new Answer(); ans.setQuizid(quizid);
	 * ans.setQuestionnum(questionnum); ans.setAnswer(option);
	 * 
	 * ansrepo.save(ans);
	 * 
	 * return "/login";
	 * 
	 * }
	 */	
	
	/*
	 * @PostMapping("/mcqs") public String processAnswers(@ModelAttribute
	 * List<Answer> answers, Model model) { // iterate through the list of answers
	 * and save them to the database for (Answer answer : answers) {
	 * ansrepo.save(answer); }
	 * 
	 * // calculate the score and pass it to the view int score =
	 * calculateScore(answers); model.addAttribute("score", score);
	 * 
	 * return "result"; }
	 * 
	 * private int calculateScore(List<Answer> answers) { int score = 0; // iterate
	 * through the list of answers and calculate the score for (Answer answer :
	 * answers) { Question question =
	 * repo.findByQuizidAndQuestionnum(answer.getQuizid(), answer.getQuestionnum());
	 * if (question != null && answer.getAnswer().equals(question.getCorrectans()))
	 * { score++; } } return score; }
	 */
	
	/*
	 * @PostMapping("/mcqs") public String processAnswers(@RequestParam String
	 * quizid, Model model,@RequestParam String questionnum,@RequestParam String
	 * option) { int score = 0; List<Question> listques = (List<Question>)
	 * repo.findAll(quizid); for (Question question : listques) { Answer answer =
	 * new Answer(); if (answer != null && option != null &&
	 * option.equals(question.getCorrectans())) { score++; } Answer result = new
	 * Answer(); result.setQuizid(quizid);
	 * result.setQuestionnum(question.getQuestionnum()); result.setAnswer(option);
	 * result.setScore(score); ansrepo.save(result); } model.addAttribute("score",
	 * score); return "login"; }
	 */
	
	@PostMapping("/mcqs")
	public String processAnswers(@RequestParam String[] quizid, @RequestParam String[] questionnum, @RequestParam String[] option, Model model,@RequestParam String[] username) {
	    int score = 0;
	    

	    
	    
	    Answer result;
	    for (int i = 0; i < questionnum.length; i++) {
	        Question question = repo.findByQuizidAndQuestionnum(quizid[i], questionnum[i]);
	        if (question != null && option[i] != null && option[i].equals(question.getCorrectans())) {
	            score=1;
	        }
	        else {
	        	score=0;
	        }
	        result = new Answer();
	        result.setQuizid(quizid[i]);
	        result.setQuestionnum(questionnum[i]);
	        result.setAnswer(option[i]);
	        result.setScore(score);
	        result.setUsername(username[i]);
	        ansrepo.save(result);
	        model.addAttribute(username[i]);
	    }
	    model.addAttribute("score", score);
	    
	    
	    
	    return "redirect:/home?firstname="+username[0];
	}
	
	
	
	public int getScoreByQuizid(String quizid) {
        List<Answer> answers = ansrepo.findByQuizid(quizid);
        int totalScore = 0;
        for (Answer answer : answers) {
            totalScore += answer.getScore();
            
        }
        return totalScore;
    }
	
	@GetMapping("/score")
	public String getScore(@RequestParam String quizid,Model model,@RequestParam String username) {
		
		String sql1 = "SELECT COUNT(*) FROM quesdb WHERE quizid = ?";
		int limit = jdbcTemplate.queryForObject(sql1, Integer.class, quizid);
		System.out.println("Count: " + limit);

        String sql = "SELECT sum(r.score) " +
        			 "FROM (SELECT * FROM response ORDER BY id DESC LIMIT ?) r " +
                     
                     "JOIN quesdb q ON q.quizid = r.quizid AND q.questionnum = r.questionnum " +
                     "WHERE q.quizid = ? AND r.username = ? AND q.correctans = r.answer";
        Integer scored = jdbcTemplate.queryForObject(sql, Integer.class, limit,quizid,username);
        model.addAttribute("quizid",quizid);
        if (scored != null) {
            model.addAttribute("score", scored.intValue());
        } else {
            model.addAttribute("score", 0);
        }
        
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setQuizid(quizid);
        scoreboard.setUsername(username);
        scoreboard.setScore(scored != null ? scored.intValue() : 0);
       
        
        scorerepo.save(scoreboard);
        
        List<Scoreboard> scoreboards = scorerepo.findAllbyQuizid(quizid);
		model.addAttribute("scoreboard",scoreboards);
		Double avgscore = scorerepo.findAvg(quizid);
		Integer maxscore = scorerepo.findMax(quizid);
		model.addAttribute("avgscore",avgscore);
		model.addAttribute("maxscore",maxscore);
        
        
        return "score";
    }
	
	
	@GetMapping("/scoreboard")
	public String getScoreboard(Model model,@RequestParam String quizid) {
		List<Scoreboard> scoreboard = scorerepo.findAllbyQuizid(quizid);
		model.addAttribute("scoreboard",scoreboard);
		Double avgscore = scorerepo.findAvg(quizid);
		Integer maxscore = scorerepo.findMax(quizid);
		model.addAttribute("avgscore",avgscore);
		model.addAttribute("maxscore",maxscore);
		return "scoreboard";
		
	}





	
	

}

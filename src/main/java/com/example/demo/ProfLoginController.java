package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class ProfLoginController {
	@Autowired
    private ProfRepository profRepository;
	
	@Autowired
	private QuestionRepository quesrepo;
	
	@Autowired
	private ScoreboardRepository scorerepo;
	
    
    @GetMapping("/loginprof")
    public String loginprof() {
        return "loginprof";
    }
    
    @GetMapping("/users")
    public ModelAndView login(@RequestParam String profid) {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("profid", profid);
        return mav;
    }
    
    
    
    
    @PostMapping("/loginprof")
    public String doLoginprof(@RequestParam String pid, @RequestParam String password, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
        Professor prof = profRepository.findByPID(pid);
        
        if (prof == null || !prof.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password.");
            return "loginprof";
        }

        // Successful login
        String profid = prof.getPID();
        model.addAttribute("profid", profid);
        
        // Successful login
        session.setAttribute("prof", prof);
        redirectAttributes.addAttribute("profid", prof.getPID());
        
        
        return "redirect:/users";
    }
    
    
    @GetMapping("/profscore")
    public String getProfScore(@RequestParam String quizid,Model model,@RequestParam String profid ) {
    	
    	List<Scoreboard> scoreboards = scorerepo.findAllbyQuizid(quizid);
		model.addAttribute("scoreboard",scoreboards);
		Double avgscore = scorerepo.findAvg(quizid);
		Integer maxscore = scorerepo.findMax(quizid);
		model.addAttribute("avgscore",avgscore);
		model.addAttribute("maxscore",maxscore);
		
		return "users";
    }
    
    @GetMapping("/adminscore")
    public String getadminScore(@RequestParam String quizid,Model model) {
    	
    	List<Scoreboard> scoreboards = scorerepo.findAllbyQuizid(quizid);
		model.addAttribute("scoreboard",scoreboards);
		Double avgscore = scorerepo.findAvg(quizid);
		Integer maxscore = scorerepo.findMax(quizid);
		model.addAttribute("avgscore",avgscore);
		model.addAttribute("maxscore",maxscore);
		
		return "admindashboard";
    }
    
    
    @GetMapping("/displayprofques")
    public String getProfQues(@RequestParam String quizid,Model model,@RequestParam String profid ) {
    	
    	List<Question> ques = quesrepo.findAll(quizid);
		model.addAttribute("profques",ques);
		
		
		return "users";
    }
    
    @GetMapping("/displayadminques")
    public String getAdminQues(@RequestParam String quizid,Model model) {
    	
    	List<Question> adques = quesrepo.findAll(quizid);
		model.addAttribute("adminques",adques);
		
		
		return "admindashboard";
    }
    
    
    
    
    @PostMapping("/deleteques")
    @Transactional
	public String deletequestionform(@RequestParam("quizid")String quizid, @RequestParam("questionnum")String questionnum,@RequestParam("profid")String profid) {
		
		quesrepo.deleteQuestion(quizid,questionnum);
		 
		return "redirect:/users?profid=" + profid;
	}
    
    @PostMapping("/deletequiz")
    @Transactional
	public String deletequizform(@RequestParam("quizid")String quizid) {
		
		quesrepo.deleteQuiz(quizid);
		 
		return "admindashboard";
	}
    
    
    
    
    
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.websocket.Session;

@Controller
public class LoginController {
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AdminRepository adminRepository;
    
	@Autowired
	private ProfRepository profrepo;
	
	@Autowired 
    @GetMapping("/login")
    public String login() {
        return "login";
    }
	
	@GetMapping("/admindashboard")
    public String admindashboard() {
        return "admindashboard";
    }
    
    
    @GetMapping("/loginadmin")
    public String loginadmin() {
        return "loginadmin";
    }

    
    @GetMapping("/home")
    public ModelAndView login(@RequestParam String firstname) {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("username", firstname);
        return mav;
    }
    
    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password, Model model,HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(email);
        
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
        
        String firstname = user.getFirstname();
        model.addAttribute("username", firstname);
        
        // Successful login
        session.setAttribute("user", user);
        redirectAttributes.addAttribute("firstname", user.getFirstname());
        
        return "redirect:/home";
    }
    
    @PostMapping("/loginadmin")
    public String doLoginadmin(@RequestParam String adminid, @RequestParam String password, Model model,HttpSession session, RedirectAttributes redirectAttributes) {
        Admin admin = adminRepository.findByADMINID(adminid);
        
        if (admin == null || !admin.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password.");
            return "loginadmin";
        }
        
        String adid = admin.getAdminid();
        model.addAttribute("admin", adid);
        
        // Successful login
        session.setAttribute("admin", admin);
        
        
        return "admindashboard";
    }
    
    @PostMapping("/deletestudent")
    @Transactional
	public String deletestudent(@RequestParam("firstname")String firstname) {
		
		userRepository.deleteStudent(firstname);
		 
		return "admindashboard";
	}
    
    @PostMapping("/deleteprofessor")
    @Transactional
	public String deleteprofessor(@RequestParam("pid")String pid) {
		
		profrepo.deleteProfessor(pid);
		 
		return "admindashboard";
	}
    
    @GetMapping("/createprofessor")
    public String showCreateProfessorForm(Model model) {
        Professor professor = new Professor();
        model.addAttribute("professor", professor);
        return "createprofessor";
    }
    
    @PostMapping("/process_professor_account")
	public String processRegistration(Professor professor) {
		profrepo.save(professor);
		
		return "admindashboard";
	}
    
    
    
}

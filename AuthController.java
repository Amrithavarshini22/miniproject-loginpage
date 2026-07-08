package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    // =============================================
    // SHOW LOGIN PAGE
    // =============================================
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    // =============================================
    // PROCESS LOGIN
    // =============================================
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        
        boolean isValid = userService.loginUser(username, password);
        
        if (isValid) {
            session.setAttribute("username", username);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
    }
    
    // =============================================
    // SHOW REGISTER PAGE
    // =============================================
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    
    // =============================================
    // PROCESS REGISTRATION
    // =============================================
    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam String email,
                                  Model model) {
        
        User user = new User(username, password, email);
        String result = userService.registerUser(user);
        
        if (result.equals("Registration successful!")) {
            model.addAttribute("success", "Registration successful! Please login.");
            return "login";
        } else {
            model.addAttribute("error", result);
            return "register";
        }
    }
    
    // =============================================
    // SHOW DASHBOARD
    // =============================================
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("username", username);
        return "dashboard";
    }
    
    // =============================================
    // LOGOUT
    // =============================================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

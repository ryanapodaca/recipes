package com.recipes.demo.controllers;


import com.recipes.demo.models.SiteUser;
import com.recipes.demo.repos.SiteUserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {
    @Autowired
    SiteUserRepo siteUserRepo;

    @GetMapping("/")
    public String getHome() {
        return "login.html";
    }

    //Post for sign up
    @PostMapping("/signup")
    public RedirectView signUp(String userName, String password) {
        //now we call hash password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        //Now make new user
        SiteUser newUser = new SiteUser(userName, hashedPassword);
        //save to db
        siteUserRepo.save(newUser);

        return new RedirectView("/");
    }

    @PostMapping("/login")
    public RedirectView login(HttpServletRequest request, String userName, String password, Model m){
        // find qua username
        SiteUser siteUser = siteUserRepo.getSiteUserByUserName(userName);

        //check to see if user exists
        if (siteUser != null) {
            //then check password
            if(BCrypt.checkpw(password, siteUser.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("userName", userName);

                return new RedirectView("/recipe");
            }
            m.addAttribute("message", "Bad password");
            return new RedirectView("/");
        }
        m.addAttribute("message", "No user");
        return new RedirectView("/");
    }
}

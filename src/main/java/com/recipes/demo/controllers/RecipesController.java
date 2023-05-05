package com.recipes.demo.controllers;

import com.recipes.demo.models.SiteUser;
import com.recipes.demo.repos.SiteUserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipesController {
    @Autowired
    SiteUserRepo siteUserRepo;

    @GetMapping("/recipe")
    public String getRecipe(HttpServletRequest request, Model m) {
        HttpSession session = request.getSession();

        String userName = session.getAttribute("userName").toString();

        //authenticate
        if(userName != null) {
            m.addAttribute("userName", userName);
            SiteUser siteUser = siteUserRepo.getSiteUserByUserName(userName);
            m.addAttribute("siteUser", siteUser);
            return "recipes.html";
        }
        return "login.html";
    }


}

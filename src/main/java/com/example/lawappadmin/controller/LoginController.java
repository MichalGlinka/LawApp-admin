package com.example.lawappadmin.controller;

import com.example.lawappadmin.model.User;
import com.example.lawappadmin.service.Communication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class LoginController {

    public static final String USER = "username=";
    public static final String PASS = "password=";
    public static final String ENAB = "enabled=";
    public static final String ADMI = "admin=";
    Communication communication;

    public LoginController(Communication communication) {
        this.communication = communication;
    }

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model){
        boolean authorize = communication.authorize(username,password,model);
        if (authorize){
            return "menu.html";
        }else{
            return "index.html";
        }
    }

    @GetMapping("/choice")
    public String edit(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) boolean changePass,
                       @RequestParam(required = false) boolean enabled, @RequestParam(required = false) boolean admin, @RequestParam String action,
                       Model model){
        if (action.equals("delete")){
            return "sure.html";
        }else{
            String enab = "false";
            String adm = "false";
            if (enabled){
                enab = "true";
            }
            if (admin){
                adm = "true";
            }

            User user = new User(communication.sendRequest("read",new String[]{USER + username}),false);
            String pass = user.getPassword();
            if (changePass){
                pass = password;
            }
            communication.sendRequest("update",new String[]{USER + username,PASS + pass,
                    ENAB + enab,ADMI + adm});
        }
        ArrayList<User> users = communication.getUserList(communication.sendRequest("readAll",null));
        model.addAttribute("users",users);
        return "menu.html";
    }

    @GetMapping("/add")
    public String add(@RequestParam String username,@RequestParam String password,@RequestParam(required = false) boolean enabled,
                      @RequestParam(required = false) boolean admin, Model model){
        String enab = "0";
        String adm = "0";
        if (enabled){
            enab = "1";
        }
        if (admin){
            adm = "1";
        }
        communication.sendRequest("add",new String[]{USER + username,PASS + password,
                ENAB + enab,ADMI + adm});
        model.addAttribute("message","User added");
        return "response.html";
    }

    @GetMapping("/addUser")
    public String addUser(){
        return "addUser.html";
    }

    @GetMapping("/ye")
    public String areYouSure(@RequestParam String username, @RequestParam String password,@RequestParam String action,Model model){
        if (communication.authorize(username,password,model)){
            communication.sendRequest("delete",new String[]{USER + username});
            model.addAttribute("message","User removed");
            return "response.html";
        }else {
            return "menu.html";
        }
    }
}
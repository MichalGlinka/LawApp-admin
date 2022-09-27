package com.example.lawappadmin.controller;

import com.example.lawappadmin.model.User;
import com.example.lawappadmin.service.Communication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ChoiceController {
    Communication communication;

    public ChoiceController(Communication communication) {
        this.communication = communication;
    }

    /**
    @GetMapping("/choice")
    public String edit(@RequestParam String username, @RequestParam String password,
                       @RequestParam boolean enabled, @RequestParam boolean admin, @RequestParam String action,
                       Model model){
        if (action.equals("delete")){
            communication.sendRequest("delete",new String[]{username});
        }else{
            String enab = "false";
            String adm = "false";
            if (enabled){
                enab = "true";
            }
            if (admin){
                adm = "true";
            }
            communication.sendRequest("update",new String[]{username,password,enab,adm});
        }
        ArrayList<User> users = communication.getUserList(communication.sendRequest("readAll",null));
        model.addAttribute("users",users);
        return "menu.html";
    }

    @GetMapping("/add")
    public String add(@RequestParam String username,@RequestParam String password,@RequestParam boolean enabled,
                      @RequestParam boolean admin, Model model){
        String enab = "false";
        String adm = "false";
        if (enabled){
            enab = "true";
        }
        if (admin){
            adm = "true";
        }
        communication.sendRequest("add",new String[]{username,password,enab,adm});
        model.addAttribute("message","User added");
        return "response.html";
    }

    @GetMapping("/add")
    public String addRedir(){
        return "addUser.html";
    }
    **/
}
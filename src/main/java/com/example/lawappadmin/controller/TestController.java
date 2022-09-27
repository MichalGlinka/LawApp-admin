package com.example.lawappadmin.controller;

import com.example.lawappadmin.model.User;
import com.example.lawappadmin.service.Communication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class TestController {
    Communication communication;

    public TestController(Communication communication) {
        this.communication = communication;
    }

    @GetMapping("/test")
    public String test(){
        String s = communication.sendRequest("readAll",null);
        ArrayList<User> users = communication.getUserList(s);
        int i = 0;
        return "XD";
    }
}

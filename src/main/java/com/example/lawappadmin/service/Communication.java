package com.example.lawappadmin.service;

import com.example.lawappadmin.controller.LoginController;
import com.example.lawappadmin.model.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


@Service
public class Communication {
    IO io;

    public Communication(IO io) {
        this.io = io;
    }



    public String sendRequest(String resource, String [] params) {
        String urlString = "http://dysart.pl:8080/" + resource;
        if (params != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(urlString);
            builder.append("?");
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i]);
                if (i != (params.length - 1)){
                    builder.append("&");
                }
            }
            urlString = builder.toString();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).join();
    }

    public boolean getAutentication(String source){
        return source.split(":")[1].split("}")[0].equals("true");
    }

    public ArrayList<User> getUserList(String source){
        ArrayList<User> users = new ArrayList<>();
        String [] contents = source.split("\\{");
        for (int i = 1; i < contents.length; i++) {
            users.add(new User(contents[i],true));
        }
        return users;
    }

    public boolean authorize(String username, String password, Model model){
        String [] params = {String.format(LoginController.USER + "%s",username), String.format(LoginController.PASS + "%s",password)};
        String response = sendRequest("auth",params);
        boolean authenticated = getAutentication(response);
        String resp = sendRequest("read",new String[]{params[0]});
        User user = new User(resp,false);
        if (authenticated && user.isAdmin()){
            ArrayList<User> users = getUserList(sendRequest("readAll",null));
            model.addAttribute("users",users);
            return true;
        }else{
            return false;
        }
    }
}

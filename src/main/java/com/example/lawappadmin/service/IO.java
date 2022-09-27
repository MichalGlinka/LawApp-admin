package com.example.lawappadmin.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class IO {
    private final static String PATH = "domain_config.txt";

    public String readConfig(){
        File file = new File(PATH);

        try {
            Scanner scanner = new Scanner(file);
            return scanner.nextLine();
        }catch (FileNotFoundException e){
            return null; //na razie
        }
    }
}

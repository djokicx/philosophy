package com.example.philosophy;

import java.util.ArrayList;
import java.util.List;


public class DataBase {
    
    private final static List<Path> db = new ArrayList<Path>();
    
    private DataBase() { // private constructor -> Singleton
    }
    
    public static List<Path> getInstance() {
        return db;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Path p : db) {
            builder.append(p.toString());
            builder.append("\n");
        }
        builder.deleteCharAt(builder.length() - 1); // delete last new line character 
        
        return builder.toString();
    }
    
}
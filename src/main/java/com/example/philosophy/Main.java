package com.example.philosophy;
import org.apache.catalina.startup.Tomcat;

public class Main {
    
    public static void main(String[] args) throws Exception {
        String contextPath = "/" ;
        String appBase = ".";
        
        Tomcat tomcat = new Tomcat(); // new instance of the Tomcat embedded server   
        tomcat.setPort(Integer.valueOf("8080"));
        tomcat.setHostname("localhost");
        
        tomcat.getHost().setAppBase(appBase); // pathname of a directory containing web application
        tomcat.addWebapp(contextPath, appBase);
        tomcat.start();
        tomcat.getServer().await(); // wait till a proper shutdown command received
    }
}
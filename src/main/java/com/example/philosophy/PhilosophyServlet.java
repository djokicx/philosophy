package com.example.philosophy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "PhilosophyServlet",
        urlPatterns = {"/philosophy"}
)

public class PhilosophyServlet extends HttpServlet {
    
    PhilosophyService philosophyService = new PhilosophyService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("startArticle"); // get param
        
        if (action != null) {
            searchByArticle(req, resp);
        } else {
            List<String> result = new ArrayList<String>();
            forwardListEmployees(req, resp, result);
        }    
    }
    
    private void searchByArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startArticle = req.getParameter("startArticle");

        List<String> result = null;
        try {
            result = philosophyService.reachPhilosophyVia(startArticle);
        } catch (Exception e) {
            Logger.getLogger(PhilosophyServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        forwardListEmployees(req, resp, result);
    }
    
    private void forwardListEmployees(HttpServletRequest req, HttpServletResponse resp, List pathList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-philosophy.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("pathList", pathList);
        dispatcher.forward(req, resp);
    }
    
}

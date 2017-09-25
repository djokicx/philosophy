package com.example.philosophy;

import java.net.URI;
import java.util.Hashtable;
import java.util.List;


public class PhilosophyService {
    
    
    List<Path> db = DataBase.getInstance();
    Hashtable<String, Integer> cache = Cache.getInstance();
    
    public List<String> reachPhilosophyVia(String url) throws Exception {

        /* Check if provided link is valid at all */
        if (URLParser.isWiki(new URI(url)) == "") {
            throw new Exception("The url " + url + " is not valid");
        }
        
        String title = URLParser.articleTitle(url);

        /* First check the cache */
        if (cache.containsKey(title))
        {
            return db.get(cache.get(title)).getList();
        }
        
        boolean flag = false;
        Path path = new Path();
        
        while (!flag) {
            path.add(title); // add the link title to the path
            
            if (path.length() > 50) {
                throw new Exception("Too many articles checked...aborting.");
            }
            
            if (Path.isPhilosophy(title)) { // check before in case it starts at Philosophy
                
                /* Update the Database */
                db.add(path);
                
                /* Update the cache. KEY: Name of the first article VALUE: Index in the DB*/
                cache.put(path.getFirst(), path.getID());
                return path.getList();
            }
            
            url = URLParser.fetchHTML(url);
            title = URLParser.articleTitle(url); // new title
            
            if (path.isLoop(title)) {
                return path.getList(); // check if it's loop before adding it to the path  
            }
        }
        
        throw new Exception("Oops...something unexpectedly went wrong."); // default
    }
    
    public String getPath(int id) {
        return db.get(id).toString();
    }
}

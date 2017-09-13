public class ToPhilosophy {
    DataBase db;
    
    public ToPhilosophy() {
        db = new DataBase();
    }
    
    public boolean reachPhilosophyVia(String url) {
        boolean flag = false;
        
        Path path = new Path();
        while (!flag) {
            path.add(URLParser.articleTitle(url)); // add the link title to the path
            
            if (path.length() > 50) {
                System.out.println("Way too many attempts, aborting...");
                return false;
            }
            
            if (Path.isPhilosophy(URLParser.articleTitle(url))) { // check before in case it starts at Philosophy
//                System.out.println("Philosophy found!");
                System.out.println(path.toString());
                db.addPath(path);
                return true;
            }
            
            url = URLParser.fetchHTML(url);
            if (path.isLoop(URLParser.articleTitle(url))) {
                System.out.println(path.toString());
                System.out.println("Loop detected");
                return false; // check if it's loop before adding it to the path  
            }
        }
        
        return false; // default
    }
    
    public String getPath(int id) {
        return db.getPath(id).toString();
    }
}

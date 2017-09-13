import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class ToPhilosophy {
    public static void main(String[] args) throws URISyntaxException {
        DataBase db = new DataBase();
        String url = "https://en.wikipedia.org/wiki/Austria_national_football_team";
        boolean flag = false;
        
        Path path = new Path();
        while(!flag) {
            path.add(URLParser.articleTitle(url));
            if (Path.isPhilosophy(URLParser.articleTitle(url))) { // check before in case it starts at Philosophy
                System.out.println("Philosophy found!");
                System.out.println(path.toString());
                break;
            }
            
            url = URLParser.fetchHTML(url);
            if (path.isLoop(URLParser.articleTitle(url))) {
                System.out.println(path.toString());
                System.err.println("Loop detected");
                break; // check if it's loop before adding it to the path  
            }
        }
        
        
        
        
        
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        ToPhilosophy api = new ToPhilosophy();
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            
            while((line = br.readLine()) != null) {
                api.reachPhilosophyVia(line);
            }
            
//            api.getPath(2);
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
    }
}

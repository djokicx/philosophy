import java.util.ArrayList;


public class DataBase {
    
    private final ArrayList<Path> db;
    
    public DataBase() {
        db = new ArrayList<Path>();
    }
    
    public void addPath(Path p) {
        db.add(p);
    }
    
    public Path getPath(int i) {
        return db.get(i);
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

import java.util.LinkedList;

public class Path {
    // since single-threaded simple increment will work for Unique IDs
    private static int idCounter = 0; // 2^31 ids
    private static final String phil = "Philosophy";
    private int ID;
    private LinkedList<String> list;
    
    public Path() {
        ID = createId();
        
        list = new LinkedList<>();
    }
    
    public void add(String article) {
        list.add(article);
    }
    
    public int length() {
        return list.size();
    }
    
    public int getID() {
        return ID;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("PATH ID: " + ID + "\n");
        builder.append("Number of articles viewed (including the initial one): " + list.size() + "\n");
        
        for (String elem : list) {
            builder.append(elem);
            builder.append("\n");
        }
        builder.deleteCharAt(builder.length() - 1); // delete last new line character 
        return builder.toString();
    }
    
    private static int createId() {
        return idCounter++;
    }

    public boolean isLoop(String a) {
        for (String article : list) {
            if (article.equals(a)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean isPhilosophy(String a) {
        return a.equals(phil);
    }
}




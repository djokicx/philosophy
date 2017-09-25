package com.example.philosophy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser {
    
    private static final String HOST = "https://en.wikipedia.org/";  
    private static final Pattern REGEX = Pattern.compile("(\\([^)]*\\))|href=\"/(.*?)\" ", Pattern.CASE_INSENSITIVE|Pattern.DOTALL); 
    
    public static String fetchHTML(String path) throws Exception {
        
        try {
            URL first = new URL(path);            
            URLConnection conn = first.openConnection(); // connection
            
            if (!validContentTypeHeader(conn.getContentType())) { // if valid, start reading
                throw new Exception("Invalid content type");
            }
            
            else {
                BufferedReader in = new BufferedReader(
                new InputStreamReader(first.openStream()));
                
                String inputLine;
                  
                while ((inputLine = in.readLine()) != null)
                           if (inputLine.contains("<p>")) { // first paragraph in the html
                               Matcher matcher = REGEX.matcher(inputLine);
                               while (matcher.find()) {
                                   if (matcher.group(2) != null){
                                       URI u = new URI(matcher.group(2));
                                       String result = isWiki(u);
                                       
                                       if (result != "" && !articleTitle(result).equals(articleTitle(path))) { // valid Wiki link and not a link to the current page
                                         in.close();
                                         return result;
                                       }
                                   }
                               }
                           }
                in.close();
            }
            // TODO
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + path + " " + e);
        } catch (IOException e) {
            System.err.println("IOException: " + path + " " + e);
        } catch (URISyntaxException e) {
            System.err.println("URISyntaxException: " + e);
        }
    
        return "";  
    }
    
    private static boolean validContentTypeHeader(String headerValue) {
        String contentType;
        String charSet;
        
        if (headerValue == null) {
            System.err.println("Invalid ContentType");
            return false;
        }
        
        int n = headerValue.indexOf(";");
        if (n != -1) { //basically check again
            contentType = headerValue.substring(0, n);
            charSet = headerValue.substring(headerValue.indexOf('=') + 1, headerValue.length());

            if (contentType.equals("text/html") && charSet != null && Charset.isSupported(charSet)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static String isWiki(URI uri) {
        /* For absolute links */
        if (uri.isAbsolute()) {
            if (uri.getHost().contains("wikipedia")) {
                return uri.toString();
            }
        }
        /* For relative links */
        else {
            if (uri.toString().startsWith("wiki")) {
                return HOST.concat(uri.toString());
            }
        }
        return "";
    }
    
    public static String articleTitle(String a) {
        return a.substring(a.lastIndexOf('/') + 1, a.length());
    }
}

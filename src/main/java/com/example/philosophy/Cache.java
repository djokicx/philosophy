package com.example.philosophy;

import java.util.Hashtable;

/*
 * Simple caching without capacity. Good enough for now as it'll be small enough.
 */

public class Cache {
    private static final Hashtable<String, Integer> cache = new Hashtable<String, Integer>(); // Hashtable instead of HashMap for purposes of potential multithreading
    
    private Cache() {
    }
    
    public static Hashtable<String, Integer> getInstance() {
        return cache;
    }
}
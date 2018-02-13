package com.example.wang.fragmentwebview.dummy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wang on 2/11/18.
 */

public class Data {
    public static final String[] keys = {
            "Dog",
            "Cat",
            "Tiger",
            "Deer"
    };

    public static final String[] dogs = {
            "German Shepherd",
            "Labrador Retriever",
            "Golden Retriever"
    };
    public static final String[] cats = {
            "British Shorthair",
            "Siamese cat",
            "Ragdoll"
    };
    public static final String[] tigers = {
            "Sumatran Tiger",
            "Siberian tiger",
            "Javan tiger"
    };
    public static final String[] deers = {
            "Red deer",
            "Sika deer",
            "Roe deer"
    };

    public static final Map<String, String[]> items = new HashMap<String, String[]>();
    static {
        items.put("Dog", dogs);
        items.put("Cat", cats);
        items.put("Tiger", tigers);
        items.put("Deer", deers);
    }
}

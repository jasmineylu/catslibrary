package app.catslibrary.utility.datastorage;

import java.util.HashMap;
import java.util.HashSet;

public class DB {
    private static HashMap<Integer, String> cats = new HashMap<>();
    private static HashMap<Integer, String> favourites = new HashMap<>();
    private static HashSet<String> currentFavourites = new HashSet<>();

    public static HashMap<Integer, String> getCats() {
        return cats;
    }

    public static void setCats(HashMap<Integer, String> cats) {
        DB.cats = cats;
    }

    public static HashMap<Integer, String> getFavourites() {
        return favourites;
    }

    public static void setFavourites(HashMap<Integer, String> favourites) {
        DB.favourites = favourites;
    }

    public static HashSet<String> getCurrentFavourites() {
        return currentFavourites;
    }

    public static void setCurrentFavourites(HashSet<String> set) {
        DB.currentFavourites = set;
    }
}

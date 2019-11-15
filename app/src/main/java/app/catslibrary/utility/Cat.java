package app.catslibrary.utility;

import java.io.Serializable;
import java.util.HashMap;

public class Cat implements Serializable {
    private String id;
    private String name;
    private String description;
    private HashMap<String, String> weight;
    private String temperament;
    private String origin;
    private String life_span;
    private String wikipedia_url;
    private String dog_friendly;
    public static String url;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(HashMap<String, String> weight) {
        this.weight = weight;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }

    public void setDog_friendly(String dog_friendly) {
        this.dog_friendly = dog_friendly;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Cat.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getWeight() {
        return weight;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLifeSpan() {
        return life_span;
    }

    public String getWikiUrl() {
        return wikipedia_url;
    }

    public String getDogFriendliness() {
        return dog_friendly;
    }
}

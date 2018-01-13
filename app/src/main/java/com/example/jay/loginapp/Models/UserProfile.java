package com.example.jay.loginapp.Models;

/**
 * Created by jay on 23/12/17.
 */

public class UserProfile {


    private String Name;
    private String Location;
    private String Instruments;
    private String Styles;
    private String Bio;
    private String Links;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getInstruments() {
        return Instruments;
    }

    public void setInstruments(String instruments) {
        Instruments = instruments;
    }

    public String getStyles() {
        return Styles;
    }

    public void setStyles(String styles) {
        Styles = styles;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getLinks() {
        return Links;
    }

    public void setLinks(String links) {
        Links = links;
    }

    // constructors
    public UserProfile(String name ,String loc, String instrument,
                       String style, String bio, String links ) {
        Name = name;
        Location = loc;
        Instruments = instrument;
        Styles = style;
        Bio = bio;
        Links = links;
    }

    public UserProfile(String name ,String loc, String instrument, String style, String bio)
    {
        Name = name;
        Location = loc;
        Instruments = instrument;
        Styles = style;
        Bio = bio;

    }
    public UserProfile(){}




}

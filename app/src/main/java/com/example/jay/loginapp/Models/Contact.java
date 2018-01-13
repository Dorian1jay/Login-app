package com.example.jay.loginapp.Models;

/**
 * Created by jay on 27/12/17.
 */

public class Contact {
    private String UID;
    private String name;
    private String location;
    private String instrument;

    public Contact(String UID, String name, String location, String instrument) {
        this.UID = UID;
        this.name = name;
        this.location = location;
        this.instrument = instrument;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}

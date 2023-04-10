package org.bonn.ooka.buchungssystem.ss2022.models;

public class Hotel {
    private long id;
    private String name;
    private String location;
    private int Stars;
    private String contact;

    public Hotel(long id, String name, String location, int stars, String contact) {
        this.id = id;
        this.name = name;
        this.location = location;
        Stars = stars;
        this.contact = contact;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getStars() {
        return Stars;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name + ", location: " + this.location;
    }
}

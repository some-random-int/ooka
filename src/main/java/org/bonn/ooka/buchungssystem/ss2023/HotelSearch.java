package org.bonn.ooka.buchungssystem.ss2023;

import org.bonn.ooka.buchungssystem.ss2023.models.Hotel;

import java.util.List;

public interface HotelSearch {

    List<Hotel> getHotelByName(String name);
    List<Hotel> getHotelByNameAndContact(String name, String contact);
    void openSession();
    void closeSession();
    List<String> getCurrentInterfaces();
}

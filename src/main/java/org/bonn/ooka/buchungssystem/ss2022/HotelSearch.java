package org.bonn.ooka.buchungssystem.ss2022;

import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

import java.util.List;

public interface HotelSearch {

    List<Hotel> getHotelByName(String name);
    void openSession();
    void closeSession();
}

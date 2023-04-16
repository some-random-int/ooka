package org.bonn.ooka.buchungssystem_test.ss2023;

import org.bonn.ooka.buchungssystem.ss2023.Cache;
import org.bonn.ooka.buchungssystem.ss2023.DBCache;
import org.bonn.ooka.buchungssystem.ss2023.HotelRetrievalProxy;
import org.bonn.ooka.buchungssystem.ss2023.HotelSearch;
import org.bonn.ooka.buchungssystem.ss2023.models.Hotel;

import java.util.List;

public class Client {

    public static void main(String[] args) {
        // create new Proxy object with Cache
        DBCache<Hotel> cache = new Cache<>();
        HotelSearch hotelSearch = new HotelRetrievalProxy(cache);

        hotelSearch.openSession();
        String[] keyWords = new String[] {"Maritim", "", "Hotel", "Maritim"};
        for (String keyWord : keyWords) {
            List<Hotel> res = hotelSearch.getHotelByName(keyWord);
            res.forEach((Hotel hotel) -> System.out.println(" - " + keyWord + ": {" + hotel + "}"));
        }
        hotelSearch.closeSession();

        // test proxy without cache
        HotelSearch hotelSearchWithoutCache = new HotelRetrievalProxy();
        hotelSearchWithoutCache.openSession();
        List<Hotel> res = hotelSearchWithoutCache.getHotelByName("Maritim");
        res.forEach((Hotel hotel) -> System.out.println(" - Maritim: { + hotel + }"));
        hotelSearchWithoutCache.closeSession();
    }
}

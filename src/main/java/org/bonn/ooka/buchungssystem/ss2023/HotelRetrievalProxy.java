package org.bonn.ooka.buchungssystem.ss2023;

import org.bonn.ooka.buchungssystem.ss2023.models.Hotel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HotelRetrievalProxy implements HotelSearch {

    HotelRetrieval hotelRetrieval;

    public HotelRetrievalProxy(DBCache<Hotel> cache) {
        this.log("constructor", "CacheType: " + cache.getClass());
        this.hotelRetrieval = new HotelRetrieval(cache);
    }
    public HotelRetrievalProxy() {
        // hand an always empty cache, to prevent failure in HotelRetrieval
        this(new DBCache<Hotel>() { // anonymous class
            @Override
            public void cacheResult(String key, List<Hotel> value) { }

            @Override
            public boolean isCached(String key) {
                return false;
            }

            @Override
            public List<Hotel> getValue(String key) {
                return new ArrayList<>();
            }
        });
    }

    @Override
    public List<Hotel> getHotelByName(String name) {
        this.log("getHotelByName", " Keyword: " + name);
        return this.hotelRetrieval.getHotelByName(name);
    }

    @Override
    public List<Hotel> getHotelByNameAndContact(String name, String contact) {
        this.log("getHotelByNameAndContact", " Keywords: " + name + ", " + contact);
        return this.hotelRetrieval.getHotelByNameAndContact(name, contact);
    }

    @Override
    public void openSession() {
        this.log("openSession", "");
        this.hotelRetrieval.openSession();
    }

    @Override
    public void closeSession() {
        this.log("closeSession", "");
        this.hotelRetrieval.closeSession();
    }

    @Override
    public List<String> getCurrentInterfaces() {
        this.log("getCurrentInterfaces", "");
        return this.hotelRetrieval.getCurrentInterfaces();
    }

    private void log(String methodName, String description) {
        System.out.println(LocalDateTime.now() + ": Booking-system was accessed using method '" + methodName + "'. " +
                description);
    }
}

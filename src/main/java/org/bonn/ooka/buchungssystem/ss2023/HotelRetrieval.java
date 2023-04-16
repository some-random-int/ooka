package org.bonn.ooka.buchungssystem.ss2023;

import org.bonn.ooka.buchungssystem.ss2023.models.Hotel;

import java.util.Collections;
import java.util.List;

class HotelRetrieval implements HotelSearch {

    private DBAccess dbAccess;
    private DBCache<Hotel> cache;

    protected HotelRetrieval(DBCache<Hotel> cache) {
        this.cache = cache;
        this.dbAccess = new DBAccess();
    }

    public List<Hotel> getHotelByName(String name) {
        if (this.cache.isCached(name)) {
            return this.cache.getValue(name);
        } else {
            List<Hotel> result = this.dbAccess.getObjects(DBAccess.HOTEL, name);
            this.cache.cacheResult(name, result);
            return result;
        }
    }

    @Override
    public List<Hotel> getHotelByNameAndContact(String name, String contact) {
        if (this.cache.isCached(name+contact)) {
            return this.cache.getValue(name+contact);
        } else {
            List<Hotel> result = this.dbAccess.getHotelsByContact(DBAccess.HOTEL, name, contact);
            this.cache.cacheResult(name, result);
            return result;
        }
    }

    public void openSession() {
        this.dbAccess.openConnection();
    }

    public void closeSession() {
        this.dbAccess.closeConnection();
    }

    @Override
    public List<String> getCurrentInterfaces() {
        return Collections.singletonList("getHotelByName(), getHotelByNameAndContact");
    }
}

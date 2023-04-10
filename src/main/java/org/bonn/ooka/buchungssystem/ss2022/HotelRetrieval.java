package org.bonn.ooka.buchungssystem.ss2022;

import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

import java.util.List;

class HotelRetrieval {

    private DBAccess dbAccess;
    private DBCache<Hotel> cache;

    protected HotelRetrieval(DBCache<Hotel> cache) {
        this.cache = cache;
        this.dbAccess = new DBAccess();
    }

    protected List<Hotel> getHotelByName(String name) {
        if (this.cache.isCached(name)) {
            return this.cache.getValue(name);
        } else {
            List<Hotel> result = this.dbAccess.getObjects(DBAccess.HOTEL, name);
            this.cache.cacheResult(name, result);
            return result;
        }
    }

    protected void openSession() {
        this.dbAccess.openConnection();
    }

    protected void closeSession() {
        this.dbAccess.closeConnection();
    }
}

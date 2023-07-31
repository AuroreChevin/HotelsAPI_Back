package fr.fms.hotels.service;

import fr.fms.hotels.entities.City;
import fr.fms.hotels.entities.Hotel;

public interface HotelService {
    public City saveCity(City city);

    public Hotel saveHotel(Hotel hotel);
}

package fr.fms.hotels.service;

import fr.fms.hotels.entities.City;
import fr.fms.hotels.entities.Hotel;

import java.util.List;

public interface HotelService {
    City saveCity(City city);

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    List<City> getAllCities();
}

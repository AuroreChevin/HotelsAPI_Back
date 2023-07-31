package fr.fms.hotels.service;

import fr.fms.hotels.dao.CityRepository;
import fr.fms.hotels.dao.HotelRepository;
import fr.fms.hotels.entities.City;
import fr.fms.hotels.entities.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService{
    @Autowired
    CityRepository cityRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Override
    public City saveCity(City city) {
        log.info("Sauvegarde d'une nouvelle ville {} en base",city);
        return cityRepository.save(city);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        log.info("Sauvegarde d'un nouvel hotel {} en base",hotel);
        return hotelRepository.save(hotel);
    }

}
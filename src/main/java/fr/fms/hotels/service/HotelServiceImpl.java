package fr.fms.hotels.service;

import fr.fms.hotels.dao.CityRepository;
import fr.fms.hotels.dao.HotelRepository;
import fr.fms.hotels.entities.City;
import fr.fms.hotels.entities.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAllByOrderByHotelNameAsc();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAllByOrderByCityNameAsc();
    }

    @Override
    public List<Hotel> getHotelsByCityId(Long id) {
        return hotelRepository.findByCityId(id);
    }

    @Override
    public Optional<Hotel> readHotelById(Long id) {
        return hotelRepository.findById(id);
    }
    @Override
    public List<City> getCitiesByKeyword(String keyword) {
        return cityRepository.findByCityNameContains(keyword);
    }
    @Override
    public Optional<City> readCityById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<Hotel> getHotelByCityKeyword(String keyword) {
        return hotelRepository.findByCityCityNameContains(keyword);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }


}

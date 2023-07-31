package fr.fms.hotels;

import fr.fms.hotels.dao.CityRepository;
import fr.fms.hotels.dao.HotelRepository;
import fr.fms.hotels.entities.City;
import fr.fms.hotels.entities.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HotelJPATests {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Test
    void should_find_all_cities() {
        cityRepository.save(new City(null, "Soumoulou", "Ville avec nom drôle", null));
        Iterable<City> cities = cityRepository.findAll();
        assertThat(cities).isNotEmpty();
    }
    @Test
    void should_find_all_hotels() {
        hotelRepository.save(new Hotel(null, "CatBed", "12 rue du fauteuil", "0623025155", 2, 5, 60,null, null));
        Iterable<Hotel> cinemas = hotelRepository.findAll();
        assertThat(cinemas).isNotEmpty();
    }
}

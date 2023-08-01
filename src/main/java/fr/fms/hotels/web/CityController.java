package fr.fms.hotels.web;

import fr.fms.hotels.entities.City;
import fr.fms.hotels.service.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
public class CityController {
    @Autowired
    HotelServiceImpl hotelServiceImpl;
    /**
     * Méthode en GET permettant d'afficher la liste des villes
     * @return la liste des villes
     */
    @GetMapping("/cities")
    public List<City> allCities(){return hotelServiceImpl.getAllCities();}
    /**
     * méthode en GET permettant la recherche d'une ville par mot clé (chaine de caractère)
     * @param keyword la chaine de caractère à trouver
     * @return renvoie une liste de ville contenant la chaine de caractères recherchée.
     */
    @GetMapping("/cities/search/{keyword}")
    public List<City> searchByKeyword(@PathVariable String keyword) {
        try {
            return hotelServiceImpl.getCitiesByKeyword(keyword);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}

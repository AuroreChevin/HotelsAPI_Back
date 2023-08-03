package fr.fms.hotels.web;

import fr.fms.hotels.entities.City;
import fr.fms.hotels.exception.RecordNotFoundException;
import fr.fms.hotels.service.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

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
    public List<City> allCities(){
        try {
            return hotelServiceImpl.getAllCities();
         }catch (Exception e){
            log.error("problème lors du chargement de la liste", e.getCause());
        }
        return null;
    }
    /**
     * méthode en GET permettant la recherche d'une ville par mot clé (chaine de caractère)
     * @param keyword la chaine de caractère à trouver
     * @return renvoie une liste de ville contenant la chaine de caractères recherchée.
     */
    /*@GetMapping("/cities/{keyword}")
    public List<City> searchByKeyword(@PathVariable String keyword) {
        try {
            return hotelServiceImpl.getCitiesByKeyword(keyword);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }*/
    /**
     * Methode en GET permettant de récupérer une ville
     * @param id de la ville
     * @return la ville
     */
    @GetMapping("/cities/{id}")
    public City getCityById(@PathVariable("id")Long id) {
        return hotelServiceImpl.readCityById(id).orElseThrow(() -> new RecordNotFoundException("Id de la ville " +id+ " n'existe pas"));
    }
    /**
     * Méthode DELETE permettant la suppression d'une ville
     * @param id de la ville
     * @return response entity status ok
     */
    @DeleteMapping(value = "/cities/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("id") Long id) {
        try {
            hotelServiceImpl.deleteCity(id);
        }
        catch (Exception e) {
            log.error("Problème durant la suppression de la ville d'id : {}",id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        log.info("Suppression de la ville d'id : {}", id);
        return ResponseEntity.ok().build();
    }
    /**
     * Méthode en POST permettant de sauvegarder une nouvelle ville
     * @param c une ville
     * @return response entity creation ville
     */
    @PostMapping("/cities")
    public ResponseEntity<City> saveCity(@RequestBody City c){
        City city = hotelServiceImpl.saveCity(c);
        if(Objects.isNull(city)) {
            return ResponseEntity.noContent().build();
        }
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    /**
     * Méthode PUT permettant de mettre à jour une ville
     * @param c une ville
     * @return response entity status ok
     */
    @PutMapping("/cities")
    public ResponseEntity<City> updateCity(@RequestBody City c){
        City city = hotelServiceImpl.readCityById(c.getId()).get();
        city.setCityName(c.getCityName());
        city.setDescription(c.getDescription());
        if(Objects.isNull(hotelServiceImpl.saveCity(city))) {
            return ResponseEntity.noContent().build();
        }
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}

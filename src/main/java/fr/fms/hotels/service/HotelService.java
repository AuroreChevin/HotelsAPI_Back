package fr.fms.hotels.service;

import fr.fms.hotels.entities.City;
import fr.fms.hotels.entities.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    /**
     * Méthode permettant de sauvegarder une nouvelle ville
     * @param city
     */
    City saveCity(City city);

    /**
     * Méthode permettant de sauvegarder un nouvel hôtel
     * @param hotel
     * @return
     */
    Hotel saveHotel(Hotel hotel);
    /**
     *Méthode permettant de récupérer la liste des hôtels
     * @return liste des hôtels
     */
    List<Hotel> getAllHotels();
    /**
     * Méthode permettant de récupérer la liste des villes
     * @return liste des villes
     */
    List<City> getAllCities();

    /**
     * Méthode permettant de récupérer une liste d'hôtels par ville
     * @param id de la ville sélectionnée
     * @return une liste d'hôtels par ville choisie
     */
    List<Hotel> getHotelsByCityId(Long id);
    /**
     * Méthode permettant de récupérer un hôtel par son id
     * @param id de l'hôtel sélectionné
     * @return un hôtel
     */
    Optional<Hotel> readHotelById(Long id);
    /**
     * Méthode permettant de récupérer une liste de villes par mot clé
     * @param keyword
     * @return une liste de ville
     */
    List<City> getCitiesByKeyword(String keyword);
}

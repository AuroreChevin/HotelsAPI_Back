package fr.fms.hotels.dao;

import fr.fms.hotels.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    /**
     * méthode permettant de récupérer la liste des villes par ordre alphabétique
     * @return renvoie une liste de villes par ordre alphabétique
     */
    List<City> findAllByOrderByCityNameAsc();

    /**
     * Méthode premettant de récupérer la liste des villes contenant un mot clé
     * @param keyword
     * @return liste de villes contenant keyword
     */
    List<City> findByCityNameContains(String keyword);
}

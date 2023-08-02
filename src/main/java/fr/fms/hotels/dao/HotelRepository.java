package fr.fms.hotels.dao;
import fr.fms.hotels.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    /**
     * méthode permettant de récupérer la liste dees hôtels par ordre alpahbétique
     * @return renvoie une liste d'hôtels par ordre alphabétique
     */
    List<Hotel> findAllByOrderByHotelNameAsc();
    /**
     * méthode permettant de récupérer une liste d'hôtels par ville
     * @param id l'identifiant de la ville choisie
     * @return renvoie la liste d'hôtels
     */
    List<Hotel> findByCityId(Long id);

    /**
     * Méthode permettant de récupérer une liste d'hôtel par mot clé contenu dans le nom de la ville
     * @param keyword contenu dans le nom de la ville
     * @return liste d'hôtels dont le nom de la ville contient le keyword
     */
    List<Hotel> findByCityCityNameContains(String keyword);

}

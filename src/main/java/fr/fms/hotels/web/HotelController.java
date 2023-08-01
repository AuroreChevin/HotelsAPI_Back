package fr.fms.hotels.web;

import fr.fms.hotels.entities.Hotel;
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
public class HotelController {
    @Autowired
    HotelServiceImpl hotelServiceImpl;
    /**
     * Méthode en GET permettant d'afficher la liste des hôtels
     * @return la liste des hotels
     */
    @GetMapping("/hotels")
    public List<Hotel> allHotels(){return hotelServiceImpl.getAllHotels();}
    /**
     * méthode en GET permettant de sélectionner une ville pour la recheche d'hôtels
     * @param id l'identifiant de la ville choisie
     * @return renvoie la liste des hôtels de la ville choisie
     */
    @GetMapping("/hotels/cities/{id}")
    public List<Hotel> allHotelsByCityId(@PathVariable("id")Long id){
        return hotelServiceImpl.getHotelsByCityId(id);
    }

}

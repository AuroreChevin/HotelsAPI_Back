package fr.fms.hotels.web;

import fr.fms.hotels.entities.City;
import fr.fms.hotels.entities.Hotel;
import fr.fms.hotels.exception.RecordNotFoundException;
import fr.fms.hotels.service.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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

    /**
     * Méthode en GET permettant d'afficher la photo des hôtels par id
     * @param id de l'hôtel
     * @return response entity
     */
    @GetMapping(path = "/photo/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> getPhotos(@PathVariable("id") Long id) throws IOException {
        byte[] file =null;
        try {
            Hotel hotel = hotelServiceImpl.readHotelById(id).get();
            if (hotel.getPhoto()==null)hotel.setPhoto("unknown.png");
            file = Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/hotels/images/"+hotel.getPhoto()));
        }catch (Exception e){
            log.error("problème lors du download de l'image correspondant à l'hôtel d'id : {}", id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        return ResponseEntity.ok().body(file);
    }

    /**
     * Méthode permettant changer la photo d'un hôtel via son id
     * @param file photo à upload
     * @param id de l'hôtel
     * @return Response entity
     */
    @PostMapping(path="/photo/{id}")
    public ResponseEntity<?> uploadPhoto(MultipartFile file, @PathVariable("id")Long id) throws IOException{
        try {
            Hotel hotel = hotelServiceImpl.readHotelById(id).get();
            hotel.setPhoto(file.getOriginalFilename());
            Files.write(Paths.get(System.getProperty("user.home")+"/hotels/images/"+hotel.getPhoto()),file.getBytes());
            hotelServiceImpl.saveHotel(hotel);
        }catch (Exception e){
            log.error("problème lors de l'upload de l'image correspondant à l'hôtel d'id : {}", id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        log.info("file upload ok {}",id);
        return ResponseEntity.ok().build();
    }

    /**
     * Methode permettant de récupérer un hôtel
     * @param id de l'hôtel
     * @return l'hôtel
     */
    @GetMapping("/hotel/{id}")
    public Hotel getHotelById(@PathVariable("id")Long id) {
        return hotelServiceImpl.readHotelById(id).orElseThrow(() -> new RecordNotFoundException("Id de l'hôtel " +id+ " n'existe pas"));
    }

}

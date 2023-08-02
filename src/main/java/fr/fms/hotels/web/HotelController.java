package fr.fms.hotels.web;


import fr.fms.hotels.entities.Hotel;
import fr.fms.hotels.exception.RecordNotFoundException;
import fr.fms.hotels.service.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

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
     * Méthode  en POST permettant de changer la photo d'un hôtel via son id
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
     * Methode en GET permettant de récupérer un hôtel
     * @param id de l'hôtel
     * @return l'hôtel
     */
    @GetMapping("/hotels/{id}")
    public Hotel getHotelById(@PathVariable("id")Long id) {
        return hotelServiceImpl.readHotelById(id).orElseThrow(() -> new RecordNotFoundException("Id de l'hôtel " +id+ " n'existe pas"));
    }

    /**
     *  Methode en GET permettant de récupérer une liste d'hôtels contenant un mot clé pour la ville
     * @param keyword mot clé contenu dans nom de ville
     * @return uneliste d'hôtels
     */
    @GetMapping("/hotels/city/{keyword}")
    public List<Hotel> getHotelByCityByKeyword(@PathVariable String keyword) {
        try {
            return hotelServiceImpl.getHotelByCityKeyword(keyword);
        } catch (Exception e) {
            log.error("Aucun hotel ne correspond à une ville contenant ce mot clé : {}", keyword);
        }
        return null;
    }

    /**
     * Méthode en POST permettant de sauvegarder un nouvel hôtel
     * @param h un hôtel
     * @return response entity creation hotel
     */
    @PostMapping("/hotels")
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel h){
        Hotel hotel = hotelServiceImpl.saveHotel(h);
        if(Objects.isNull(hotel)) {
            return ResponseEntity.noContent().build();
        }
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hotel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Méthode DELETE permettant la suppression d'un hôtel
     * @param id de l'hôtel
     * @return response entity status ok
     */
    @DeleteMapping(value = "/hotels/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable("id") Long id) {
        try {
            hotelServiceImpl.deleteHotel(id);
        }
        catch (Exception e) {
            log.error("Problème durant la suppression de l'hôtel' d'id : {}",id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        log.info("Suppression de l'hôtel'd'id : {}", id);
        return ResponseEntity.ok().build();
    }

    /**
     * Méthode PUT permettant de mettre à jour un hotel
     * @param h hotel
     * @return response entity status ok
     */
    @PutMapping("/hotels")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel h){
        Hotel hotel = hotelServiceImpl.readHotelById(h.getId()).get();
        hotel.setHotelName(h.getHotelName());
        hotel.setAddress(h.getAddress());
        hotel.setPhone(h.getPhone());
        hotel.setNbRoom(h.getNbRoom());
        hotel.setNbStar(h.getNbStar());
        hotel.setPriceRoom(h.getPriceRoom());
        hotel.setCity(hotelServiceImpl.readCityById(h.getCity().getId()).get());
        if(Objects.isNull(hotelServiceImpl.saveHotel(hotel))) {
            return ResponseEntity.noContent().build();
        }
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hotel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}

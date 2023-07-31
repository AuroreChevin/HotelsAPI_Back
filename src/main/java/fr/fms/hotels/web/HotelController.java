package fr.fms.hotels.web;

import fr.fms.hotels.entities.Hotel;
import fr.fms.hotels.service.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
public class HotelController {
    @Autowired
    HotelServiceImpl hotelServiceImpl;
    @GetMapping("/hotels")
    public List<Hotel> allHotels(){return hotelServiceImpl.getAllHotels();}
}

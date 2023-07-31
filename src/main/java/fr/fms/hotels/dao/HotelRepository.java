package fr.fms.hotels.dao;

import fr.fms.hotels.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findAllByOrderByHotelNameAsc();
}

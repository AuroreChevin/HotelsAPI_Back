package fr.fms.hotels.dao;

import fr.fms.hotels.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}

package fr.fms.hotels.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "city_id")
    @Cascade(CascadeType.DELETE)
    private Collection<Hotel> hotels;
}

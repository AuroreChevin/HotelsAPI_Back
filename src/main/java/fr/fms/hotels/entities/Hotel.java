package fr.fms.hotels.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.fms.hotels.security.entities.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Hotel implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hotelName;
    private String address;
    private String phone;
    private int nbRoom;
    private int nbStar;
    private double priceRoom;
    private String photo;
    @ManyToOne
    @ToString.Exclude
    private City city;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppUser> users  = new ArrayList<>();
}

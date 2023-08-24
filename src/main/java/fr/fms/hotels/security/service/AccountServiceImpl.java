package fr.fms.hotels.security.service;

import fr.fms.hotels.dao.HotelRepository;
import fr.fms.hotels.entities.Hotel;
import fr.fms.hotels.security.dao.AppRoleRepository;
import fr.fms.hotels.security.dao.AppUserRepository;
import fr.fms.hotels.security.entities.AppRole;
import fr.fms.hotels.security.entities.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @Transactional
@Slf4j
public class AccountServiceImpl implements AccountService{
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    AppRoleRepository appRoleRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        log.info("Sauvegarde d'un nouvel utilisateur {} en base",user);
        return appUserRepository.save(user);
    }
    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppRole role = appRoleRepository.findByRolename(rolename);
        AppUser user = appUserRepository.findByUsername(username);
        user.getRoles().add(role);
        log.info("association d'un rôle à un utilisateur");
    }

    @Override
    public AppUser findUserByUsername(String username) {   return appUserRepository.findByUsername(username);   }

    @Override
    public ResponseEntity<List<AppUser>> listUsers() {  //ResponseEntity permet d'ajouter au corps des entetes et un état
        return ResponseEntity.ok().body(appUserRepository.findAll());
    }

    @Override
    public List<AppUser> getUsersByRolename(String roles) {
        return appUserRepository.findByRolesRolename(roles);
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public Optional<AppUser> readUserById(Long id) {
        return appUserRepository.findById(id);
    }

   /* @Override
    public void addHotelToManager(String username, String hotelName) {
        Hotel hotel = hotelRepository.findByHotelName(hotelName);
        AppUser user = appUserRepository.findByUsername(username);
        user.getHotels().add(hotel);
        log.info("association d'un hôtel à un utilisateur");
    }*/

    @Override
    public ResponseEntity<List<AppRole>> listRoles() {
        return ResponseEntity.ok().body(appRoleRepository.findAll());
    }


}

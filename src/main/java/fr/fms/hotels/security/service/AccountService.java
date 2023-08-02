package fr.fms.hotels.security.service;

import fr.fms.hotels.entities.Hotel;
import fr.fms.hotels.security.entities.AppRole;
import fr.fms.hotels.security.entities.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface AccountService {
    /**
     * Méthode permettant de sauvegarder un nouvel utilisateur
     * @param user
     */
    AppUser saveUser(AppUser user);
    /**
     * Méthode permettant de sauvegarder un nouveau role
     * @param role
     */
    AppRole saveRole(AppRole role);

    /**
     * Méthode permettant d'ajouter un role à un user
     * @param username
     * @param rolename
     */
     void addRoleToUser(String username, String rolename);

    /**
     * Méthode permettant de trouver un user par son nom
     * @param username
     * @return un user
     */
    public AppUser findUserByUsername(String username);

    ResponseEntity<List<AppUser>> listUsers();
    public List<AppUser> getUsersByRolename(String roles);

}

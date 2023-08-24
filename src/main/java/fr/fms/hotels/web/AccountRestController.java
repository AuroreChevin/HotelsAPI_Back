package fr.fms.hotels.web;


import fr.fms.hotels.entities.Hotel;
import fr.fms.hotels.security.entities.AppRole;
import fr.fms.hotels.security.entities.AppUser;
import fr.fms.hotels.security.service.AccountServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@Slf4j
public class AccountRestController {
    @Autowired
    AccountServiceImpl accountService;
    /**
     * Méthode en GET permettant d'afficher la liste des users
     * @return la liste des users
     */
    @GetMapping("/users")
    ResponseEntity<List<AppUser>> getUsers(){
        try {
            return this.accountService.listUsers();
        }catch (Exception e){
            log.error("problème lors du chargement de la liste", e.getCause());
        }
        return null;
    }
    @GetMapping("/roles")
    ResponseEntity<List<AppRole>> getRoles(){
        try {
            return this.accountService.listRoles();
        }catch (Exception e){
            log.error("problème lors du chargement de la liste", e.getCause());
        }
        return null;
    }
    /**
     * Méthode en Post permettant de sauvegarder un nouvel utilisateur
     * @param u un objet User
     * @return user sauvegardé en base
     */
    @PostMapping("/users")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser u){
        AppUser appUser = accountService.saveUser(u);
        if(Objects.isNull(appUser)) {
            return ResponseEntity.noContent().build();
        }
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(appUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/role")
    public AppRole postRole(@RequestBody AppRole role) { return this.accountService.saveRole(role); }

    @PostMapping("/roleUser")
    public void postRoleToUser(@RequestBody UserRoleForm userRoleForm) {
        accountService.addRoleToUser(userRoleForm.getUsername(),userRoleForm.getRolename());
    }
    @GetMapping("/users/{rolename}")
    public List<AppUser> getUsersByRole(@PathVariable String rolename){
        return accountService.getUsersByRolename(rolename);
    }
    /**
     * Méthode DELETE permettant la suppression d'un user
     * @param id du user
     * @return response entity status ok
     */
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            accountService.deleteUser(id);
        }
        catch (Exception e) {
            log.error("Problème durant la suppression du user d'id : {}",id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        log.info("Suppression du user d'id : {}", id);
        return ResponseEntity.ok().build();
    }
    /**
     * Méthode PUT permettant de mettre à jour un user
     * @param u un user
     * @return response entity status ok
     */
    @PutMapping("/users")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser u){
        AppUser appUser = accountService.readUserById(u.getId()).get();
        appUser.setUsername(u.getUsername());
        appUser.setPassword(u.getPassword());
        if(Objects.isNull(accountService.saveUser(appUser))) {
            return ResponseEntity.noContent().build();
        }
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(appUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}

@Data
class UserRoleForm {
    private String username;
    private String rolename;
}
package fr.fms.hotels.web;


import fr.fms.hotels.security.entities.AppRole;
import fr.fms.hotels.security.entities.AppUser;
import fr.fms.hotels.security.service.AccountServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
public class AccountRestController {
    @Autowired
    AccountServiceImpl accountService;
    /**
     * Méthode en GET permettant d'afficher la liste des users
     * @return la liste des users
     */
    @GetMapping("/users")
    ResponseEntity<List<AppUser>> getUsers(){  return this.accountService.listUsers(); }

    /**
     * Méthode en Post permettant de sauvegarder un nouvel utilisateur
     * @param user
     * @return
     */
    @PostMapping("/users")
    public AppUser postUser(@RequestBody AppUser user) { return this.accountService.saveUser(user); }

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
}

@Data
class UserRoleForm {
    private String username;
    private String rolename;
}
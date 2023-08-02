package fr.fms.hotels.security.dao;

import fr.fms.hotels.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    List<AppUser> findByRolesRolename(String rolename);
}

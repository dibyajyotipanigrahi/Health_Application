package in.ashokit.authservice.repository;

import in.ashokit.authservice.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

public Optional<Roles> findByroleName(String roleName);
}

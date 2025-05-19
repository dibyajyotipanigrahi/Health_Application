package in.ashokit.AuthService.Repository;

import in.ashokit.AuthService.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
}

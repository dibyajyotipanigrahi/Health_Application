package in.ashokit.AuthService.Repository;

import in.ashokit.AuthService.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

Optional<User>  findByEmail(String email);
}

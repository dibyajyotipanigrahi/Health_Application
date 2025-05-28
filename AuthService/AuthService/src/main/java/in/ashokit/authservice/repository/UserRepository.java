package in.ashokit.authservice.repository;

import in.ashokit.authservice.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

Optional<User>  findByEmail(String email);
}

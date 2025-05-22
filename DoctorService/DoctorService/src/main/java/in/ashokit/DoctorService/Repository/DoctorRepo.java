package in.ashokit.DoctorService.Repository;

import in.ashokit.DoctorService.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    public List<Doctor> findBySpecialization(String specialisation);

}

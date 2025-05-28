package in.ashokit.doctorservice.repository;

import in.ashokit.doctorservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    public List<Doctor> findBySpecialization(String specialisation);

}

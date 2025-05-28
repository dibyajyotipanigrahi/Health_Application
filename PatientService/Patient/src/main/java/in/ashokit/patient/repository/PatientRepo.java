package in.ashokit.patient.repository;

import in.ashokit.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Integer> {


}

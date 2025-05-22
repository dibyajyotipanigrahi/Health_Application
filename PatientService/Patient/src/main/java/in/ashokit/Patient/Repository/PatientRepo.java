package in.ashokit.Patient.Repository;

import in.ashokit.Patient.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Integer> {


}

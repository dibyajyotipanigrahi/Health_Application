package in.ashokit.AppointmentService.Repopsitory;

import in.ashokit.AppointmentService.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {


    public List<Appointment> findByDoctorIdAndStatus(Integer doctorId, String status);

}
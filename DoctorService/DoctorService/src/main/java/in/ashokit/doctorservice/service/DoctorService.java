package in.ashokit.doctorservice.service;

import in.ashokit.doctorservice.dto.DoctorDto;
import in.ashokit.doctorservice.dto.DoctorResponseDto;

import java.util.List;

public interface DoctorService {


    public DoctorResponseDto addDoctor(DoctorDto doctor);

    public DoctorResponseDto findByIdDoctor(Integer id);

    public List<DoctorResponseDto> getAllDoctor();

    public DoctorResponseDto updateDoctor(Integer id, DoctorDto dto);

    public List<DoctorResponseDto> getDoctorsBySpecialization(String specialization);

    public void deleteDoctor(Integer id);
}

package in.ashokit.AppointmentService.Dto;

import java.time.LocalDate;

public class DoctorDto {

    private Integer id;
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private LocalDate createdDate;
    private LocalDate updateDate;

    public DoctorDto() {
    }

    public DoctorDto(Integer id, String name, String specialization, String email, String phone, LocalDate createdDate, LocalDate updateDate) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}

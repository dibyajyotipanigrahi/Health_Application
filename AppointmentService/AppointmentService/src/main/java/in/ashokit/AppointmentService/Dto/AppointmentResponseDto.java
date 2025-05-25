package in.ashokit.AppointmentService.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentResponseDto {


    private Integer id;
    private Integer userId;
    private Integer doctorId;
    private String status;
    private LocalDateTime dateTime;


    private LocalDate createdDate;

    private LocalDate updatedDate;

    public AppointmentResponseDto() {

    }

    public AppointmentResponseDto(Integer id, Integer userId, Integer doctorId, String status, LocalDateTime dateTime, LocalDate createdDate, LocalDate updatedDate) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.status = status;
        this.dateTime = dateTime;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "AppointmentResponseDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", doctorId=" + doctorId +
                ", status='" + status + '\'' +
                ", dateTime=" + dateTime +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}

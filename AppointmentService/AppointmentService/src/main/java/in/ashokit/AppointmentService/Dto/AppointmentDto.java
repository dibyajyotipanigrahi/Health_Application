package in.ashokit.AppointmentService.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentDto {
    public AppointmentDto() {
    }

    private Integer userId;

    private Integer doctorId;

    private String status;

    private LocalDateTime dateTime;


    private LocalDate createdDate;

    private LocalDate UpdatedDate;
    public AppointmentDto(Integer userId, Integer doctorId, String status, LocalDateTime dateTime) {
        this.userId = userId;
        this.doctorId = doctorId;
        this.status = status;
        this.dateTime = dateTime;
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
        return UpdatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        UpdatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "userId=" + userId +
                ", doctorId=" + doctorId +
                ", status='" + status + '\'' +
                ", dateTime=" + dateTime +
                ", createdDate=" + createdDate +
                ", UpdatedDate=" + UpdatedDate +
                '}';
    }
}

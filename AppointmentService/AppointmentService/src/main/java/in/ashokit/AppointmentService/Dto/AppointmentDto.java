package in.ashokit.AppointmentService.Dto;

import java.time.LocalDateTime;

public class AppointmentDto {
    public AppointmentDto(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;
    private Integer doctorId;

    private String status;

    private LocalDateTime dateTime;

    public AppointmentDto() {
    }

    public AppointmentDto(Integer userId, Integer doctorId, String status, LocalDateTime dateTime) {
        this.userId = userId;
        this.doctorId = doctorId;
        this.status = status;
        this.dateTime = dateTime;
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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "userId=" + userId +
                ", doctorId=" + doctorId +
                ", status='" + status + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}

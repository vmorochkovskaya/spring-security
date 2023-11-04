package jmp.dto.main.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public class SubscriptionResponseDto {
    @Schema(name = "SubscriptionID", description = "Sample subscription id for the documentation", example = "123", required = true)
    private Long id;
    @Schema(name = "UserID", description = "Sample user id for the documentation", example = "123")
    private Long userId;
    @Schema(name = "StartDate", description = "Sample start date for the documentation", example = "2020-03-03")
    private String startDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "SubscriptionResponseDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", startDate='" + startDate + '\'' +
                '}';
    }

    public SubscriptionResponseDto(Long id, Long userId, String startDate) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
    }

    public SubscriptionResponseDto() {
    }
}

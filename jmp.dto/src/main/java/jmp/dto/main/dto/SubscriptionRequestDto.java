package jmp.dto.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SubscriptionRequestDto {
    @Schema(name = "SubscriptionID", description = "Sample subscription id for the documentation", example = "123")
    private Long id;
    @Schema(name = "UserID", description = "Sample user id for the documentation", example = "123", required = true)
    private Long userId;

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

    @Override
    public String toString() {
        return "SubscriptionRequestDto{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }

    public SubscriptionRequestDto(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public SubscriptionRequestDto() {
    }
}

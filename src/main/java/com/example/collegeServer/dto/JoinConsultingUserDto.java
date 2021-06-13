package com.example.collegeServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class JoinConsultingUserDto {
    private String userId;
    private Long consultingId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getConsultingId() {
        return consultingId;
    }

    public void setConsultingId(Long consultingId) {
        this.consultingId = consultingId;
    }
}

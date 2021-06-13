package com.example.collegeServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JoinMasterClassUserDto {
    private Long masterClassId;
    private String userId;

    public Long getMasterClassId() {
        return masterClassId;
    }

    public void setMasterClassId(Long masterClassId) {
        this.masterClassId = masterClassId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

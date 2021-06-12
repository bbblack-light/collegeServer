package com.example.collegeServer.dto.user;

import com.example.collegeServer.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailDto {
        private String userId;
        private String firstName;
        private String lastName;
        private String position;

        public static UserDetailDto convertFromEntity(User user) {
            UserDetailDto dto = new UserDetailDto();
            BeanUtils.copyProperties(user, dto);
            return dto;
        }

        public static List<UserDetailDto> convertFromEntities(List<User> users) {
            return users.stream().map(UserDetailDto::convertFromEntity).collect(Collectors.toList());
        }
}

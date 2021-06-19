package com.example.collegeServer.config;

import com.example.collegeServer.dto.JoinConsultingUserDto;
import com.example.collegeServer.dto.JoinMasterClassUserDto;
import com.example.collegeServer.model.buisness.JoinConsultingUser;
import com.example.collegeServer.model.buisness.JoinMasterClassUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        configureJoinMasterClassUserMappings(modelMapper);
        configureJoinConsultingUserMappings(modelMapper);
        return modelMapper;

    }

    private void configureJoinMasterClassUserMappings(ModelMapper modelMapper) {
        modelMapper.typeMap(JoinMasterClassUser.class, JoinMasterClassUserDto.class).addMappings(mapper -> {
            mapper.map(join -> join.getMasterClass().getId(), JoinMasterClassUserDto::setMasterClassId);
            mapper.map(join -> join.getUser().getUserId(), JoinMasterClassUserDto::setUserId);
        });

    }

    private void configureJoinConsultingUserMappings(ModelMapper modelMapper) {
        modelMapper.typeMap(JoinConsultingUser.class, JoinConsultingUserDto.class).addMappings(mapper -> {
            mapper.map(join -> join.getConsulting().getId(), JoinConsultingUserDto::setConsultingId);
            mapper.map(join -> join.getUser().getUserId(), JoinConsultingUserDto::setUserId);
        });
    }

}

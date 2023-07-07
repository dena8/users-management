package com.example.usersmanagement.Configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    private final ModelMapper modelMapper;

    public ModelMapperConfiguration() {
        this.modelMapper = new ModelMapper();
        setGlobalMapperConfiguration();
    }

    private void setGlobalMapperConfiguration() {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Bean
    public ModelMapper modelMapper() {
        return modelMapper;
    }
}

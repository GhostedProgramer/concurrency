package com.billy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "schedule")
@Data
public class ScheduleConfig {

    private String ecifUrl;
    private String engineUrl;
    private Map<String, String> urlMaps;
}

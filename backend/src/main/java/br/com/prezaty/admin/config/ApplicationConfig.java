package br.com.prezaty.admin.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationConfig {

    @Value("${spring.profiles}")
    private String activeProfile;

}

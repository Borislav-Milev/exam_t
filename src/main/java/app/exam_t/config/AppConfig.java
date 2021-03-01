package app.exam_t.config;

import app.exam_t.util.ModelValidationChecker;
import app.exam_t.util.ModelValidationCheckerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelValidationChecker attributesAdder() {
        return new ModelValidationCheckerImpl();
    }
}

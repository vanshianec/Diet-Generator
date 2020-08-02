package diet.dietgenerator.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//TODO test without enable scheduling
@Configuration
@EnableScheduling
public class WebConfig implements WebMvcConfigurer {
}

package s22.carRest;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig extends WebMvcAutoConfiguration implements WebMvcConfigurer {

	    // Spring Boot 2.3.9.RELEASE
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/rest/cars")
	                .allowedOriginPatterns(CorsConfiguration.ALL)
	                .allowedMethods(CorsConfiguration.ALL)
	                .allowedHeaders(CorsConfiguration.ALL)
	                .exposedHeaders("Location")
	                .allowCredentials(true);
	    }
	}


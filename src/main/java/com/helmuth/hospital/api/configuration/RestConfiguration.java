package com.helmuth.hospital.api.configuration;

import com.helmuth.hospital.api.projection.MeasurementProjection;
import com.helmuth.hospital.api.projection.NotesProjection;
import com.helmuth.hospital.api.projection.PatientProjection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration (RepositoryRestConfiguration repositoryRestConfiguration, CorsRegistry cors) {
        repositoryRestConfiguration.getProjectionConfiguration()
                .addProjection(PatientProjection.class).addProjection(MeasurementProjection.class)
                .addProjection(NotesProjection.class);
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}

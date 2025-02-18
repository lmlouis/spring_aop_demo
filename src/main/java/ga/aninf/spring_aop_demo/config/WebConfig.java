package ga.aninf.spring_aop_demo.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }

    /**
     * Cette méthode configure et retourne un bean GroupedOpenApi pour le groupe d'API public.
     * Le bean GroupedOpenApi est utilisé pour regrouper et documenter plusieurs spécifications OpenApi.
     *
     * @return Un bean GroupedOpenApi configuré pour le groupe d'API public.
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}

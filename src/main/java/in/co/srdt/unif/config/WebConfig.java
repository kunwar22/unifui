package in.co.srdt.unif.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/passwordreset/{x:[\\w\\-]+}").setViewName("forward:/forms/resetpassword/reset");
    	registry.addViewController("/passwordreset/{x:[\\w\\-]+}").setViewName("forward:/forms/resetpassword/reset");
    	registry.addViewController("/").setViewName("forward:/login");
        registry.addViewController("/{x:[\\w\\-]+}").setViewName("forward:/home");
        registry.addViewController("/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}").setViewName("forward:/home");
    }

    @Bean
    public RestTemplate getRestTemplate() {
    	SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(300000);
        factory.setReadTimeout(300000);
        return new RestTemplate();
    }

    @Bean
    @SessionScope
    public HttpHeaders getHttpHeader() {
        return new HttpHeaders();
    }

}

package kg.attractor.job_search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    private LocaleChangeInterceptor localeChangeInterceptor() {
        var loc = new LocaleChangeInterceptor();
        loc.setParamName("lang");
        return loc;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CustomLocaleResolver clr = new CustomLocaleResolver();
        clr.setDefaultLocale(Locale.ENGLISH);
        return clr;
    }
}

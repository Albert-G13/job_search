package kg.attractor.job_search.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.job_search.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Locale;

public class CustomLocaleResolver extends SessionLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute(LOCALE_SESSION_ATTRIBUTE_NAME);
        if (locale != null) return locale;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {

            UserService userService = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(request.getServletContext())
                    .getBean(UserService.class);

            String email = auth.getName();
            String lang = userService.getLanguageByEmail(email);

            if (lang != null) {
                Locale dbLocale = new Locale(lang);
                request.getSession().setAttribute(LOCALE_SESSION_ATTRIBUTE_NAME, dbLocale);
                return dbLocale;
            }
        }

        return super.resolveLocale(request);
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        super.setLocale(request, response, locale);

        if (locale != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
                UserService userService = WebApplicationContextUtils
                        .getRequiredWebApplicationContext(request.getServletContext())
                        .getBean(UserService.class);

                userService.updateLanguage(auth.getName(), locale.getLanguage());
            }
        }
    }
}
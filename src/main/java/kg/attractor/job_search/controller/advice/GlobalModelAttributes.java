package kg.attractor.job_search.controller.advice;

import kg.attractor.job_search.model.User;
import kg.attractor.job_search.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Objects;

    @ControllerAdvice
    public class GlobalModelAttributes {

        @ModelAttribute("user")
        public User user(Authentication authentication) {
            if (authentication == null ||
                    "anonymousUser".equals(authentication.getPrincipal())) {
                return null;
            }
            return ((CustomUserDetails) Objects.requireNonNull(authentication.getPrincipal())).getUser();
        }
    }

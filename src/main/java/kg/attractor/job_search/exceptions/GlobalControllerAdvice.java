package kg.attractor.job_search.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidUserException.class)
    public String invalidAuthorOfVacancy(Model model, InvalidUserException e){
        model.addAttribute("status", HttpStatus.FORBIDDEN.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(InvalidAuthorOfResumeEditException.class)
    public String invalidAuthorOfVacancy(Model model, InvalidAuthorOfResumeEditException e){
        model.addAttribute("status", HttpStatus.FORBIDDEN.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(InvalidAuthorOfVacancyEditException.class)
    public String invalidAuthorOfVacancy(Model model, InvalidAuthorOfVacancyEditException e){
        model.addAttribute("status", HttpStatus.FORBIDDEN.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound(Model model, HttpServletRequest request){
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }
    @ExceptionHandler(exception = InvalidRoleEmployerException.class)
    public String invalidRoleEmployer(Model model, InvalidRoleEmployerException e){
        model.addAttribute("status", HttpStatus.FORBIDDEN.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }
    @ExceptionHandler(InvalidRoleApplicantException.class)
    public String invalidRoleApplicant(Model model, InvalidRoleApplicantException e){
        model.addAttribute("status", HttpStatus.FORBIDDEN.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }
    @ExceptionHandler(WorkExperienceDateException.class)
    public String workExperienceDate(WorkExperienceDateException e,Model model){
        model.addAttribute("status", HttpStatus.CONFLICT.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }
    @ExceptionHandler(InvalidWorkExperienceAgeException.class)
    public String workExperienceAge(InvalidWorkExperienceAgeException e,Model model){
        model.addAttribute("status", HttpStatus.CONFLICT.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }
    @ExceptionHandler(NumberAlreadyExistsException.class)
    public String numberAlreadyExists(NumberAlreadyExistsException e,Model model){
        model.addAttribute("status", HttpStatus.CONFLICT.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String emailAlreadyExists(UserAlreadyExistsException e,Model model){
        model.addAttribute("status", HttpStatus.CONFLICT.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(Model model) {
        model.addAttribute("status", HttpStatus.FORBIDDEN.value());
        model.addAttribute("reason", HttpStatus.FORBIDDEN.getReasonPhrase());
        return "errors/error";
    }
    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalState(IllegalStateException e, Model model) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", HttpStatus.BAD_REQUEST.getReasonPhrase());
        return "errors/error";
    }
}



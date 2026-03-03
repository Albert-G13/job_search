package kg.attractor.job_search.exceptions;

public class InvalidWorkExperienceAgeException extends RuntimeException {
    public InvalidWorkExperienceAgeException(){super("Стаж (опыт работы) не может быть больше возраста пользователя");}
}

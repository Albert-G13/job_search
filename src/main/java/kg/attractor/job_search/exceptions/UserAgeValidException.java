package kg.attractor.job_search.exceptions;

public class UserAgeValidException extends RuntimeException {
    public UserAgeValidException(){super("Возраст должен быть не менее 14 лет");}
}

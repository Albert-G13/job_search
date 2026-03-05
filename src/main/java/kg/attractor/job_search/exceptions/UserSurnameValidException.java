package kg.attractor.job_search.exceptions;

public class UserSurnameValidException extends RuntimeException {
    public UserSurnameValidException(){super("Фамилия не может быть пустой");}
}

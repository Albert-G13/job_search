package kg.attractor.job_search.exceptions;

public class NumberAlreadyExistsException extends RuntimeException {
    public NumberAlreadyExistsException() {
        super("Пользователь с таким номером уже существует");
    }
}

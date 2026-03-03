package kg.attractor.job_search.exceptions;

public class EndDateIsAfterNowException extends RuntimeException {
    public EndDateIsAfterNowException(){super("Дата окончания не может быть в будущем");}
}

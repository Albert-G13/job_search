package kg.attractor.job_search.exceptions;

public class InvalidAuthorOfResumeEditException extends RuntimeException {
    public InvalidAuthorOfResumeEditException() {
        super("Редактировать можно только свои резюме");
    }
}

package kg.attractor.job_search.exceptions;

public class InvalidAuthorOfVacancyEditException extends RuntimeException {
    public InvalidAuthorOfVacancyEditException() {
        super("Редактировать можно только свои вакансии");
    }
}

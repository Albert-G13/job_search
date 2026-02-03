package kg.attractor.job_search.exceptions;


public class InvalidRoleEmployerException extends RuntimeException {
    public InvalidRoleEmployerException() {super("Создавать вакансии может только работодатель");
    }
}

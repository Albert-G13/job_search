package kg.attractor.job_search.exceptions;

public class InvalidRoleApplicantException extends RuntimeException {
    public InvalidRoleApplicantException() {
        super("Создавать резюме может только соискатель");
    }
}

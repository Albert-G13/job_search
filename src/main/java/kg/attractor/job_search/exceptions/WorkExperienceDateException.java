package kg.attractor.job_search.exceptions;

public class WorkExperienceDateException extends RuntimeException{
    public WorkExperienceDateException(){super("Дата начала трудоустройства не может быть больше даты конца трудоустройства");}
}

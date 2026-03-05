package kg.attractor.job_search.exceptions;

public class AlreadyRespondedException extends RuntimeException {
    public AlreadyRespondedException(){super("Вы уже откликнулись на эту вакансию с этим резюме");}
}

package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class RoleNotFoundException extends NoSuchElementException {
    public RoleNotFoundException(){ super("Role not found");}
}

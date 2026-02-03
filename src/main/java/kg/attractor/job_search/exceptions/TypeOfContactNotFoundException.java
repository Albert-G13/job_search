package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class TypeOfContactNotFoundException extends NoSuchElementException {
    public TypeOfContactNotFoundException() {
        super("Тип контакта не найден");
    }
}

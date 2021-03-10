package ru.lalibrairiestore.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("Невозможно найти id: " + id);
    }

    public EntityNotFoundException() {
        super("Невозможно найти id: " );
    }
}

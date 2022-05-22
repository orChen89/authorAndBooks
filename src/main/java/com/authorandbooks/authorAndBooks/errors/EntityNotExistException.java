package com.authorandbooks.authorAndBooks.errors;

import com.authorandbooks.authorAndBooks.enums.EntityType;

public class EntityNotExistException extends LibraryException {

           public EntityNotExistException(final EntityType entityType, final Constraint constraint) {

            super("This " + entityType + constraint.getErrorMsg());
        }
}

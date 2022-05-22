package com.authorandbooks.authorAndBooks.errors;

import com.authorandbooks.authorAndBooks.enums.EntityType;

public class EntityAlreadyExistsException extends LibraryException {

            public EntityAlreadyExistsException(final EntityType entityType, final Constraint constraint){

            super("This " + entityType + constraint.getErrorMsg());
        }
}

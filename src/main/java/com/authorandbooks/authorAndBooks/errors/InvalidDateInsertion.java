package com.authorandbooks.authorAndBooks.errors;

public class InvalidDateInsertion extends LibraryException{

    public InvalidDateInsertion(final Constraint constraint) {
        super(constraint.getErrorMsg());

    }
}

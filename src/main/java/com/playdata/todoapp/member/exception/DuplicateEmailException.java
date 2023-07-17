package com.playdata.todoapp.member.exception;

public class DuplicateEmailException extends IllegalArgumentException{

    public DuplicateEmailException() {
        super("EXISTS EMAIL");
    }

    public DuplicateEmailException(String s) {
        super(s);
    }
}

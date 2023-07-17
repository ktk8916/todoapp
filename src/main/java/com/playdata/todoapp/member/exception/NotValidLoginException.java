package com.playdata.todoapp.member.exception;

public class NotValidLoginException extends NullPointerException{

    public NotValidLoginException(String s) {
        super(s);
    }

    public NotValidLoginException() {
        super("CHECK LOGIN USER");
    }
}
